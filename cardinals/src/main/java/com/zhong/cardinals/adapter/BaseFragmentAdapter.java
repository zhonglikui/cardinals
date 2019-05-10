package com.zhong.cardinals.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by zhong on 2017/5/24.
 */

public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;


    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
