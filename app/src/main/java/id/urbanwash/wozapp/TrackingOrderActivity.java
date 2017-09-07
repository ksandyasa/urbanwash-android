package id.urbanwash.wozapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.liulishuo.magicprogresswidget.MagicProgressCircle;

import id.urbanwash.wozapp.adapter.OrderCompleteAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 4/12/16.
 */
public class TrackingOrderActivity extends AppCompatActivity {
    private LinearLayout ll_content5_tracking_order;
    private AppCompatImageView iv_dropdown_content4_tracking_order;
    private RecyclerView rv_content5_tracking_order;
    private LinearLayoutManager linearLayoutManager;
    private OrderCompleteAdapter rv_content5_tracking_order_adapter;
    private MagicProgressCircle mpc_content1_tracking_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custon_navigation_bar);
        getSupportActionBar().setHomeAsUpIndicator(changeBackArrowColor());

        setNavigationBarTitle();

        this.ll_content5_tracking_order = (LinearLayout) findViewById(R.id.ll_content5_tracking_order);
        this.mpc_content1_tracking_order = (MagicProgressCircle) findViewById(R.id.mpc_content1_tracking_order);
        this.iv_dropdown_content4_tracking_order = (AppCompatImageView) findViewById(R.id.iv_dropdown_content4_tracking_order);
        this.rv_content5_tracking_order = (RecyclerView) findViewById(R.id.rv_content5_tracking_order);

        this.linearLayoutManager = new LinearLayoutManager(this);
        this.rv_content5_tracking_order.setHasFixedSize(true);
        this.rv_content5_tracking_order.setLayoutManager(this.linearLayoutManager);

        this.rv_content5_tracking_order_adapter = new OrderCompleteAdapter(this);
        this.rv_content5_tracking_order.setAdapter(this.rv_content5_tracking_order_adapter);

        this.iv_dropdown_content4_tracking_order.setOnClickListener(new ShowContent6View());
        this.mpc_content1_tracking_order.setSmoothPercent(0.5f);
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

    private void showCancelAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want quit tracking order?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        TrackingOrderActivity.this.finish();
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

    private Drawable changeBackArrowColor() {
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    private void setNavigationBarTitle() {
        ((AppCompatTextView)getSupportActionBar().getCustomView().findViewById(R.id.tv_title_custombar)).setText("TRACKING ORDER");
    }

    class ShowContent6View implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (ll_content5_tracking_order.isShown()) {
                ll_content5_tracking_order.setVisibility(View.GONE);
                iv_dropdown_content4_tracking_order.setImageResource(R.drawable.icon_arrow_right);
            }else {
                ll_content5_tracking_order.setVisibility(View.VISIBLE);
                iv_dropdown_content4_tracking_order.setImageResource(R.drawable.icon_dropdown);
            }
        }
    }

}
