package com.example.bottomnavigationactivity.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.view_fragments.TabLayoutFragment;


public class TabLayoutAdapter extends FragmentPagerAdapter {

    public static final int[] TAB_TITLES = {R.string.Action, R.string.Animation, R.string.Comedy, R.string.Fantasy};
    private final Context context;

    public TabLayoutAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.context = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i("positionAdapter", position + "");
        return TabLayoutFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }


}
