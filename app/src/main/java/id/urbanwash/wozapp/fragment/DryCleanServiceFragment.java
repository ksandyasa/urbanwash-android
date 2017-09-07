package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.PieceServiceAdapter;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 4/10/16.
 */
@SuppressLint("ValidFragment")
public class DryCleanServiceFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private RecyclerView rv_content2_dryclean_service;
    private LinearLayoutManager linearLayoutManager;
    private PieceServiceAdapter rv_content2_dryclean_service_adapter;

    public DryCleanServiceFragment() {

    }

    public DryCleanServiceFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_dryclean_service, container, false);

        this.rv_content2_dryclean_service = (RecyclerView) this.rootView.findViewById(R.id.rv_content2_dryclean_service);
        this.linearLayoutManager = new LinearLayoutManager(this.appCompatActivity);
        this.rv_content2_dryclean_service.setHasFixedSize(true);
        this.rv_content2_dryclean_service.setLayoutManager(this.linearLayoutManager);

        this.rv_content2_dryclean_service_adapter = new PieceServiceAdapter(this.appCompatActivity);
        this.rv_content2_dryclean_service.setAdapter(this.rv_content2_dryclean_service_adapter);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }
}
