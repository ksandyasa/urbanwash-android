package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import id.urbanwash.wozapp.TrackingOrderActivity;
import id.urbanwash.wozapp.adapter.MyOrderAdapter;
import id.urbanwash.wozapp.callback.MyOrderAdapterCallback;
import id.urbanwash.wozapp.util.Utility;
import id.urbanwash.wozapp.widget.RecycleDividerItemDecoration;

/**
 * Created by apridosandyasa on 4/11/16.
 */
@SuppressLint("ValidFragment")
public class TabContentMyOrderFragment extends Fragment implements MyOrderAdapterCallback {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private RecyclerView rv_tab_content_myorder;
    private LinearLayoutManager linearLayoutManager;
    private MyOrderAdapter rv_tab_content_myorder_adapter;

    public TabContentMyOrderFragment() {

    }

    public TabContentMyOrderFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_tab_content_myorder, container, false);

        this.rv_tab_content_myorder = (RecyclerView) this.rootView.findViewById(R.id.rv_tab_content_myorder);
        this.linearLayoutManager = new LinearLayoutManager(this.appCompatActivity);
        this.rv_tab_content_myorder.setHasFixedSize(true);
        this.rv_tab_content_myorder.setLayoutManager(this.linearLayoutManager);
        this.rv_tab_content_myorder.addItemDecoration(new RecycleDividerItemDecoration(getActivity()));

        this.rv_tab_content_myorder_adapter = new MyOrderAdapter(this.appCompatActivity, 0, this);
        this.rv_tab_content_myorder.setAdapter(this.rv_tab_content_myorder_adapter);

        return this.rootView;
    }

    @Override
    public void onShowTrackingOrderView() {
        Intent trackingOrderIntent = new Intent(getActivity(), TrackingOrderActivity.class);
        getActivity().startActivity(trackingOrderIntent);
    }
}
