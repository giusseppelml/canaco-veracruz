package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.PagerAfiliacionAdapter;

public class AfiliacionFragment extends Fragment {


    public AfiliacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_afiliacion, container, false);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabLayoutAfiliacion);
        tabLayout.addTab(tabLayout.newTab().setText("Afiliación"));
        tabLayout.addTab(tabLayout.newTab().setText("Beneficios"));
        tabLayout.addTab(tabLayout.newTab().setText("¿Dudas?"));
        tabLayout.addTab(tabLayout.newTab().setText("Programa"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewPagerAfiliacion);
        PagerAfiliacionAdapter adapter = new PagerAfiliacionAdapter(getFragmentManager(), tabLayout.getTabCount());

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
