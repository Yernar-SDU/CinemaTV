package com.example.bottomnavigationactivity.network;

import com.example.bottomnavigationactivity.model.Trending;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGES = "https://image.tmdb.org/t/p/original";
    public static final String API_KEY = "?api_key=13a2c587a53a6fd51a2b86a2b9296af3";
    public static final String BASE_YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=";
    private Retrofit retrofit;


    public static MediaAPI getAPI(){

        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).
                client(client)

                .baseUrl(BASE_URL).build();
        MediaAPI mediaAPI = retrofit.create(MediaAPI.class);
        return mediaAPI;

    }

    private Trending[] trendingResults;



}
