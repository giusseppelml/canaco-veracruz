package com.beta.giusseppe.canacoveracruz.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.beta.giusseppe.canacoveracruz.fragments.AclaracionesFragment;
import com.beta.giusseppe.canacoveracruz.fragments.AfiliacionContenidoFragment;
import com.beta.giusseppe.canacoveracruz.fragments.BeneficiosFragment;
import com.beta.giusseppe.canacoveracruz.fragments.EmpresarialFragment;

/**
 * Created by Giusseppe on 25/11/2017.
 */

public class PagerAfiliacionAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAfiliacionAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new AfiliacionContenidoFragment();
            case 1:
                return new BeneficiosFragment();
            case 2:
                return new AclaracionesFragment();
            case 3:
                return new EmpresarialFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
