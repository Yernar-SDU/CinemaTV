package com.example.bottomnavigationactivity.model;

import java.io.Serializable;

public class CreditsPeople implements Serializable {
    private Person[] cast;
    private Person[] crew;

    public Person[] getCast() {
        return cast;
    }

    public void setCast(Person[] cast) {
        this.cast = cast;
    }

    public Person[] getCrew() {
        return crew;
    }

    public void setCrew(Person[] crew) {
        this.crew = crew;
    }
}
