package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.StartOrderActivity;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/18/16.
 */
@SuppressLint("ValidFragment")
public class MyOrderFragment extends Fragment {
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private AppCompatTextView tv_create_myorder;
    private FragmentTabHost fragmentTabHost;

    // This is empty constructor. Do not delete it otherwise it will cause force close
    public MyOrderFragment() {

    }

    public MyOrderFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_myorder, container, false);

        this.fragmentTabHost = (FragmentTabHost) this.rootView.findViewById(android.R.id.tabhost);
        this.tv_create_myorder = (AppCompatTextView) this.rootView.findViewById(R.id.tv_create_myorder);

        this.fragmentTabHost.setup(this.appCompatActivity, getChildFragmentManager(), R.id.realtabcontent);
        
        this.fragmentTabHost.addTab(
                setIndicator(this.appCompatActivity, this.fragmentTabHost.newTabSpec("In Progress"), R.drawable.custom_tab_widget_backgroung),
                TabContentMyOrderFragment.class, null);
        this.fragmentTabHost.addTab(
                setIndicator(this.appCompatActivity, this.fragmentTabHost.newTabSpec("Completed"), R.drawable.custom_tab_widget_backgroung),
                TabContentMyOrderFragment1.class, null);

        this.tv_create_myorder.setOnClickListener(new GoToStartOrder());

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

    class GoToStartOrder implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent startOrderIntent = new Intent(appCompatActivity, StartOrderActivity.class);
            startActivity(startOrderIntent);
        }
    }

}
