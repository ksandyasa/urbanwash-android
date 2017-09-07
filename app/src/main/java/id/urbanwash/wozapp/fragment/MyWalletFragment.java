package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.MyWalletFragmentCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/30/16.
 */
@SuppressLint("ValidFragment")
public class MyWalletFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private AppCompatImageView iv_wallet_tab_content_pricing;
    private AppCompatEditText ed_voucher_tab_content_pricing;
    private MyWalletFragmentCallback myWalletFragmentCallback;

    public MyWalletFragment() {

    }

    public MyWalletFragment(AppCompatActivity aca, MyWalletFragmentCallback listener) {
        this.appCompatActivity = aca;
        this.myWalletFragmentCallback = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_mywallet, container, false);

        this.iv_wallet_tab_content_pricing = (AppCompatImageView) this.rootView.findViewById(R.id.iv_wallet_tab_content_pricing);
        this.ed_voucher_tab_content_pricing = (AppCompatEditText) this.rootView.findViewById(R.id.ed_voucher_tab_content_pricing);

        this.iv_wallet_tab_content_pricing.setOnClickListener(new ShowTopUpView());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class ShowTopUpView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myWalletFragmentCallback.showTopUpView();
        }
    }
}
