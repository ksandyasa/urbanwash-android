package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.TopUpFragmentCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 4/9/16.
 */
@SuppressLint("ValidFragment")
public class TopUpFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private TopUpFragmentCallback topUpFragmentCallback;

    public TopUpFragment() {

    }

    public TopUpFragment(AppCompatActivity aca, TopUpFragmentCallback listener) {
        this.appCompatActivity = aca;
        this.topUpFragmentCallback = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_topup, container, false);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new ShowMyWalletView());
    }

    class ShowMyWalletView implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                // handle back button's click listener
                topUpFragmentCallback.showMyWalletView();

                return true;
            }

            return false;
        }
    }
}
