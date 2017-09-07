package id.urbanwash.wozapp.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.SearchableAddressAdapterCallback;
import id.urbanwash.wozapp.model.SearchAddress;

/**
 * Created by apridosandyasa on 4/24/16.
 */
public class SearchableAddressAdapter extends RecyclerView.Adapter<SearchableAddressAdapter.SearchableAddressViewHolder> {
    private AppCompatActivity appCompatActivity;
    private List<SearchAddress> resultList;
    private SearchableAddressAdapterCallback searchableAddressAdapterCallback;

    public SearchableAddressAdapter(AppCompatActivity aca, List<SearchAddress> objects, SearchableAddressAdapterCallback listener) {
        this.appCompatActivity = aca;
        this.resultList = objects;
        this.searchableAddressAdapterCallback = listener;
    }

    @Override
    public SearchableAddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_searchable, parent, false);
        SearchableAddressViewHolder searchableAddressViewHolder = new SearchableAddressViewHolder(view);
        return searchableAddressViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchableAddressViewHolder holder, int position) {
        holder.tv_item_searchable.setText(this.resultList.get(position).getAddress());
        if (this.resultList.get(position).getLatitude() != 0 || this.resultList.get(position).getLatitude() != 0)
            holder.ll_container_item_searchable.setOnClickListener(new ShowPinFromSearchAddress(position));
    }

    @Override
    public int getItemCount() {
        return this.resultList.size();
    }

    class ShowPinFromSearchAddress implements View.OnClickListener {
        private int mPos;

        ShowPinFromSearchAddress(int p) {
            this.mPos = p;
        }

        @Override
        public void onClick(View v) {
            searchableAddressAdapterCallback.onSearchableAddressAdapterCallback(resultList.get(mPos).getLatitude(),
                    resultList.get(mPos).getLongitude());
        }
    }

    public static class SearchableAddressViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_container_item_searchable;
        private AppCompatTextView tv_item_searchable;

        SearchableAddressViewHolder(View view) {
            super(view);
            this.ll_container_item_searchable = (LinearLayout) view.findViewById(R.id.ll_container_item_searchable);
            this.tv_item_searchable = (AppCompatTextView) view.findViewById(R.id.tv_item_searchable);
        }
    }
}
