package id.urbanwash.wozapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.StartOrderAdapter;
import id.urbanwash.wozapp.callback.StartOrderActivityCallback;
import id.urbanwash.wozapp.util.CustomViewPager;

import id.urbanwash.wozapp.widget.DepthPageTransformer;
import id.urbanwash.wozapp.widget.DialogOrderCancelled;
import id.urbanwash.wozapp.widget.DialogOrderSuccess;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 3/19/16.
 */
public class StartOrderActivity extends AppCompatActivity implements StartOrderActivityCallback {
    private int startOrderStep = 0;
    private CustomViewPager vp_startorder;
    private StartOrderAdapter vp_startorder_adapter;
    private ProgressBar pb_startorder;
    private AppCompatImageView iv_arrow_right_startorder;
    private AppCompatTextView tv_confirm_startorder;
    private AppCompatTextView tv_title_custombar;
    private DialogOrderSuccess dialogOrderSuccess;
    private DialogOrderCancelled dialogOrderCancelled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custon_navigation_bar);
        getSupportActionBar().setHomeAsUpIndicator(changeBackArrowColor());

        this.vp_startorder = (CustomViewPager) findViewById(R.id.vp_startorder);
        this.pb_startorder = (ProgressBar) findViewById(R.id.pb_startorder);
        this.iv_arrow_right_startorder = (AppCompatImageView) findViewById(R.id.iv_arrow_right_startorder);
        this.tv_confirm_startorder = (AppCompatTextView) findViewById(R.id.tv_confirm_startorder);

        this.vp_startorder_adapter = new StartOrderAdapter(getSupportFragmentManager(), this);
        this.vp_startorder.setAdapter(this.vp_startorder_adapter);
        this.vp_startorder.setPagingEnabled(false);
        this.vp_startorder.setPageTransformer(false, new DepthPageTransformer());

        this.iv_arrow_right_startorder.setOnClickListener(new GoToNextStep());
        this.tv_confirm_startorder.setOnClickListener(new ConfirmStartOrder());

        setProgressBarOnTop(this.startOrderStep);
        setTitleOnNavigationBar(this.startOrderStep);
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
            if (this.startOrderStep == 0)
                showCloseDialogStartOrder();

            if (this.startOrderStep > -1)
                this.startOrderStep--;

            setReverseProgressBarOnTop(this.startOrderStep);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ShowOrderCompleteView() {
        Intent orderCompleteIntent = new Intent(this, OrderCompleteActivity.class);
        startActivity(orderCompleteIntent);
        StartOrderActivity.this.finish();
    }

    class GoToNextStep implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (startOrderStep < 3)
                startOrderStep++;

            setProgressBarOnTop(startOrderStep);
        }
    }

    class ConfirmStartOrder implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            showConfirmDialogStartOrder();
        }
    }

    private void setProgressBarOnTop(int step) {
        switch (step) {
            case 0:
                setProgressWithAnimation(0, 33);
                this.vp_startorder.setCurrentItem(step);
                setTitleOnNavigationBar(step);
                break;

            case 1:
                setProgressWithAnimation(33, 66);
                this.vp_startorder.setCurrentItem(step);
                setTitleOnNavigationBar(step);
                this.tv_confirm_startorder.setVisibility(View.INVISIBLE);
                this.iv_arrow_right_startorder.setVisibility(View.VISIBLE);
                break;

            case 2:
                setProgressWithAnimation(66, 99);
                this.vp_startorder.setCurrentItem(step);
                setTitleOnNavigationBar(step);
                this.iv_arrow_right_startorder.setVisibility(View.INVISIBLE);
                this.tv_confirm_startorder.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setReverseProgressBarOnTop(int step) {
        switch (step) {
            case 0:
                setProgressWithAnimation(66, 33);
                this.vp_startorder.setCurrentItem(step);
                setTitleOnNavigationBar(step);
                break;

            case 1:
                setProgressWithAnimation(99, 66);
                this.vp_startorder.setCurrentItem(step);
                setTitleOnNavigationBar(step);
                this.tv_confirm_startorder.setVisibility(View.INVISIBLE);
                this.iv_arrow_right_startorder.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void showConfirmDialogStartOrder() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want confirm order?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        showDialogOrderSuccess();
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

    private void showCloseDialogStartOrder() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want to cancel order?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        showDialogOrderCancelled();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        startOrderStep = 0;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void setTitleOnNavigationBar(int step) {
        View view = getSupportActionBar().getCustomView();
        this.tv_title_custombar = (AppCompatTextView) view.findViewById(R.id.tv_title_custombar);
        switch (step) {
            case 0:
                this.tv_title_custombar.setText("SCHEDULE PICK UP");
                break;

            case 1:
                this.tv_title_custombar.setText("SELECT SERVICES");
                break;

            case 2:
                this.tv_title_custombar.setText("ORDER SUMMARY");
                break;
        }
        this.tv_title_custombar.startAnimation(setAnimationOnNavigationTitle());
    }

    private Drawable changeBackArrowColor() {
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    private void setProgressWithAnimation(int value1, int value2) {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(this.pb_startorder, "progress", value1, value2);
        progressAnimator.setDuration(250);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    private Animation setAnimationOnNavigationTitle() {
        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(500);
        in.setInterpolator(new AccelerateDecelerateInterpolator());

        return in;
    }

    private void showDialogOrderSuccess() {
        this.dialogOrderSuccess = new DialogOrderSuccess(this, this);
        this.dialogOrderSuccess.setCancelable(false);
        this.dialogOrderSuccess.show(getSupportFragmentManager(), "dialogOrderSuccess");
    }

    private void showDialogOrderCancelled() {
        this.dialogOrderCancelled = new DialogOrderCancelled(this, this);
        this.dialogOrderCancelled.setCancelable(false);
        this.dialogOrderCancelled.show(getSupportFragmentManager(), "dialogOrderCancelled");
    }

}
