package com.beta.giusseppe.canacoveracruz.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.beta.giusseppe.canacoveracruz.fragments.EventosFragment;
import com.beta.giusseppe.canacoveracruz.fragments.NoticiasFragment;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new NoticiasFragment();
            case 1:
                return new EventosFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
