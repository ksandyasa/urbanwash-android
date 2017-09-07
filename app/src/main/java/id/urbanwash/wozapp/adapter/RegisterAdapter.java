package id.urbanwash.wozapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import id.urbanwash.wozapp.fragment.CreateAccountStepOne;
import id.urbanwash.wozapp.fragment.CreateAccountStepThree;
import id.urbanwash.wozapp.fragment.CreateAccountStepTwo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by apridosandyasa on 3/28/16.
 */
public class RegisterAdapter extends FragmentStatePagerAdapter {
    private AppCompatActivity appCompatActivity;
    private List<Fragment> fragmentList;
    private Fragment fragment;

    public RegisterAdapter(FragmentManager fm, AppCompatActivity aca) {
        super(fm);
        this.appCompatActivity = aca;
        initFragmentListData(this.appCompatActivity);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    private void initFragmentListData(AppCompatActivity aca) {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CreateAccountStepOne(aca));
        fragmentList.add(new CreateAccountStepTwo(aca));
        fragmentList.add(new CreateAccountStepThree(aca));
    }

}
