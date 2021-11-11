/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

import java.util.ArrayList;
import java.util.List;

public class University {

    private static final String DEFAULT_NAME = "University name";

    private String name;
    private static List<Faculty> faculties;

    protected University() {
        this(DEFAULT_NAME, new ArrayList<>());
    }

    protected University(String name) {
        this(name, new ArrayList<>());
    }

    protected University(String name, List<Faculty> faculties) {
        this.name = name;
        faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }

    public void setFaculty(int position, Faculty faculty) {
        faculties.add(position, faculty);
    }
}
