/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

public class Direction extends Faculty {

    private final static String DEFAULT_NAME = "Direction name";
    private final static int DEFAULT_PASSING_POINTS = 0;
    private final static int DEFAULT_COLLEGE_PASSING_POINTS = 0;
    private final static int DEFAULT_COLLEGE_PLACES = 10;
    private final static int DEFAULT_USE_PLACES = 50;

    private final String name;
    private int passingPoints;
    private int collegePassingPoints;
    private int usePlaces;
    private int collegePlaces;

    public Direction(String name) {
        this(name,
                DEFAULT_PASSING_POINTS,
                DEFAULT_COLLEGE_PASSING_POINTS,
                DEFAULT_USE_PLACES,
                DEFAULT_COLLEGE_PLACES);
    }

    public Direction(String name, int passingPoints) {
        this(name,
                passingPoints,
                DEFAULT_COLLEGE_PASSING_POINTS,
                DEFAULT_USE_PLACES,
                DEFAULT_COLLEGE_PLACES);
    }

    public Direction(String name, int passingPoints, int collegePassingPoints) {
        this(name, passingPoints, collegePassingPoints, DEFAULT_USE_PLACES, DEFAULT_COLLEGE_PLACES);
    }

    public Direction(String name, int passingPoints, int collegePassingPoints, int usePlaces) {
        this(name, passingPoints, collegePassingPoints, usePlaces, DEFAULT_COLLEGE_PLACES);
    }

    public Direction(String name,
                     int passingPoints,
                     int collegePassingPoints,
                     int usePlaces,
                     int collegePlaces) {
        this.name = name;
        this.passingPoints = passingPoints;
        this.collegePassingPoints = collegePassingPoints;
        this.usePlaces = usePlaces;
        this.collegePlaces = collegePlaces;
    }

    public String getName() {
        return name;
    }

    public int getPassingPoints() {
        return passingPoints;
    }

    public int getCollegePassingPoints() {
        return collegePassingPoints;
    }

    public void setCollegePassingPoints(int collegePassingPoints) {
        this.collegePassingPoints = collegePassingPoints;
    }

    public int getCollegePlaces() {
        return collegePlaces;
    }

    public void setCollegePlaces(int collegePlaces) {
        this.collegePlaces = collegePlaces;
    }

    public void setPassingPoints(int passingPoints) {
        this.passingPoints = passingPoints;
    }

    public int getUsePlaces() {
        return usePlaces;
    }

    public void setUsePlaces(int usePlaces) {
        this.usePlaces = usePlaces;
    }
}
