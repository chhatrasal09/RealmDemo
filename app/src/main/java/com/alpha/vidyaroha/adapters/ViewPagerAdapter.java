package com.alpha.vidyaroha.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpha on 11/8/17.
 */
// Adapter to create the number of view pagers.
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> fragmentTitleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position); // return the total number of fragments to be shown.
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);  // To add the number of fragments to be added in the view pager
        fragmentTitleList.add(title);   // add the names to each of the view pager.
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);   // returns the title of the view pager.
    }
}
