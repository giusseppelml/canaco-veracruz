package com.beta.giusseppe.canacoveracruz.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.beta.giusseppe.canacoveracruz.fragments.PsicometricaFragment;
import com.beta.giusseppe.canacoveracruz.fragments.SeleccionFragment;
import com.beta.giusseppe.canacoveracruz.fragments.VacantesFragment;

/**
 * Created by Giusseppe on 26/11/2017.
 */

public class PagerBolsaAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerBolsaAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new VacantesFragment();
            case 1:
                return new SeleccionFragment();
            case 2:
                return new PsicometricaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
