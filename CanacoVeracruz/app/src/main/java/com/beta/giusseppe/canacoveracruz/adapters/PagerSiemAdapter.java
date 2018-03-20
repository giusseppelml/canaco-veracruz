package com.beta.giusseppe.canacoveracruz.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.beta.giusseppe.canacoveracruz.fragments.PromotoresFragment;
import com.beta.giusseppe.canacoveracruz.fragments.SiemFragment;

/**
 * Created by Giusseppe on 30/10/2017.
 */

public class PagerSiemAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerSiemAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new SiemFragment();
            case 1:
                return new PromotoresFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
