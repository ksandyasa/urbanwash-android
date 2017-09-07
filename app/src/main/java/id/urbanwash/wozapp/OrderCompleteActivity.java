package id.urbanwash.wozapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import id.urbanwash.wozapp.adapter.OrderCompleteAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 4/11/16.
 */
public class OrderCompleteActivity extends AppCompatActivity {
    private LinearLayout ll_content6_order_complete;
    private AppCompatImageView iv_dropdown_content5_order_complete;
    private RecyclerView rv_content6_order_complete;
    private LinearLayoutManager linearLayoutManager;
    private OrderCompleteAdapter rv_content6_order_complete_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

        this.ll_content6_order_complete = (LinearLayout) findViewById(R.id.ll_content6_order_complete);
        this.iv_dropdown_content5_order_complete = (AppCompatImageView) findViewById(R.id.iv_dropdown_content5_order_complete);
        this.rv_content6_order_complete = (RecyclerView) findViewById(R.id.rv_content6_order_complete);

        this.linearLayoutManager = new LinearLayoutManager(this);
        this.rv_content6_order_complete.setHasFixedSize(true);
        this.rv_content6_order_complete.setLayoutManager(this.linearLayoutManager);

        this.rv_content6_order_complete_adapter = new OrderCompleteAdapter(this);
        this.rv_content6_order_complete.setAdapter(this.rv_content6_order_complete_adapter);

        this.iv_dropdown_content5_order_complete.setOnClickListener(new ShowContent6View());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    class ShowContent6View implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (ll_content6_order_complete.isShown()) {
                ll_content6_order_complete.setVisibility(View.GONE);
                iv_dropdown_content5_order_complete.setImageResource(R.drawable.icon_arrow_right);
            }else {
                ll_content6_order_complete.setVisibility(View.VISIBLE);
                iv_dropdown_content5_order_complete.setImageResource(R.drawable.icon_dropdown);
            }
        }
    }

}
