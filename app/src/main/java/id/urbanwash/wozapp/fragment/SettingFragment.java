package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.SettingAdapter;
import id.urbanwash.wozapp.callback.SettingAdapterCallback;
import id.urbanwash.wozapp.callback.SettingFragmentCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/28/16.
 */
@SuppressLint("ValidFragment")
public class SettingFragment extends Fragment implements SettingAdapterCallback {
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private RecyclerView rv_setting;
    private LinearLayoutManager linearLayoutManager;
    private SettingAdapter rv_setting_adapter;
    private SettingFragmentCallback settingFragmentCallback;

    public SettingFragment() {

    }

    public SettingFragment(AppCompatActivity aca, SettingFragmentCallback listener) {
        this.appCompatActivity = aca;
        this.settingFragmentCallback = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_setting, container, false);

        this.rv_setting = (RecyclerView) this.rootView.findViewById(R.id.rv_setting);
        this.linearLayoutManager = new LinearLayoutManager(this.appCompatActivity);
        this.rv_setting.setHasFixedSize(true);
        this.rv_setting.setLayoutManager(this.linearLayoutManager);

        this.rv_setting_adapter = new SettingAdapter(this.appCompatActivity,
                this.appCompatActivity.getResources().getStringArray(R.array.setting_content_items), this);
        this.rv_setting.setAdapter(this.rv_setting_adapter);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    @Override
    public void LogoutFromAccount() {
        this.settingFragmentCallback.ShowLoginView();
    }

    @Override
    public void ShowProfileFromSetting() {
        this.settingFragmentCallback.ShowProfileView();
    }

    @Override
    public void ShowFAQViewFromSetting() {

    }

    @Override
    public void ShowAbousUsFromSetting() {

    }

    @Override
    public void ShowPrivacyPolicyFromSetting() {

    }

    @Override
    public void ShowTermsOfServiceFromSetting() {

    }

    @Override
    public void ShowPromoFromSetting() {

    }

    @Override
    public void ShowChangePasswordFromSetting() {

    }
}
