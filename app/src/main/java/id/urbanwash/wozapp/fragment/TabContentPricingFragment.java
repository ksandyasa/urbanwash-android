package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.TabContentPricingAdapter;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/30/16.
 */
@SuppressLint("ValidFragment")
public class TabContentPricingFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private AppCompatTextView tv_regular_tab_content_pricing, tv_express_tab_content_pricing,
                              tv_deluxe_tab_content_pricing;
    private RecyclerView rv_tab_content_pricing;
    private LinearLayoutManager linearLayoutManager;
    private TabContentPricingAdapter rv_tab_content_pricing_adapter;

    public TabContentPricingFragment() {

    }

    public TabContentPricingFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_tab_content_pricing, container, false);

        this.tv_regular_tab_content_pricing = (AppCompatTextView) this.rootView.findViewById(R.id.tv_regular_tab_content_pricing);
        this.tv_express_tab_content_pricing = (AppCompatTextView) this.rootView.findViewById(R.id.tv_express_tab_content_pricing);
        this.tv_deluxe_tab_content_pricing = (AppCompatTextView) this.rootView.findViewById(R.id.tv_deluxe_tab_content_pricing);
        this.rv_tab_content_pricing = (RecyclerView) this.rootView.findViewById(R.id.rv_tab_content_pricing);

        this.linearLayoutManager = new LinearLayoutManager(this.appCompatActivity);
        this.rv_tab_content_pricing.setHasFixedSize(true);
        this.rv_tab_content_pricing.setLayoutManager(this.linearLayoutManager);

        this.rv_tab_content_pricing_adapter = new TabContentPricingAdapter(this.appCompatActivity);
        this.rv_tab_content_pricing.setAdapter(this.rv_tab_content_pricing_adapter);

        this.tv_regular_tab_content_pricing.setOnClickListener(new SetActiveRegularList());
        this.tv_express_tab_content_pricing.setOnClickListener(new SetActiveExpressList());
        this.tv_deluxe_tab_content_pricing.setOnClickListener(new SetActiveDeluxeList());

        SetActiveTabContentPricingList("Regular");

        return this.rootView;
    }

    class SetActiveRegularList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            SetActiveTabContentPricingList("Regular");
        }
    }

    class SetActiveExpressList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            SetActiveTabContentPricingList("Express");
        }
    }

    class SetActiveDeluxeList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            SetActiveTabContentPricingList("Deluxe");
        }
    }

    private void SetActiveTabContentPricingList(String service) {
        switch (service) {
            case "Regular":
                this.tv_regular_tab_content_pricing.setBackgroundResource(R.drawable.dialog_edit_date_button_normal);
                this.tv_express_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_deluxe_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_regular_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                this.tv_express_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                this.tv_deluxe_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                break;

            case "Express":
                this.tv_regular_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_express_tab_content_pricing.setBackgroundResource(R.drawable.dialog_edit_date_button_normal);
                this.tv_deluxe_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_regular_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                this.tv_express_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                this.tv_deluxe_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                break;

            case "Deluxe":
                this.tv_regular_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_express_tab_content_pricing.setBackgroundColor(Color.WHITE);
                this.tv_deluxe_tab_content_pricing.setBackgroundResource(R.drawable.dialog_edit_date_button_normal);
                this.tv_regular_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                this.tv_express_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorCustomGreen));
                this.tv_deluxe_tab_content_pricing.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                break;
        }
    }
}
