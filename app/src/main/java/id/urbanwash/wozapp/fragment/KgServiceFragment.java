package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 4/10/16.
 */
@SuppressLint("ValidFragment")
public class KgServiceFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private AppCompatImageView iv_plus_kg_service, iv_minus_kg_service;

    public KgServiceFragment() {

    }

    public KgServiceFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_kg_service, container, false);

        this.iv_plus_kg_service = (AppCompatImageView) this.rootView.findViewById(R.id.iv_plus_kg_service);
        this.iv_minus_kg_service = (AppCompatImageView) this.rootView.findViewById(R.id.iv_minus_kg_service);

        this.iv_plus_kg_service.setOnClickListener(new IncreaseWeight());
        this.iv_minus_kg_service.setOnClickListener(new DecreaseWeight());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class IncreaseWeight implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    class DecreaseWeight implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
