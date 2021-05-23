package com.example.bottomnavigationactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bottomnavigationactivity.Data.DB;

import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.model.TrendingMainJsonObject;
import com.example.bottomnavigationactivity.network.MediaAPI;
import com.example.bottomnavigationactivity.network.NetworkService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends AppCompatActivity {
    private Movie[] trendingResults;
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    private final MediaAPI mediaAPI = NetworkService.getAPI();
    //Lists for recyclerView
    private final ArrayList<Movie> actionMoviesForRecycleView = new ArrayList<>();
    private final ArrayList<Movie> fantasyMoviesForRecycleView = new ArrayList<>();
    private final ArrayList<Movie> animationMoviesForRecycleView = new ArrayList<>();
    private final ArrayList<Movie> comedyMoviesForRecycleView = new ArrayList<>();
    //Lists for imageAdapter
    private final ArrayList<Movie> actionMovies = new ArrayList<>();
    private final ArrayList<Movie> fanstasyMovies = new ArrayList<>();
    private final ArrayList<Movie> animationMovies = new ArrayList<>();
    private final ArrayList<Movie> comedyMovies = new ArrayList<>();
    //HashMap of genres
    private static final Map<Integer,String> GENRES = new HashMap<Integer, String>(){{
        put(28,"Action");put(12,"Adventure");put(16,"Animation");put(35,"Comedy");put(80,"Crime");
        put(99,"Documentary");put(18,"Drama");put(10751,"Family");put(14,"Fantasy");put(36,"History");
        put(27,"Horror");put(10402,"Music");put(9648,"Mystery");put(10749,"Romance");put(878,"Science Fiction");
        put(10770,"TV Movie");put(53,"Thriller");put(10752,"War");put(37,"Western");
    }};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar= findViewById(R.id.progressBar);
        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(i%6 == 0){
                                initializeLists(i / 6 + 1);
                            }
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
        progressBar.setProgress(0);
        DB.sharedPreferences = getPreferences(MODE_PRIVATE);

    }

    public void initializeLists(int i) {

            int finalPage = i;//do not remove this line!
            mediaAPI.getTrendingMovieWeekByPage(i).enqueue(new Callback<TrendingMainJsonObject>() {

                @Override
                public void onResponse(@NotNull Call<TrendingMainJsonObject> call, Response<TrendingMainJsonObject> response) {


                    if (!response.isSuccessful()) {
                        Log.i("response getTrendMovDay", response.message());
                        return;
                    }
                    //200mls
                    TrendingMainJsonObject trendingMainJsonObject = response.body();
                    trendingResults = trendingMainJsonObject.getResult();
                    for (Movie trendingResult : trendingResults) {
                        String genreName = GENRES.get(trendingResult.getGenre_ids()[0]);
                        switch (genreName) {
                            case "Animation":
                                if (animationMovies.size() > 4) {
                                    animationMoviesForRecycleView.add(trendingResult);
                                    continue;
                                }
                                animationMovies.add(trendingResult);
                                break;
                            case "Fantasy":
                                if (fanstasyMovies.size() > 4) {
                                    fantasyMoviesForRecycleView.add(trendingResult);
                                    continue;
                                }
                                fanstasyMovies.add(trendingResult);
                                break;
                            case "Comedy":
                                if (comedyMovies.size() > 4) {
                                    comedyMoviesForRecycleView.add(trendingResult);
                                    continue;
                                }
                                comedyMovies.add(trendingResult);
                            default:
                                if (actionMovies.size() > 4) {
                                    actionMoviesForRecycleView.add(trendingResult);

                                    continue;
                                }
                                actionMovies.add(trendingResult);
                        }

                    }

                    if (finalPage == 17){
                        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                        DB.setActionMovies(actionMovies);
                        DB.setComedyMovies(comedyMovies);
                        DB.setAnimationMovies(animationMovies);
                        DB.setFantasyMovies(fanstasyMovies);
                        DB.setActionMoviesForRecyclerView(actionMoviesForRecycleView);
                        DB.setAnimationMoviesForRecyclerView(animationMoviesForRecycleView);
                        DB.setComedyMoviesForRecyclerView(comedyMoviesForRecycleView);
                        DB.setFantasyMoviesForRecycleView(fantasyMoviesForRecycleView);
                        startActivity(intent);
                    }

                }


                @Override
                public void onFailure(@NotNull Call<TrendingMainJsonObject> call, @NotNull Throwable t) {

                }

            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = 100 / 14;
            int d = a * i;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(d);

                }
            });
        }



}
