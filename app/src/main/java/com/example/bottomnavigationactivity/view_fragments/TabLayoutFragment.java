package com.example.bottomnavigationactivity.view_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigationactivity.Data.DB;
import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.adapter.ImageAdapter;
import com.example.bottomnavigationactivity.adapter.RecycleViewCardMovieAdapter;
import com.example.bottomnavigationactivity.model.Movie;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class TabLayoutFragment extends Fragment {
    private RecyclerView mainRecycleView;
    private TabLayout tabDots;
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;
    private RecycleViewCardMovieAdapter recycleViewCardMovieAdapter;
    private ImageView leftArrowButton;
    private ImageView rightArrowButton;
    private int position;
    //Lists for recyclerView
    private List<Movie> actionMoviesForRecycleView = new ArrayList<>();
    private List<Movie> fantasyMoviesForRecycleView = new ArrayList<>();
    private List<Movie> animationMoviesForRecycleView = new ArrayList<>();
    private List<Movie> comedyMoviesForRecycleView = new ArrayList<>();
    //Lists for imageAdapter
    private ArrayList<Movie> actionMovies = new ArrayList<>();
    private List<Movie> fantasyMovies = new ArrayList<>();
    private List<Movie> animationMovies = new ArrayList<>();
    private List<Movie> comedyMovies = new ArrayList<>();

    public static TabLayoutFragment newInstance(int a) {
        return new TabLayoutFragment(a);
    }

    public TabLayoutFragment(int a){
        this.position = a;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_layout_fragment, container, false);

        getLists();

        //find views from id
        findViewByIds(root);

        //View pager buttons set OnClick
        leftArrowButton.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
        rightArrowButton.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1));
        initializeAdapters(position);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void initializeAdapters(int position){

        if (position == 0){
            imageAdapter = new ImageAdapter(getContext(),actionMovies);
            recycleViewCardMovieAdapter = new RecycleViewCardMovieAdapter(getContext(),actionMoviesForRecycleView);
        }else if (position == 1){
            imageAdapter = new ImageAdapter(getContext(),animationMovies);
            recycleViewCardMovieAdapter = new RecycleViewCardMovieAdapter(getContext(),animationMoviesForRecycleView);
        }else if (position == 2){
            imageAdapter = new ImageAdapter(getContext(),comedyMovies);
            recycleViewCardMovieAdapter = new RecycleViewCardMovieAdapter(getContext(),comedyMoviesForRecycleView);
        }else {
            imageAdapter = new ImageAdapter(getContext(),fantasyMovies);
            recycleViewCardMovieAdapter = new RecycleViewCardMovieAdapter(getContext(),fantasyMoviesForRecycleView);
        }
        imageAdapter.notifyDataSetChanged();
        viewPager.setAdapter(imageAdapter);
        mainRecycleView.setAdapter(recycleViewCardMovieAdapter);
        mainRecycleView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    private void getLists() {
        actionMovies = DB.getActionMovies();
        fantasyMovies = DB.getFanstasyMovies();
        animationMovies = DB.getAnimationMovies();
        comedyMovies = DB.getComedyMovies();
        actionMoviesForRecycleView = DB.getActionMoviesForRecycleView();
        fantasyMoviesForRecycleView = DB.getFantasyMoviesForRecycleView();
        animationMoviesForRecycleView = DB.getAnimationMoviesForRecycleView();
        comedyMoviesForRecycleView = DB.getComedyMoviesForRecycleView();
    }

    public void findViewByIds(View root){
        leftArrowButton = root.findViewById(R.id.leftArrowButton);
        rightArrowButton = root.findViewById(R.id.rightArrowButton);
        tabDots = root.findViewById(R.id.tabDots);
        viewPager = root.findViewById(R.id.viewPager);
        tabDots.setupWithViewPager(viewPager,true);
        mainRecycleView = root.findViewById(R.id.main_recycleView);
    }




}

