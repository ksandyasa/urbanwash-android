package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;

/**
 * Created by apridosandyasa on 4/10/16.
 */
public class PieceServiceAdapter extends RecyclerView.Adapter<PieceServiceAdapter.PieceServiceViewHolder> {
    private AppCompatActivity appCompatActivity;
    private String[] titles = {"Celana Panjang", "Celana Pendek", "Kemeja", "Kaos", "Jaket", "Sprei"};
    private String[] prices = {"Rp. 20.000", "Rp. 20.000", "Rp. 5.000", "Rp. -", "Rp. 20.000", "Rp. 5.000"};
    private String[] quantitys = {"2", "2", "2", "0", "2", "2"};

    public PieceServiceAdapter(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public PieceServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_piece_service, parent, false);
        PieceServiceViewHolder pieceServiceViewHolder = new PieceServiceViewHolder(view);
        return pieceServiceViewHolder;
    }

    @Override
    public void onBindViewHolder(PieceServiceViewHolder holder, int position) {
        holder.tv_title_item_piece_service.setText(this.titles[position]);
        holder.tv_price_item_piece_service.setText(this.prices[position]);
        holder.tv_quantity_item_piece_service.setText(this.quantitys[position]);
        holder.iv_plus_item_piece_service.setOnClickListener(new IncreaseQuantity());
        holder.iv_minus_item_piece_service.setOnClickListener(new DecreaseQuantity());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class PieceServiceViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tv_title_item_piece_service, tv_price_item_piece_service,
                                  tv_quantity_item_piece_service;
        private AppCompatImageView iv_plus_item_piece_service, iv_minus_item_piece_service;

        PieceServiceViewHolder(View view) {
            super(view);
            this.tv_title_item_piece_service = (AppCompatTextView) view.findViewById(R.id.tv_title_item_piece_service);
            this.tv_price_item_piece_service = (AppCompatTextView) view.findViewById(R.id.tv_price_item_piece_service);
            this.tv_quantity_item_piece_service = (AppCompatTextView) view.findViewById(R.id.tv_quantity_item_piece_service);
            this.iv_plus_item_piece_service = (AppCompatImageView) view.findViewById(R.id.iv_plus_item_piece_service);
            this.iv_minus_item_piece_service = (AppCompatImageView) view.findViewById(R.id.iv_minus_item_piece_service);
        }
    }

    class IncreaseQuantity implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    class DecreaseQuantity implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
