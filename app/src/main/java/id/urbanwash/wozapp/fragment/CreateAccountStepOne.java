package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.RegisterActivity;
import id.urbanwash.wozapp.adapter.CustomSpinnerAdapter;
import id.urbanwash.wozapp.callback.CreateAccountStepOneCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/28/16.
 */
@SuppressLint("ValidFragment")
public class CreateAccountStepOne extends Fragment implements CreateAccountStepOneCallback {
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private AppCompatSpinner sp_name_register;
    private AppCompatEditText ed_name_register;
    private AppCompatEditText ed_date_register;

    public CreateAccountStepOne() {

    }

    public CreateAccountStepOne(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_createaccount1, container, false);

        this.sp_name_register = (AppCompatSpinner) this.rootView.findViewById(R.id.sp_name_register);
        this.ed_name_register = (AppCompatEditText) this.rootView.findViewById(R.id.ed_name_register);
        this.ed_date_register = (AppCompatEditText) this.rootView.findViewById(R.id.ed_date_register);

        this.sp_name_register.setAdapter(new CustomSpinnerAdapter(this.appCompatActivity,
                this.appCompatActivity.getResources().getStringArray(R.array.title_name)));

        if (this.appCompatActivity instanceof RegisterActivity)
            RegisterActivity.setCreateAccountStepOneCallback(this);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    @Override
    public void onCreateAccountStepOneGetData() {
        if (this.ed_name_register.getText().toString().equals("")) {
            if (this.appCompatActivity instanceof RegisterActivity) {
                ((RegisterActivity) this.appCompatActivity).showDialogAlertName();
                ((RegisterActivity) this.appCompatActivity).resetRegisterStep(0);
            }
        }else if (this.ed_date_register.getText().toString().equals("")) {
            if (this.appCompatActivity instanceof RegisterActivity) {
                ((RegisterActivity) this.appCompatActivity).showDialogAlertDOB();
                ((RegisterActivity) this.appCompatActivity).resetRegisterStep(0);
            }
        }
    }
}
