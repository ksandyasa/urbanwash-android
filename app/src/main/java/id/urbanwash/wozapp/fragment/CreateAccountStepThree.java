package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import id.urbanwash.wozapp.AddressActivity;
import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.RegisterActivity;
import id.urbanwash.wozapp.callback.CreateAccountStepThreeCallback;
import id.urbanwash.wozapp.widget.DialogAlertInfo;

/**
 * Created by apridosandyasa on 3/28/16.
 */
@SuppressLint("ValidFragment")
public class CreateAccountStepThree extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationChangeListener, CreateAccountStepThreeCallback {
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private AppCompatEditText ed_pinlocation_register;
    private AppCompatEditText ed_address_register;
    private GoogleMap gmap_register;
    private SupportMapFragment supportMapFragment;
    private Marker markerPinPoint;

    public CreateAccountStepThree() {

    }

    public CreateAccountStepThree(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_createaccount3, container, false);

        this.ed_pinlocation_register = (AppCompatEditText) this.rootView.findViewById(R.id.ed_pinlocation_register);
        this.ed_address_register = (AppCompatEditText) this.rootView.findViewById(R.id.ed_address_register);

        try {
            initializeMap();
        } catch (Exception e) {
            Log.i(CreateAccountStepThree.class.getSimpleName(), "Failed loading map " + e.getMessage());
        }

        this.ed_pinlocation_register.setOnClickListener(new ShowAddressView());

        return this.rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == this.appCompatActivity.RESULT_OK) {
            Log.d(CreateAccountStepThree.class.getSimpleName(), "addressText " + data.getStringExtra("addressText"));
            Log.d(CreateAccountStepThree.class.getSimpleName(),
                    "Latitude " + data.getStringExtra("addressLatitude") +
                    " Longitude " + data.getStringExtra("addressLongitude"));
            this.ed_pinlocation_register.setText(data.getStringExtra("addressText"));
            this.ed_address_register.setText(data.getStringExtra("addressText"));

            LatLng position = new LatLng(Double.parseDouble(data.getStringExtra("addressLatitude")),
                    Double.parseDouble(data.getStringExtra("addressLongitude")));
            this.gmap_register.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f));

            if (markerPinPoint != null)
                markerPinPoint.remove();

            markerPinPoint = this.gmap_register.addMarker(
                    new MarkerOptions().position(position)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_pin_green)));
        }
    }

    private void initializeMap() {
        this.supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.gmap_register);
        this.supportMapFragment.getMapAsync(this);
        if (this.appCompatActivity instanceof RegisterActivity)
            RegisterActivity.setCreateAccountStepThreeCallback(this);
    }

    @Override
    public void onCreateAccountStepThreeGetData() {
        if (this.ed_address_register.getText().toString().equals("")) {
            if (this.appCompatActivity instanceof RegisterActivity) {
                //((RegisterActivity) this.appCompatActivity).showDialogAlertInfo();
                ((RegisterActivity) this.appCompatActivity).showDialogAlertAddress();
                ((RegisterActivity) this.appCompatActivity).resetRegisterStep(2);
            }
        }else {
            Log.d(CreateAccountStepThree.class.getSimpleName(), "not empty");
            if (this.appCompatActivity instanceof RegisterActivity) {
                Log.d(CreateAccountStepThree.class.getSimpleName(), "finish");
                ((RegisterActivity) this.appCompatActivity).finish();
            }
        }
    }

    class ShowAddressView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent addressIntent = new Intent(appCompatActivity, AddressActivity.class);
            startActivityForResult(addressIntent, 110);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gmap_register = googleMap;
        this.supportMapFragment.getView().setClickable(false);

        this.gmap_register.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        this.gmap_register.getUiSettings().setAllGesturesEnabled(false);
        this.gmap_register.setOnMyLocationChangeListener(this);

        // untuk test menampilkan lokasi di peta
        // jika ingin menampilkan lokasi user, tinggal di uncomment baris code dibawah
        LatLng position = new LatLng(-6.229728,106.6894312);
        this.gmap_register.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f));
//
//        if (markerPinPoint != null)
//            markerPinPoint.remove();
//
//        markerPinPoint = this.gmap_register.addMarker(
//                new MarkerOptions().position(position)
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_pin_green)));
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.d(CreateAccountStepThree.class.getSimpleName(), "" + location.getLatitude());
        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        this.gmap_register.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15.0f));

        if (markerPinPoint != null)
            markerPinPoint.remove();

        markerPinPoint = this.gmap_register.addMarker(
                new MarkerOptions().position(currentLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_pin_green)));
    }
}
