package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.StartOrderActivity;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/18/16.
 */
@SuppressLint("ValidFragment")
public class CreateOrderFragment extends Fragment{
    private View rootView;
    private AppCompatActivity appCompatActivity;
    private LinearLayout ll_plus_startorder;

    // This is empty constructor. Do not delete it otherwise it will cause force close
    public CreateOrderFragment() {

    }

    public CreateOrderFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_createorder, container, false);

        this.ll_plus_startorder = (LinearLayout) this.rootView.findViewById(R.id.ll_plus_startorder);

        this.ll_plus_startorder.setOnClickListener(new GoToStartOrder());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class GoToStartOrder implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent startOrderIntent = new Intent(appCompatActivity, StartOrderActivity.class);
            appCompatActivity.startActivity(startOrderIntent);
        }
    }
}
