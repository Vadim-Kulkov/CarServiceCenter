/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

import java.util.HashMap;
import java.util.Map;

public class Faculty extends University {

    private final static String DEFAULT_NAME = "Faculty name";

    private final String name;
    private Map<String, Direction> directionList;

    public Faculty() {
        this(DEFAULT_NAME, new HashMap<>());
    }

    public Faculty(String name) {
        this(name, new HashMap<>());
    }

    public Faculty(String name, Map<String, Direction> directionList) {
        this.name = name;
        this.directionList = directionList;
    }

    public String getName() {
        return name;
    }

    public void addDirection(Direction direction) {
        directionList.put(direction.getName(), direction);
    }

    public Direction getDirection(String name) {
        return directionList.get(name);
    }

    public void removeDirectionFromList(String name) {
        this.directionList.remove(name);
    }

    public Map<String, Direction> getDirectionList() {
        return directionList;
    }

    public void setDirectionList(Map<String, Direction> directionList) {
        this.directionList = directionList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("name='").append(name).append('\'');
        sb.append(", directionList=").append(directionList);
        sb.append('}');
        return sb.toString();
    }
}
