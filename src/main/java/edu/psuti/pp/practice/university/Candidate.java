/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

import java.util.ArrayList;
import java.util.List;

public abstract class Candidate {

    private static final int DEFAULT_RESULT_USE = 0;
    private static final int DEFAULT_ENTRACE_TEST_RESULT = 0;
    private final int id;
    private final int passportTally;
    private final int passportNumber;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private List<Direction> directionList;

    public Candidate(int id,
                     int passportTally,
                     int passportNumber,
                     String firstName,
                     String middleName,
                     String lastName) {
        this(id,
                passportTally,
                passportNumber,
                firstName,
                middleName,
                lastName,
                new ArrayList<>());
    }

    public Candidate(int id,
                     int passportTally,
                     int passportNumber,
                     String firstName,
                     String middleName,
                     String lastName,
                     List<Direction> directionList) {
        this.id = id;
        this.passportTally = passportTally;
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.directionList = directionList;
    }

    abstract int getResultPoints();

    abstract void setResultPoints(int resultPoints);

    public int getId() {
        return id;
    }

    public int getPassportTally() {
        return passportTally;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }

    public void setDirectionList(List<Direction> directionList) {
        this.directionList = directionList;
    }

    public void removeDirection(Direction direction) {
        this.directionList.remove(direction);
    }
}
