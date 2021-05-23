package com.example.bottomnavigationactivity.bottomNavigationUI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.adapter.TabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    TabLayoutAdapter tabLayoutAdapter;
    HomeViewModel homeViewModel;
    Bundle bundle;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);


        ViewPager viewPager = root.findViewById(R.id.pager);
        tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(tabLayoutAdapter);
        TabLayout tabLayout = root.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }




}