package id.urbanwash.wozapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.urbanwash.wozapp.adapter.ServicesWozappAdapter;
import id.urbanwash.wozapp.fragment.DryCleanServiceFragment;
import id.urbanwash.wozapp.fragment.KgServiceFragment;
import id.urbanwash.wozapp.fragment.PieceServiceFragment;
import id.urbanwash.wozapp.util.CustomViewPager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 4/10/16.
 */
public class ServicesWozappActivity extends AppCompatActivity {
    private Fragment fragment;
    private CustomViewPager vp_services_wozapp;
    private ServicesWozappAdapter vp_services_wozapp_adapter;
    private AppCompatTextView tv_save_services_wozapp;
    private String servicesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_wozapp);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custon_navigation_bar);
        getSupportActionBar().setHomeAsUpIndicator(changeBackArrowColor());

        setNavigationBarTitle();

        this.servicesName = getIntent().getStringExtra("services");
        this.fragment = (this.servicesName.equals("PieceService")) ? new PieceServiceFragment(this) :
                (this.servicesName.equals("KgService")) ? new KgServiceFragment(this) : new DryCleanServiceFragment(this);

        this.vp_services_wozapp = (CustomViewPager) findViewById(R.id.vp_services_wozapp);
        this.tv_save_services_wozapp = (AppCompatTextView) findViewById(R.id.tv_save_services_wozapp);

        this.vp_services_wozapp_adapter = new ServicesWozappAdapter(getSupportFragmentManager(), this, this.fragment);
        this.vp_services_wozapp.setAdapter(this.vp_services_wozapp_adapter);

        this.tv_save_services_wozapp.setOnClickListener(new SaveOrderService());
    }

    private Drawable changeBackArrowColor() {
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    private void setNavigationBarTitle() {
        ((AppCompatTextView)getSupportActionBar().getCustomView().findViewById(R.id.tv_title_custombar)).setText("ORDER SERVICE");
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
            showCancelAlertDialog();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class SaveOrderService implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            showSaveAlertDialog();
        }
    }

    private void showCancelAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want cancel order service?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ServicesWozappActivity.this.finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showSaveAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want save order service?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ServicesWozappActivity.this.finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
