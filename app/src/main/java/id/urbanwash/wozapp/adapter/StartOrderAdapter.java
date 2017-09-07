package id.urbanwash.wozapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import id.urbanwash.wozapp.callback.ServiceFragmentCallback;
import id.urbanwash.wozapp.fragment.ConfirmFragment;
import id.urbanwash.wozapp.fragment.PieceServiceFragment;
import id.urbanwash.wozapp.fragment.SchedulePickupFragment;
import id.urbanwash.wozapp.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apridosandyasa on 3/7/16.
 */
public class StartOrderAdapter extends FragmentStatePagerAdapter implements ServiceFragmentCallback {
    private Fragment fragment1;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private AppCompatActivity appCompatActivity;

    public StartOrderAdapter(FragmentManager fm, AppCompatActivity aca) {
        super(fm);
        this.fragmentManager = fm;
        this.appCompatActivity = aca;
        initFragmentListData(this.appCompatActivity);
    }

    @Override
    public Fragment getItem(int position) {
        return (position != 1) ? fragmentList.get(position) : fragment1;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof ServiceFragment && fragment1 instanceof PieceServiceFragment)
            return POSITION_NONE;
        if (object instanceof PieceServiceFragment && fragment1 instanceof ServiceFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    private void initFragmentListData(AppCompatActivity aca) {
        fragment1 = new ServiceFragment(aca, this);
        fragmentList.add(new SchedulePickupFragment(aca));
        fragmentList.add(new ServiceFragment(aca, this));
        fragmentList.add(new ConfirmFragment(aca));
    }

    @Override
    public void ShowPieceServiceView() {
        this.fragmentManager.beginTransaction().remove(fragment1);
        fragment1 = new PieceServiceFragment(this.appCompatActivity);
        notifyDataSetChanged();
    }
}
