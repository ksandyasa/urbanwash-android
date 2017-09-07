package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.MyProfileAdapter;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/29/16.
 */
@SuppressLint("ValidFragment")
public class MyProfileFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private RecyclerView rv_myprofile;
    private LinearLayoutManager linearLayoutManager;
    private MyProfileAdapter rv_myprofile_adapter;

    public MyProfileFragment() {

    }

    public MyProfileFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_myprofile, container, false);

        this.rv_myprofile = (RecyclerView) this.rootView.findViewById(R.id.rv_myprofile);
        this.linearLayoutManager = new LinearLayoutManager(this.appCompatActivity);
        this.rv_myprofile.setHasFixedSize(true);
        this.rv_myprofile.setLayoutManager(this.linearLayoutManager);

        this.rv_myprofile_adapter = new MyProfileAdapter(this.appCompatActivity);
        this.rv_myprofile.setAdapter(this.rv_myprofile_adapter);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }
}
