package id.urbanwash.wozapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.urbanwash.wozapp.R;

/**
 * Created by apridosandyasa on 3/7/16.
 */
public class DetailServiceAdapter extends RecyclerView.Adapter<DetailServiceAdapter.DetailServiceViewHolder> {

    public DetailServiceAdapter() {

    }

    @Override
    public DetailServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_service,
                parent, false);
        DetailServiceViewHolder dsvh = new DetailServiceViewHolder(itemView);
        return dsvh;
    }

    @Override
    public void onBindViewHolder(DetailServiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class DetailServiceViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_quantity_detail_service;
        private TextView tv_price_title_detail_service;
        private TextView tv_price_value_detail_service;

        DetailServiceViewHolder(View itemView) {
            super(itemView);
            this.tv_quantity_detail_service = (TextView) itemView.findViewById(R.id.tv_quantity_detail_service);
            this.tv_price_title_detail_service = (TextView) itemView.findViewById(R.id.tv_price_title_detail_service);
            this.tv_price_value_detail_service = (TextView) itemView.findViewById(R.id.tv_price_value_detail_service);
        }
    }
}
