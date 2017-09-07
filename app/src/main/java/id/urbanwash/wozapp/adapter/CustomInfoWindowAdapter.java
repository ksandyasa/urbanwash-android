package id.urbanwash.wozapp.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 3/30/16.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private AppCompatActivity appCompatActivity;
    private View contentView;
    private RelativeLayout rl_title_info_window;
    private LinearLayout ll_snippet_info_window;
    private AppCompatTextView tv_title_info_window,
                              tv_snippet_info_window;

    public CustomInfoWindowAdapter(AppCompatActivity aca) {
        this.appCompatActivity = aca;
        this.contentView = LayoutInflater.from(this.appCompatActivity).inflate(R.layout.custom_info_window, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        this.rl_title_info_window = (RelativeLayout) this.contentView.findViewById(R.id.rl_title_info_window);
        this.ll_snippet_info_window = (LinearLayout) this.contentView.findViewById(R.id.ll_snippet_info_window);

        this.tv_title_info_window = (AppCompatTextView) this.contentView.findViewById(R.id.tv_title_info_window);
        this.tv_snippet_info_window = (AppCompatTextView) this.contentView.findViewById(R.id.tv_snippet_info_window);

        this.tv_title_info_window.setText(marker.getTitle());
        this.tv_snippet_info_window.setText(marker.getSnippet());

        setRelativeLayoutWidthHeight(this.rl_title_info_window);
        setLinearLayoutWidthHeight(this.ll_snippet_info_window);

        return this.contentView;
    }

    private void setRelativeLayoutWidthHeight(RelativeLayout relativeLayout) {
        DisplayMetrics dm = Utility.DisplayUtilty.getDisplayMetricFromWindow(this.appCompatActivity);
        int w = (int) (dm.widthPixels * 0.75);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(w,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        50,
                        dm)));
    }

    private void setLinearLayoutWidthHeight(LinearLayout linearLayout) {
        DisplayMetrics dm = Utility.DisplayUtilty.getDisplayMetricFromWindow(this.appCompatActivity);
        int w = (int) (dm.widthPixels * 0.75);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(w, LinearLayout.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
