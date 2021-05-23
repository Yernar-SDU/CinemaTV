package com.example.bottomnavigationactivity.model;

import java.io.Serializable;

public class VideoTrailer implements Serializable {
    private Video[] results;

    public Video[] getResults() {
        return results;
    }

    public void setResults(Video[] results) {
        this.results = results;
    }
}
