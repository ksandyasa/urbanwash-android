package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.MyOrderAdapterCallback;

/**
 * Created by apridosandyasa on 3/18/16.
 */
public class MyOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AppCompatActivity appCompatActivity;
    private int viewMode;
    private MyOrderAdapterCallback myOrderAdapterCallback;

    public MyOrderAdapter(AppCompatActivity aca, int mode, MyOrderAdapterCallback listener) {
        this.appCompatActivity = aca;
        this.viewMode = mode;
        this.myOrderAdapterCallback = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myorder1, parent, false);
            MyOrderViewHolder1 movh1 = new MyOrderViewHolder1(view);
            return movh1;
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myorder, parent, false);
            MyOrderViewHolder movh = new MyOrderViewHolder(view);
            return movh;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.viewMode;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(MyOrderAdapter.class.getSimpleName(), "itemViewType " + holder.getItemViewType());
        if (holder.getItemViewType() == 0)
            setupMyOrderViewHolder1((MyOrderViewHolder1) holder);
        else
            setupMyOrderViewHolder((MyOrderViewHolder) holder);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class MyOrderViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_container_item_myorder;
        private AppCompatTextView tv_order_item_myorder, tv_title_content1_item_myorder,
                                  tv_value_content1_item_myorder, tv_title_content2_item_myorder,
                                  tv_value_content2_item_myorder, tv_title_content3_item_myorder,
                                  tv_value_content3_item_myorder, tv_title_content4_item_myorder,
                                  tv_value_content4_item_myorder;
        private AppCompatImageView iv_content2_item_myorder, iv_arrow_item_myorder;

        MyOrderViewHolder(View view) {
            super(view);
            this.rl_container_item_myorder = (RelativeLayout) view.findViewById(R.id.rl_container_item_myorder);
            this.tv_order_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_order_item_myorder);
            this.tv_title_content1_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_title_content1_item_myorder);
            this.tv_title_content2_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_title_content2_item_myorder);
            this.tv_title_content3_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_title_content3_item_myorder);
            this.tv_title_content4_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_title_content4_item_myorder);
            this.tv_value_content1_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_value_content1_item_myorder);
            this.tv_value_content2_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_value_content2_item_myorder);
            this.tv_value_content3_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_value_content3_item_myorder);
            this.tv_value_content4_item_myorder = (AppCompatTextView) view.findViewById(R.id.tv_value_content4_item_myorder);
            this.iv_content2_item_myorder = (AppCompatImageView) view.findViewById(R.id.iv_content2_item_myorder);
            this.iv_arrow_item_myorder = (AppCompatImageView) view.findViewById(R.id.iv_arrow_item_myorder);
        }
    }

    public static class MyOrderViewHolder1 extends RecyclerView.ViewHolder {
        private RelativeLayout rl_container_item_myorder1;
        private AppCompatTextView tv_order_item_myorder1, tv_title_content1_item_myorder1,
                tv_value_content1_item_myorder1, tv_title_content2_item_myorder1,
                tv_value_content2_item_myorder1, tv_title_content3_item_myorder1,
                tv_value_content3_item_myorder1, tv_title_content4_item_myorder1,
                tv_value_content4_item_myorder1, tv_title_content5_item_myorder1,
                tv_value_content5_item_myorder1;
        private AppCompatImageView iv_content3_item_myorder1, iv_arrow_item_myorder1;

        MyOrderViewHolder1(View view) {
            super(view);
            this.rl_container_item_myorder1 = (RelativeLayout) view.findViewById(R.id.rl_container_item_myorder1);
            this.tv_title_content1_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_title_content1_item_myorder1);
            this.tv_title_content2_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_title_content2_item_myorder1);
            this.tv_title_content3_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_title_content3_item_myorder1);
            this.tv_title_content4_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_title_content4_item_myorder1);
            this.tv_title_content5_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_title_content5_item_myorder1);
            this.tv_value_content1_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_value_content1_item_myorder1);
            this.tv_value_content2_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_value_content2_item_myorder1);
            this.tv_value_content3_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_value_content3_item_myorder1);
            this.tv_value_content4_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_value_content4_item_myorder1);
            this.tv_value_content5_item_myorder1 = (AppCompatTextView) view.findViewById(R.id.tv_value_content5_item_myorder1);
            this.iv_content3_item_myorder1 = (AppCompatImageView) view.findViewById(R.id.iv_content3_item_myorder1);
            this.iv_arrow_item_myorder1 = (AppCompatImageView) view.findViewById(R.id.iv_arrow_item_myorder1);
        }
    }

    private void setupMyOrderViewHolder(MyOrderViewHolder holder) {
        holder.rl_container_item_myorder.setOnClickListener(new ShowTrackingOrderView());
    }

    private void setupMyOrderViewHolder1(MyOrderViewHolder1 holder1) {
        holder1.rl_container_item_myorder1.setOnClickListener(new ShowTrackingOrderView());
    }

    class ShowTrackingOrderView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myOrderAdapterCallback.onShowTrackingOrderView();
        }
    }
}
