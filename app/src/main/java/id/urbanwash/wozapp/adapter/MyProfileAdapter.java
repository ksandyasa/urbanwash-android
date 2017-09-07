package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.urbanwash.wozapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apridosandyasa on 3/29/16.
 */
public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AppCompatActivity appCompatActivity;

    public MyProfileAdapter(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myprofile1, parent, false);
            MyProfileViewHolder1 mpvh1 = new MyProfileViewHolder1(view);
            return mpvh1;
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_myprofile2, parent, false);
            MyProfileViewHolder2 mpvh2 = new MyProfileViewHolder2(view);
            return mpvh2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(MyProfileAdapter.class.getSimpleName(), "" + holder.getItemViewType());
        if (position == 0) {
            MyProfileViewHolder1 myProfileViewHolder1 = (MyProfileViewHolder1) holder;
            setupViewHolder1(myProfileViewHolder1, position);
        }else {
            MyProfileViewHolder2 myProfileViewHolder2 = (MyProfileViewHolder2) holder;
            setupViewHolder2(myProfileViewHolder2, position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? 0 : 1;
    }

    private void setupViewHolder2(MyProfileViewHolder2 holder, int position) {
        switch (position) {
            case 1:
                holder.iv_icon_item_myprofile2.setImageResource(R.drawable.icon_home);
                holder.tv_top_item_myprofile2.setVisibility(View.VISIBLE);
                holder.tv_title_item_myprofile2.setText("Home");
                holder.tv_subtitle_item_myprofile2.setText("102 Jalan Metro Pondok Indah, No. 6");
                break;

            case 2:
                holder.iv_icon_item_myprofile2.setImageResource(R.drawable.icon_apartement);
                holder.tv_top_item_myprofile2.setVisibility(View.GONE);
                holder.tv_title_item_myprofile2.setText("Apartement");
                holder.tv_subtitle_item_myprofile2.setText("E Jalan Kompleks Polri, Apartemen Tamansari Semanggi Tower A Lt 19/10");
                break;
        }
    }

    private void setupViewHolder1(MyProfileViewHolder1 holder, int position) {
        if (position == 0) {
            holder.iv_profile_item_myprofile1.setImageResource(R.drawable.iv_profile_default);
            holder.tv_name_item_myprofile1.setText("Rado Ardian");
            holder.tv_email_item_myprofile1.setText("rado.yanu@gmail.com");
            holder.tv_phone_item_myprofile1.setText("+62 8111014433");
        }
    }

    static class MyProfileViewHolder1 extends RecyclerView.ViewHolder {
        private CircleImageView iv_profile_item_myprofile1;
        private AppCompatTextView tv_name_item_myprofile1,
                                  tv_email_item_myprofile1,
                                  tv_phone_item_myprofile1,
                                  tv_edit_item_myprofile1;
        private AppCompatImageView iv_edit_item_myprofile1;

        MyProfileViewHolder1(View view) {
            super(view);
            this.iv_profile_item_myprofile1 = (CircleImageView) view.findViewById(R.id.iv_profile_item_myprofile1);
            this.tv_name_item_myprofile1 = (AppCompatTextView) view.findViewById(R.id.tv_name_item_myprofile1);
            this.tv_email_item_myprofile1 = (AppCompatTextView) view.findViewById(R.id.tv_email_item_myprofile1);
            this.tv_edit_item_myprofile1 = (AppCompatTextView) view.findViewById(R.id.tv_edit_item_myprofile1);
            this.tv_phone_item_myprofile1 = (AppCompatTextView) view.findViewById(R.id.tv_phone_item_myprofile1);
            this.iv_edit_item_myprofile1 = (AppCompatImageView) view.findViewById(R.id.iv_edit_item_myprofile1);
        }
    }

    static class MyProfileViewHolder2 extends RecyclerView.ViewHolder {
        private AppCompatTextView tv_top_item_myprofile2,
                                  tv_title_item_myprofile2,
                                  tv_subtitle_item_myprofile2;
        private AppCompatImageView iv_icon_item_myprofile2,
                                   iv_edit_item_myprofile2,
                                   iv_close_item_myprofile2;

        MyProfileViewHolder2(View view) {
            super(view);
            this.tv_top_item_myprofile2 = (AppCompatTextView) view.findViewById(R.id.tv_top_item_myprofile2);
            this.tv_title_item_myprofile2 = (AppCompatTextView) view.findViewById(R.id.tv_title_item_myprofile2);
            this.tv_subtitle_item_myprofile2 = (AppCompatTextView) view.findViewById(R.id.tv_subtitle_item_myprofile2);
            this.iv_icon_item_myprofile2 = (AppCompatImageView) view.findViewById(R.id.iv_icon_item_myprofile2);
            this.iv_edit_item_myprofile2 = (AppCompatImageView) view.findViewById(R.id.iv_edit_item_myprofile2);
            this.iv_close_item_myprofile2 = (AppCompatImageView) view.findViewById(R.id.iv_close_item_myprofile2);
        }
    }
}
