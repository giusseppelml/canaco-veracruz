package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.PagerBolsaAdapter;

public class BolsaDeTrabajoFragment extends Fragment {


    public BolsaDeTrabajoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bolsa_de_trabajo, container, false);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabLayoutBolsa);
        tabLayout.addTab(tabLayout.newTab().setText("Vacantes"));
        tabLayout.addTab(tabLayout.newTab().setText("Selección"));
        tabLayout.addTab(tabLayout.newTab().setText("Psicometrica"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewPagerBolsa);
        PagerBolsaAdapter adapter = new PagerBolsaAdapter(getFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
