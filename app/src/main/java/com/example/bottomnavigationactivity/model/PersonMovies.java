package com.example.bottomnavigationactivity.model;

public class PersonMovies{
    private boolean adult;
    private String overview;
    private String backdrop_path;
    private String release_date;
    private String title;
    private int id;
    private int[] genre_ids;
    private String original_language;
    private String original_title;
    private String poster_path;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private double popularity;
    private String character;
    private String credit_id;
    private int order;


    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public int getOrder() {
        return order;
    }
}
