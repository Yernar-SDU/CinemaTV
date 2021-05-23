package com.example.bottomnavigationactivity.model;

import java.io.Serializable;

public class CreditsMovie implements Serializable {
    private Movie[] cast;
    private Movie[] crew;

    public Movie[] getCast() {
        return cast;
    }

    public void setCast(Movie[] cast) {
        this.cast = cast;
    }

    public Movie[] getCrew() {
        return crew;
    }

    public void setCrew(Movie[] crew) {
        this.crew = crew;
    }
}
