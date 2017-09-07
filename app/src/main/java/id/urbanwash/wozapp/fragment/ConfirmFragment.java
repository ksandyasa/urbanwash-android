package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;
import id.urbanwash.wozapp.widget.DialogEditDate;

/**
 * Created by apridosandyasa on 3/8/16.
 */
@SuppressLint("ValidFragment")
public class ConfirmFragment extends Fragment {
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private RelativeLayout rl_content6_order_summary;
    private AppCompatImageView iv_switch_content5_order_summary;
    private AppCompatImageView iv_edit_return_order_summary;
    private DialogEditDate dialogEditDate;

    // This is empty constructor. Do not delete it otherwise it will cause force close
    public ConfirmFragment() {

    }

    public ConfirmFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_order_summary, container, false);

        this.rl_content6_order_summary = (RelativeLayout) this.rootView.findViewById(R.id.rl_content6_order_summary);
        this.iv_switch_content5_order_summary = (AppCompatImageView) this.rootView.findViewById(R.id.iv_switch_content5_order_summary);
        this.iv_edit_return_order_summary = (AppCompatImageView) this.rootView.findViewById(R.id.iv_edit_return_order_summary);

        this.iv_switch_content5_order_summary.setOnClickListener(new ShowContent6Layout());
        this.iv_edit_return_order_summary.setOnClickListener(new ShowDialogEditDate());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class ShowContent6Layout implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(ConfirmFragment.class.getSimpleName(), "layout clicked");
            if (rl_content6_order_summary.isShown()) {
                rl_content6_order_summary.setVisibility(View.INVISIBLE);
                iv_switch_content5_order_summary.setImageResource(R.drawable.iv_payment_cash_order_summary);
            }else {
                rl_content6_order_summary.setVisibility(View.VISIBLE);
                iv_switch_content5_order_summary.setImageResource(R.drawable.iv_payment_wallet_order_summary);
            }
        }
    }

    class ShowDialogEditDate implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dialogEditDate = new DialogEditDate(appCompatActivity);
            dialogEditDate.setCancelable(false);
            dialogEditDate.show(appCompatActivity.getSupportFragmentManager(), "dialogEditDate");
        }
    }
}
