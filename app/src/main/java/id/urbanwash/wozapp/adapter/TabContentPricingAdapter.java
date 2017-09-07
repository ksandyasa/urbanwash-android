package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;

/**
 * Created by apridosandyasa on 3/30/16.
 */
public class TabContentPricingAdapter extends RecyclerView.Adapter<TabContentPricingAdapter.TabContentPricingViewHolder> {
    private AppCompatActivity appCompatActivity;

    public TabContentPricingAdapter(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public TabContentPricingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tab_content_pricing,
                parent, false);
        TabContentPricingViewHolder tcpvh = new TabContentPricingViewHolder(view);
        return tcpvh;
    }

    @Override
    public void onBindViewHolder(TabContentPricingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class TabContentPricingViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tv_title_item_tabcontent_pricing,
                                  tv_price_item_tabcontent_pricing;

        TabContentPricingViewHolder(View view) {
            super(view);
            this.tv_title_item_tabcontent_pricing = (AppCompatTextView) view.findViewById(R.id.tv_title_item_tabcontent_pricing);
            this.tv_price_item_tabcontent_pricing = (AppCompatTextView) view.findViewById(R.id.tv_price_item_tabcontent_pricing);
        }
    }
}
