package id.urbanwash.wozapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.LinearLayout;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.RegisterAdapter;
import id.urbanwash.wozapp.callback.CreateAccountStepOneCallback;
import id.urbanwash.wozapp.callback.CreateAccountStepThreeCallback;
import id.urbanwash.wozapp.util.CustomViewPager;

import id.urbanwash.wozapp.widget.DepthPageTransformer;
import id.urbanwash.wozapp.widget.DialogAlertAddress;
import id.urbanwash.wozapp.widget.DialogAlertDOB;
import id.urbanwash.wozapp.widget.DialogAlertInfo;
import id.urbanwash.wozapp.widget.DialogAlertName;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 3/18/16.
 */
public class RegisterActivity extends AppCompatActivity {
    private int registerStep = 0;
    private LinearLayout ll_step_register, ll_content_register;
    private CustomViewPager vp_register;
    private RegisterAdapter vp_register_adapter;
    private AppCompatTextView tv_step1_register, tv_step2_register,
                               tv_step3_register, tv_confirm_register;
    public static CreateAccountStepThreeCallback createAccountStepThreeCallback;
    public static CreateAccountStepOneCallback createAccountStepOneCallback;
    private DialogAlertInfo dialogAlertInfo;
    private DialogAlertAddress dialogAlertAddress;
    private DialogAlertName dialogAlertName;
    private DialogAlertDOB dialogAlertDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custon_navigation_bar);
        getSupportActionBar().setHomeAsUpIndicator(changeBackCloseColor());

        this.ll_step_register = (LinearLayout) findViewById(R.id.ll_step_register);
        this.tv_step1_register = (AppCompatTextView) findViewById(R.id.tv_step1_register);
        this.tv_step2_register = (AppCompatTextView) findViewById(R.id.tv_step2_register);
        this.tv_step3_register = (AppCompatTextView) findViewById(R.id.tv_step3_register);
        this.tv_confirm_register = (AppCompatTextView) findViewById(R.id.tv_confirm_register);

        this.ll_content_register = (LinearLayout) findViewById(R.id.ll_content_register);
        this.vp_register = (CustomViewPager) findViewById(R.id.vp_register);
        this.vp_register_adapter = new RegisterAdapter(getSupportFragmentManager(), this);
        this.vp_register.setAdapter(this.vp_register_adapter);
        this.vp_register.setPagingEnabled(false);
        this.vp_register.setPageTransformer(false, new DepthPageTransformer());

        setActiveStepTitle(this.registerStep);
        setActiveStepView(this.registerStep);
        setNavigationBarTitle("CREATE ACCOUNT", this.registerStep);

        this.tv_confirm_register.setOnClickListener(new NextStepRegister());
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
        if (resultCode == RESULT_OK && requestCode == 110) {
            setResult(RESULT_OK, data);
        }
    }

    class NextStepRegister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (registerStep < 3) {
                registerStep++;
                if (registerStep == 3)
                    showConfirmAlertDialog();
                if (registerStep == 1)
                    createAccountStepOneCallback.onCreateAccountStepOneGetData();
            }
            setActiveStepTitle(registerStep);
            setActiveStepView(registerStep);
        }
    }

    private void setActiveStepTitle(int step) {
        switch (step) {
            case 0:
                this.tv_step1_register.setTextColor(getResources().getColor(R.color.colorCustomGreen));
                this.tv_step2_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                this.tv_step3_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                break;

            case 1:
                this.tv_step1_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                this.tv_step2_register.setTextColor(getResources().getColor(R.color.colorCustomGreen));
                this.tv_step3_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                break;

            case 2:
                this.tv_step1_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                this.tv_step2_register.setTextColor(getResources().getColor(R.color.colorLightGrey));
                this.tv_step3_register.setTextColor(getResources().getColor(R.color.colorCustomGreen));
                break;
        }
    }

    private void setActiveStepView(int step) {
        switch (step) {
            case 0:
                this.vp_register.setCurrentItem(step);
                break;

            case 1:
                this.vp_register.setCurrentItem(step);
                break;

            case 2:
                setNavigationBarTitle("YOUR ADDRESS", step);
                this.ll_step_register.setVisibility(View.GONE);
                this.ll_content_register.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        0, 9f));
                this.vp_register.setCurrentItem(step);
                this.tv_confirm_register.setText("Confirm");
                this.tv_confirm_register.startAnimation(setAnimationOnNext());
                break;
        }
    }

    private void showCloseAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Do you want to cancel registration process? ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        RegisterActivity.this.finish();
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

    private void showConfirmAlertDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Create account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        createAccountStepThreeCallback.onCreateAccountStepThreeGetData();
                        //RegisterActivity.this.finish();
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

    private Animation setAnimationOnNext() {
        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(500);
        in.setInterpolator(new AccelerateDecelerateInterpolator());

        return in;
    }

    private Drawable changeBackCloseColor() {
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.icon_cancel)).getBitmap();
        Drawable upArrow = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 24, 24, true));
        upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    private void setNavigationBarTitle(String text, int registerStep) {
        ((AppCompatTextView)getSupportActionBar().getCustomView().findViewById(R.id.tv_title_custombar)).setText(text);
        if (registerStep == 2)
            ((AppCompatTextView)getSupportActionBar().getCustomView().findViewById(R.id.tv_title_custombar)).startAnimation(setAnimationOnNext());
    }

    public void showDialogAlertInfo() {
        this.dialogAlertInfo = new DialogAlertInfo(this);
        this.dialogAlertInfo.setCancelable(true);
        this.dialogAlertInfo.show(getSupportFragmentManager(), "dialogAlertInfo");
    }

    public void showDialogAlertAddress() {
        this.dialogAlertAddress = new DialogAlertAddress(this);
        this.dialogAlertAddress.setCancelable(true);
        this.dialogAlertAddress.show(getSupportFragmentManager(), "dialogAlertAddress");
    }

    public void showDialogAlertName() {
        this.dialogAlertName = new DialogAlertName(this);
        this.dialogAlertName.setCancelable(true);
        this.dialogAlertName.show(getSupportFragmentManager(), "dialogAlertName");
    }

    public void showDialogAlertDOB() {
        this.dialogAlertDOB = new DialogAlertDOB(this);
        this.dialogAlertDOB.setCancelable(true);
        this.dialogAlertDOB.show(getSupportFragmentManager(), "dialogAlertDOB");
    }

    public void resetRegisterStep(int step) {
        this.registerStep = step;
    }

    public static void setCreateAccountStepThreeCallback(CreateAccountStepThreeCallback listener) {
        createAccountStepThreeCallback = listener;
    }

    public static void setCreateAccountStepOneCallback(CreateAccountStepOneCallback listener) {
        createAccountStepOneCallback = listener;
    }

}
