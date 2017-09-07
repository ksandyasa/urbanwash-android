package id.urbanwash.wozapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.view.MenuItem;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.adapter.MainAdapter;

import id.urbanwash.wozapp.callback.MainAdapterCallback;
import id.urbanwash.wozapp.widget.DepthPageTransformer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements MainAdapterCallback {
    private TabLayout tabs_main;
    private ViewPager vp_main;
    private MainAdapter vp_main_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tabs_main = (TabLayout) findViewById(R.id.tabs_main);
        this.vp_main = (ViewPager) findViewById(R.id.vp_main);
        this.vp_main_adapter = new MainAdapter(getSupportFragmentManager(), this, this);
        this.vp_main.setAdapter(this.vp_main_adapter);
        this.vp_main.setPageTransformer(true, new DepthPageTransformer());
        this.tabs_main.setupWithViewPager(this.vp_main);

        for (int i = 0;i < tabs_main.getTabCount(); i++) {
            setIconTabLayoutBasedOnIndeks(i);
        }

        ((AppCompatImageView) this.tabs_main.getTabAt(0).getCustomView().findViewById(R.id.iv_icon_tablayout)).setColorFilter(getResources().getColor(R.color.colorCustomGreen), PorterDuff.Mode.SRC_ATOP);
        this.tabs_main.setOnTabSelectedListener(new SetActiveTabIcon());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setIconTabLayoutBasedOnIndeks(int indeks) {
        TabLayout.Tab tab = this.tabs_main.getTabAt(indeks);
        if(tab != null) {
            tab.setCustomView(this.vp_main_adapter.getTabView(indeks));
            ((AppCompatImageView) tab.getCustomView().findViewById(R.id.iv_icon_tablayout)).setColorFilter(getResources().getColor(R.color.colorCustomLightGrey), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshMainAdapter() {
        this.vp_main_adapter.getItem(3);
        this.vp_main_adapter.notifyDataSetChanged();
    }

    @Override
    public void ShowProfileFromMainView() {
        this.vp_main.setCurrentItem(5, true);
    }

    class SetActiveTabIcon implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            vp_main.setCurrentItem(tab.getPosition(), true);
            ((AppCompatImageView) tab.getCustomView().findViewById(R.id.iv_icon_tablayout)).setColorFilter(getResources().getColor(R.color.colorCustomGreen), PorterDuff.Mode.SRC_ATOP);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            ((AppCompatImageView) tab.getCustomView().findViewById(R.id.iv_icon_tablayout)).setColorFilter(getResources().getColor(R.color.colorCustomLightGrey), PorterDuff.Mode.SRC_ATOP);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
