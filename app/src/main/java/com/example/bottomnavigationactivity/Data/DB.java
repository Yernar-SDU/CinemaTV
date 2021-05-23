package com.example.bottomnavigationactivity.Data;

import android.content.SharedPreferences;

import com.example.bottomnavigationactivity.IntentBundleKeys;
import com.example.bottomnavigationactivity.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB {

    public static SharedPreferences sharedPreferences;

    private static final Map<Integer,String> GENRES = new HashMap<Integer, String>(){{
        put(28,"Action");put(12,"Adventure");put(16,"Animation");put(35,"Comedy");put(80,"Crime");
        put(99,"Documentary");put(18,"Drama");put(10751,"Family");put(14,"Fantasy");put(36,"History");
        put(27,"Horror");put(10402,"Music");put(9648,"Mystery");put(10749,"Romance");put(878,"Science Fiction");
        put(10770,"TV Movie");put(53,"Thriller");put(10752,"War");put(37,"Western");
    }};


    public static ArrayList<Movie> getFantasyMoviesForRecycleView(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.FANTASY_TRENDINGS_FOR_RECYCLER_VIEW,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setFantasyMoviesForRecycleView(ArrayList<Movie> fantasyMoviesForRecycleView){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(fantasyMoviesForRecycleView);
        editor.putString(IntentBundleKeys.FANTASY_TRENDINGS_FOR_RECYCLER_VIEW,json);
        editor.apply();
    }


    public static ArrayList<Movie> getActionMoviesForRecycleView(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.ACTION_TRENDINGS_FOR_RECYCLER_VIEW,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setActionMoviesForRecyclerView(ArrayList<Movie> actionMoviesForRecycleView){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(actionMoviesForRecycleView);
        editor.putString(IntentBundleKeys.ACTION_TRENDINGS_FOR_RECYCLER_VIEW,json);
        editor.apply();
    }



    public static ArrayList<Movie> getAnimationMoviesForRecycleView(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.ANIMATION_TRENDINGS_FOR_RECYCLER_VIEW,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setAnimationMoviesForRecyclerView(ArrayList<Movie> animationMoviesForRecyclerView){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(animationMoviesForRecyclerView);
        editor.putString(IntentBundleKeys.ANIMATION_TRENDINGS_FOR_RECYCLER_VIEW,json);
        editor.apply();
    }



    public static ArrayList<Movie> getComedyMoviesForRecycleView(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.COMEDY_TRENDING_FOR_RECYCLER_VIEW,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }



    public static void setComedyMoviesForRecyclerView(ArrayList<Movie> comedyMoviesForRecyclerView){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(comedyMoviesForRecyclerView);
        editor.putString(IntentBundleKeys.COMEDY_TRENDING_FOR_RECYCLER_VIEW,json);
        editor.apply();
    }




    public static ArrayList<Movie> getComedyMovies(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.COMEDY_TRENDINGS,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setComedyMovies(ArrayList<Movie> comedyMovies){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(comedyMovies);
        editor.putString(IntentBundleKeys.COMEDY_TRENDINGS,json);
        editor.apply();
    }



    public static ArrayList<Movie> getAnimationMovies(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.ANIMATION_TRENDINGS,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }


    public static void setAnimationMovies(ArrayList<Movie> animationMovies){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(animationMovies);
        editor.putString(IntentBundleKeys.ANIMATION_TRENDINGS,json);
        editor.apply();
    }




    public static ArrayList<Movie> getActionMovies(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.ACTION_TRENDINGS,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setActionMovies(ArrayList<Movie> actionMovies){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(actionMovies);
        editor.putString(IntentBundleKeys.ACTION_TRENDINGS,json);
        editor.apply();
    }



    public static ArrayList<Movie> getFanstasyMovies(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(IntentBundleKeys.FANTASY_TRENDINGS,"");
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        if(gson.fromJson(json,type) == null){
            return  new ArrayList<>();
        }
        else{
            return gson.fromJson(json,type);
        }
    }

    public static void setFantasyMovies(ArrayList<Movie> fantasyMovies){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(fantasyMovies);
        editor.putString(IntentBundleKeys.FANTASY_TRENDINGS,json);
        editor.apply();
    }









}
