package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.ServicesWozappActivity;
import id.urbanwash.wozapp.callback.ServiceFragmentCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/7/16.
 */
@SuppressLint("ValidFragment")
public class ServiceFragment extends Fragment {
    private View rootView;
    private AppCompatImageView iv_piece_service;
    private AppCompatImageView iv_kg_service;
    private AppCompatImageView iv_dryclean_service;
    private AppCompatActivity appCompatActivity;
    private ServiceFragmentCallback serviceFragmentCallback;

    // This is empty constructor. Do not delete it otherwise it will cause force close
    public ServiceFragment() {

    }

    public ServiceFragment(AppCompatActivity aca, ServiceFragmentCallback listener) {
        this.appCompatActivity = aca;
        this.serviceFragmentCallback = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_service, container, false);

        this.iv_piece_service = (AppCompatImageView) this.rootView.findViewById(R.id.iv_piece_service);
        this.iv_kg_service = (AppCompatImageView) this.rootView.findViewById(R.id.iv_kg_service);
        this.iv_dryclean_service = (AppCompatImageView) this.rootView.findViewById(R.id.iv_dryclean_service);

        this.iv_piece_service.setOnClickListener(new ShowPieceServiceView());
        this.iv_kg_service.setOnClickListener(new ShowKgServiceView());
        this.iv_dryclean_service.setOnClickListener(new ShowDryCleanServiceView());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class ShowPieceServiceView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent pieceViewIntent = new Intent(appCompatActivity, ServicesWozappActivity.class);
            pieceViewIntent.putExtra("services", "PieceService");
            appCompatActivity.startActivity(pieceViewIntent);
        }
    }

    class ShowKgServiceView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent kgViewIntent = new Intent(appCompatActivity, ServicesWozappActivity.class);
            kgViewIntent.putExtra("services", "KgService");
            appCompatActivity.startActivity(kgViewIntent);
        }
    }

    class ShowDryCleanServiceView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent dryCleanViewIntent = new Intent(appCompatActivity, ServicesWozappActivity.class);
            dryCleanViewIntent.putExtra("services", "DryCleanService");
            appCompatActivity.startActivity(dryCleanViewIntent);
        }
    }
}
