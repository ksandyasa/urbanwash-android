package id.urbanwash.wozapp.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import id.urbanwash.wozapp.LoginActivity;
import id.urbanwash.wozapp.MainActivity;
import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.MainAdapterCallback;
import id.urbanwash.wozapp.callback.MyWalletFragmentCallback;
import id.urbanwash.wozapp.callback.SettingFragmentCallback;
import id.urbanwash.wozapp.callback.TopUpFragmentCallback;
import id.urbanwash.wozapp.fragment.CreateOrderFragment;
import id.urbanwash.wozapp.fragment.MyOrderFragment;
import id.urbanwash.wozapp.fragment.MyProfileFragment;
import id.urbanwash.wozapp.fragment.MyWalletFragment;
import id.urbanwash.wozapp.fragment.PricingFragment;
import id.urbanwash.wozapp.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.urbanwash.wozapp.fragment.TopUpFragment;

/**
 * Created by apridosandyasa on 3/18/16.
 */
public class MainAdapter extends FragmentStatePagerAdapter implements MyWalletFragmentCallback,
        TopUpFragmentCallback, SettingFragmentCallback {
    private AppCompatActivity appCompatActivity;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private MainAdapterCallback mainAdapterCallback;
    private int[] imagesResID = { R.drawable.icon_plus,
                                  R.drawable.icon_order,
                                  R.drawable.icon_pricing,
                                  R.drawable.icon_wallet,
                                  R.drawable.icon_setting,
                                  R.drawable.iv_profile_default};

    public MainAdapter(FragmentManager fm, AppCompatActivity aca, MainAdapterCallback listener) {
        super(fm);
        this.fragmentManager = fm;
        this.appCompatActivity = aca;
        this.mainAdapterCallback = listener;
        initFragmentListData(this.appCompatActivity);
    }

    @Override
    public Fragment getItem(int position) {
        return (position != 3) ? this.fragmentList.get(position) : this.fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof MyWalletFragment && fragment instanceof TopUpFragment)
            return POSITION_NONE;
        if (object instanceof TopUpFragment && fragment instanceof MyWalletFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this.appCompatActivity).inflate(R.layout.custom_tab_layout, null);
        AppCompatImageView img = (AppCompatImageView) view.findViewById(R.id.iv_icon_tablayout);
        CircleImageView circleImg = (CircleImageView) view.findViewById(R.id.iv_circle_tablayout);
        img.setImageResource(imagesResID[position]);
        circleImg.setImageResource(imagesResID[5]);
        if (position < 5) {
            circleImg.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
        }else {
            img.setVisibility(View.GONE);
            circleImg.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void setActiveImageTabView(int position) {

    }

    private void initFragmentListData(AppCompatActivity aca) {
        this.fragment = new MyWalletFragment(aca, this);
        this.fragmentList = new ArrayList<>();
        this.fragmentList.add(new CreateOrderFragment(aca));
        this.fragmentList.add(new MyOrderFragment(aca));
        this.fragmentList.add(new PricingFragment(aca));
        this.fragmentList.add(new MyWalletFragment(aca, this));
        this.fragmentList.add(new SettingFragment(aca, this));
        this.fragmentList.add(new MyProfileFragment(aca));
    }

    @Override
    public void showTopUpView() {
        Log.d(MainAdapter.class.getSimpleName(), "showTopUpView");
        this.fragmentManager.beginTransaction().remove(this.fragment);
        fragment = new TopUpFragment(this.appCompatActivity, this);
        notifyDataSetChanged();
    }

    @Override
    public void showMyWalletView() {
        Log.d(MainAdapter.class.getSimpleName(), "showMyWalletView");
        this.fragmentManager.beginTransaction().remove(this.fragment);
        fragment = new MyWalletFragment(this.appCompatActivity, this);
        notifyDataSetChanged();
    }

    @Override
    public void ShowLoginView() {
        Intent loginIntent = new Intent(this.appCompatActivity, LoginActivity.class);
        this.appCompatActivity.startActivity(loginIntent);
        if (this.appCompatActivity instanceof MainActivity)
            ((MainActivity) this.appCompatActivity).finish();
    }

    @Override
    public void ShowProfileView() {
        this.mainAdapterCallback.ShowProfileFromMainView();
    }
}
