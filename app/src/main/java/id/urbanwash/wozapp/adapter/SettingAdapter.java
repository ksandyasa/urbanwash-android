package id.urbanwash.wozapp.adapter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.SettingAdapterCallback;

/**
 * Created by apridosandyasa on 3/28/16.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private AppCompatActivity appCompatActivity;
    private String[] settingItems;
    private SettingAdapterCallback settingAdapterCallback;

    public SettingAdapter(AppCompatActivity aca, String[] objects, SettingAdapterCallback listener) {
        this.appCompatActivity = aca;
        this.settingItems = objects;
        this.settingAdapterCallback = listener;
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_setting, parent, false);
        SettingViewHolder svh = new SettingViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(SettingViewHolder holder, int position) {
        holder.tv_title_item_setting.setText(this.settingItems[position]);
        if (position < 7) {
            holder.tv_title_item_setting.setTextColor(Color.parseColor("#444749"));
            switch (position) {
                case 0:
                    holder.rl_container_item_setting.setOnClickListener(new ShowProfile());
                    break;

                case 1:
                    holder.rl_container_item_setting.setOnClickListener(new ShowChangePassword());
                    break;

                case 2:
                    holder.rl_container_item_setting.setOnClickListener(new ShowPromo());
                    break;

                case 3:
                    holder.rl_container_item_setting.setOnClickListener(new ShowTermsOfService());
                    break;

                case 4:
                    holder.rl_container_item_setting.setOnClickListener(new ShowPrivacyPolicy());
                    break;

                case 5:
                    holder.rl_container_item_setting.setOnClickListener(new ShowAboutUs());
                    break;

                case 6:
                    holder.rl_container_item_setting.setOnClickListener(new ShowFAQ());
                    break;
            }
        }else {
            holder.tv_title_item_setting.setTextColor(Color.parseColor("#03a994"));
            holder.rl_container_item_setting.setOnClickListener(new LogOut());
        }
    }

    @Override
    public int getItemCount() {
        return this.settingItems.length;
    }

    class LogOut implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.LogoutFromAccount();
        }
    }

    class ShowProfile implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowProfileFromSetting();
        }
    }

    class ShowChangePassword implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowChangePasswordFromSetting();
        }
    }

    class ShowPromo implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowPromoFromSetting();
        }
    }

    class ShowTermsOfService implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowTermsOfServiceFromSetting();
        }
    }

    class ShowPrivacyPolicy implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowPrivacyPolicyFromSetting();
        }
    }

    class ShowAboutUs implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowAbousUsFromSetting();
        }
    }

    class ShowFAQ implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            settingAdapterCallback.ShowFAQViewFromSetting();
        }
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_container_item_setting;
        private AppCompatTextView tv_title_item_setting;
        private AppCompatImageView iv_icon_item_setting;

        SettingViewHolder(View view) {
            super(view);
            this.rl_container_item_setting = (RelativeLayout) view.findViewById(R.id.rl_container_item_setting);
            this.tv_title_item_setting = (AppCompatTextView) view.findViewById(R.id.tv_title_item_setting);
            this.iv_icon_item_setting = (AppCompatImageView) view.findViewById(R.id.iv_icon_item_setting);
        }
    }
}
