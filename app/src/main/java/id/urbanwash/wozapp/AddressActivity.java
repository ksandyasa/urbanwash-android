package id.urbanwash.wozapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.urbanwash.wozapp.adapter.CustomInfoWindowAdapter;
import id.urbanwash.wozapp.adapter.SearchableAddressAdapter;
import id.urbanwash.wozapp.callback.SearchableAddressAdapterCallback;
import id.urbanwash.wozapp.model.SearchAddress;
import id.urbanwash.wozapp.service.WozappLocationService;
import id.urbanwash.wozapp.util.Utility;
import id.urbanwash.wozapp.widget.RecycleDividerItemDecoration;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 3/29/16.
 */
public class AddressActivity extends AppCompatActivity implements OnMapReadyCallback, Callback, SearchView.OnQueryTextListener,
        SearchView.OnCloseListener, SearchableAddressAdapterCallback {
    private final static String TAG = AddressActivity.class.getSimpleName();

    private final static int LOCATION_LATLNG = 101;

    private ProgressDialog progressDialog;
    private SearchView sv_address;
    private RecyclerView rv_search_result_address;
    private LinearLayoutManager linearLayoutManager;
    private SearchableAddressAdapter rv_search_result_address_adapter;
    private AppCompatImageView iv_gps_address,
                               iv_info_address;
    private GoogleMap gmap_address;
    private SupportMapFragment supportMapFragment;
    private LatLng pinLatLng;
    private Marker markerPinPoint;
    private String[] responseString = {""};
    private String addressText;
    private List<SearchAddress> addressList;
    private int networkMode = -1;
    private static Handler messageHandler;
    private static Bundle bundleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custon_navigation_bar);
        getSupportActionBar().setHomeAsUpIndicator(changeBackCloseColor());

        setNavigationBarTitle("YOUR ADDRESS");
        setSearchAddressProgressDialog();

        this.sv_address = (SearchView) findViewById(R.id.sv_address);
        this.rv_search_result_address = (RecyclerView) findViewById(R.id.rv_search_result_address);
        this.iv_gps_address = (AppCompatImageView) findViewById(R.id.iv_gps_address);
        this.iv_info_address = (AppCompatImageView) findViewById(R.id.iv_info_address);

        try {
            initializeMap();
        } catch (Exception e) {
            Log.d(AddressActivity.class.getSimpleName(), "Failed loading map " + e.getMessage());
        }

        this.sv_address.setOnQueryTextListener(this);
        this.sv_address.setOnCloseListener(this);
        this.iv_gps_address.setOnClickListener(new ShowCurrentLocation());
        this.iv_info_address.setOnClickListener(new ShowInfoOnMarkerPin());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            showCloseAlertDialog();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initializeMap() {
        this.supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gmap_address);
        this.supportMapFragment.getMapAsync(this);
    }

    private void getAddressFromNetwork(double latitude, double longitude) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://maps.google.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&sensor=true")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(this);
    }

    private void getListAddressFromNetwork(String addressText) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://maps.google.com/maps/api/geocode/json?address="+addressText.replace(" ", "%20")+"&sensor=false")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(this);
    }

    private void setPinOnTappedLocation(String json) {
        try {
            this.networkMode = -1;

            this.addressText = Utility.JSONUtility.getAddressTextFromJSON(json);

            if (this.markerPinPoint != null)
                this.markerPinPoint.remove();

            this.gmap_address.moveCamera(CameraUpdateFactory.newLatLngZoom(this.pinLatLng, 15.0f));

            this.markerPinPoint = this.gmap_address.addMarker(
                    new MarkerOptions().position(this.pinLatLng)
                            .title("Use this Locations")
                            .snippet(addressText)
                            .infoWindowAnchor(0, 0)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_pin_green)));

            this.gmap_address.setInfoWindowAdapter(new CustomInfoWindowAdapter(this));

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void setListAddressFromResult(String json) {
        try {
            this.networkMode = -1;

            this.addressList = new ArrayList<>();
            this.addressList = Utility.JSONUtility.getListAddressTextFromJSON(json);

            this.linearLayoutManager = new LinearLayoutManager(this);
            this.rv_search_result_address.setHasFixedSize(true);
            this.rv_search_result_address.setLayoutManager(this.linearLayoutManager);
            this.rv_search_result_address.addItemDecoration(new RecycleDividerItemDecoration(this));

            this.rv_search_result_address_adapter = new SearchableAddressAdapter(this, this.addressList, this);
            this.rv_search_result_address.setAdapter(this.rv_search_result_address_adapter);
            this.rv_search_result_address.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void setSearchAddressProgressDialog() {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage("Obtaining result search..");
    }

    private Drawable changeBackCloseColor() {
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.icon_cancel)).getBitmap();
        Drawable upArrow = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 24, 24, true));
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    private void setNavigationBarTitle(String text) {
        ((AppCompatTextView)getSupportActionBar().getCustomView().findViewById(R.id.tv_title_custombar)).setText(text);
    }

    private void showCloseAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Quit pick location?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with quit
                        stopService(new Intent(AddressActivity.this, WozappLocationService.class));
                        AddressActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gmap_address = googleMap;

        this.gmap_address.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        this.gmap_address.getUiSettings().setCompassEnabled(true);
        this.gmap_address.getUiSettings().setZoomControlsEnabled(true);

        LatLng position = new LatLng(-6.229728,106.6894312);
        this.gmap_address.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13.0f));

        this.gmap_address.setOnMapClickListener(new GetLatLngFromMap());
        this.gmap_address.setOnInfoWindowClickListener(new PickAddressFromInfoWindow());
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.d(TAG, e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        responseString[0] = response.body().string();
        Log.d(TAG, responseString[0]);
        AddressActivity.this.runOnUiThread(new ShowPinOnTappedLocation());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.networkMode = 1;
        if (this.progressDialog != null)
            this.progressDialog.show();
        getListAddressFromNetwork(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onClose() {
        this.rv_search_result_address.setVisibility(View.INVISIBLE);
        return false;
    }

    @Override
    public void onSearchableAddressAdapterCallback(double latitude, double longitude) {
        networkMode = 0;
        if (this.progressDialog != null)
            this.progressDialog.show();
        this.pinLatLng = new LatLng(latitude, longitude);
        getAddressFromNetwork(latitude, longitude);
        this.rv_search_result_address.setAdapter(null);
        this.rv_search_result_address_adapter = null;
        this.rv_search_result_address.setVisibility(View.INVISIBLE);
    }

    class ShowPinOnTappedLocation implements Runnable {

        @Override
        public void run() {
            if (progressDialog != null)
                progressDialog.dismiss();
            if (networkMode == 0)
                setPinOnTappedLocation(responseString[0]);
            else if (networkMode == 1) {
                setListAddressFromResult(responseString[0]);
            }
        }
    }

    class GetLatLngFromMap implements GoogleMap.OnMapClickListener {

        @Override
        public void onMapClick(LatLng latLng) {
            pinLatLng = latLng;
            networkMode = 0;
            if (progressDialog != null)
                progressDialog.show();
            getAddressFromNetwork(latLng.latitude, latLng.longitude);
        }
    }

    class PickAddressFromInfoWindow implements GoogleMap.OnInfoWindowClickListener {

        @Override
        public void onInfoWindowClick(Marker marker) {
            Log.d(TAG, "Longitude " + markerPinPoint.getPosition().latitude + " Longitude " + markerPinPoint.getPosition().longitude);
            Intent addressIntent = new Intent();
            addressIntent.putExtra("addressText", markerPinPoint.getSnippet());
            addressIntent.putExtra("addressLatitude", "" + markerPinPoint.getPosition().latitude);
            addressIntent.putExtra("addressLongitude", "" + markerPinPoint.getPosition().longitude);
            setResult(RESULT_OK, addressIntent);
            AddressActivity.this.finish();
        }
    }

    class ShowCurrentLocation implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            messageHandler = new MessengerHandler();
            Intent intentStartService = new Intent(AddressActivity.this, WozappLocationService.class);
            intentStartService.putExtra("MESSENGER", new Messenger(messageHandler));
            startService(intentStartService);
        }
    }

    class ShowInfoOnMarkerPin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (markerPinPoint != null) {
                if (markerPinPoint.isInfoWindowShown())
                    markerPinPoint.hideInfoWindow();
                else
                    markerPinPoint.showInfoWindow();
            }
        }
    }

    public static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message message) {
            int state = message.arg1;
            switch (state) {
                case LOCATION_LATLNG:
                    bundleData = message.getData();
                    Log.d(TAG,
                            "Latitude " + bundleData.get("LATITUDE") + ", Longitude " + bundleData.get("LONGITUDE"));

                    break;
            }
        }

    }
 }
