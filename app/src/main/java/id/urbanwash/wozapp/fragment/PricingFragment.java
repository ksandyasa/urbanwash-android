package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/30/16.
 */
@SuppressLint("ValidFragment")
public class PricingFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private FragmentTabHost fragmentTabHost;

    public PricingFragment() {

    }

    public PricingFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_pricing, container, false);

        this.fragmentTabHost = (FragmentTabHost) this.rootView.findViewById(android.R.id.tabhost);
        this.fragmentTabHost.setup(this.appCompatActivity, getChildFragmentManager(), R.id.realtabcontent);

        this.fragmentTabHost.addTab(
                setIndicator(this.appCompatActivity, this.fragmentTabHost.newTabSpec("Kg/s Laundry"), R.drawable.custom_tab_widget_backgroung),
                TabContentPricingFragment.class, null);
        this.fragmentTabHost.addTab(
                setIndicator(this.appCompatActivity, this.fragmentTabHost.newTabSpec("Pc/s Laundry"), R.drawable.custom_tab_widget_backgroung),
                TabContentPricingFragment.class, null);
        this.fragmentTabHost.addTab(
                setIndicator(this.appCompatActivity, this.fragmentTabHost.newTabSpec("Dry Clean"), R.drawable.custom_tab_widget_backgroung),
                TabContentPricingFragment.class, null);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    public TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec, int resid) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(ctx).inflate(R.layout.custom_tab_widget, null);
        v.setBackgroundResource(resid);
        TextView text = (TextView) v.findViewById(R.id.tv_title_tab_widget);
        text.setText(spec.getTag());
        return spec.setIndicator(v);
    }
}
