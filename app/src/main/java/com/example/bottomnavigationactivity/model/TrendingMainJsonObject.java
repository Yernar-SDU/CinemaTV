package com.example.bottomnavigationactivity.model;

import java.io.Serializable;

public class TrendingMainJsonObject implements Serializable {
    private int page;
    private Movie[] results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Movie[] getResult() {
        return results;
    }

    public void setResult(Movie[] results) {
        this.results = results;
    }
}
