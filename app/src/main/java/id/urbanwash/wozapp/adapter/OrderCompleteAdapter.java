package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;

/**
 * Created by apridosandyasa on 4/11/16.
 */
public class OrderCompleteAdapter extends RecyclerView.Adapter<OrderCompleteAdapter.OrderCompleteViewHolder> {
    private AppCompatActivity appCompatActivity;
    private String[] titles = {"Skirt", "T-Shirt", "Jeans", "Jacket"};
    private String[] values = {"x 2", "x 2", "x 2", "x 1"};

    public OrderCompleteAdapter(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public OrderCompleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_complete,
                parent, false);
        OrderCompleteViewHolder orderCompleteViewHolder = new OrderCompleteViewHolder(view);
        return orderCompleteViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderCompleteViewHolder holder, int position) {
        holder.tv_title_item_order_complete.setText(this.titles[position]);
        holder.tv_value_item_order_complete.setText(this.values[position]);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class OrderCompleteViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tv_title_item_order_complete;
        private AppCompatTextView tv_value_item_order_complete;

        OrderCompleteViewHolder(View view) {
            super(view);
            this.tv_title_item_order_complete = (AppCompatTextView) view.findViewById(R.id.tv_title_item_order_complete);
            this.tv_value_item_order_complete = (AppCompatTextView) view.findViewById(R.id.tv_value_item_order_complete);
        }
    }
}
