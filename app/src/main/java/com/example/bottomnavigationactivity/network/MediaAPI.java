package com.example.bottomnavigationactivity.network;


import com.example.bottomnavigationactivity.model.CreditsMovie;
import com.example.bottomnavigationactivity.model.CreditsPeople;
import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.model.Person;
import com.example.bottomnavigationactivity.model.TrendingMainJsonObject;
import com.example.bottomnavigationactivity.model.VideoTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MediaAPI {
    String API_KEY = "?api_key=13a2c587a53a6fd51a2b86a2b9296af3";


    @GET("trending/movie/week" + API_KEY)
    Call<TrendingMainJsonObject> getTrendingMovieWeekByPage(@Query("page") int page);

    @GET("movie/{movie_id}" + API_KEY + "&language=en-US")
    Call<Movie> getMovieById(@Path("movie_id") int movie_id);


    @GET("movie/{movie_id}/credits" + API_KEY + "&language=en-US")
    Call<CreditsPeople> getCreditsMovieById(@Path("movie_id") int movie_id);


    @GET("movie/{movie_id}/videos" + API_KEY + "&language=en-US")
    Call<VideoTrailer> getVideoMovieById(@Path("movie_id") int movie_id);


    @GET("person/{person_id}/movie_credits" + API_KEY)
    Call<CreditsMovie> getPersonMovies(@Path("person_id") int person_id);

    @GET("person/{person_id}" + API_KEY)
    Call<Person> getPersonDetails(@Path("person_id") int person_id);

}
