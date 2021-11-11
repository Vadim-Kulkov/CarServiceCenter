/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollegeCandidate extends Candidate {

    private static final int DEFAULT_ENTRANCE_EXAM_RESULT = 0;

    private int resultPoints;

    public CollegeCandidate(int id,
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
                new ArrayList<>(),
                DEFAULT_ENTRANCE_EXAM_RESULT);
    }

    public CollegeCandidate(int id,
                            int passportTally,
                            int passportNumber,
                            String firstName,
                            String middleName,
                            String lastName,
                            List<Direction> directionList) {
        this(id,
                passportTally,
                passportNumber,
                firstName,
                middleName,
                lastName,
                directionList,
                DEFAULT_ENTRANCE_EXAM_RESULT);
    }

    public CollegeCandidate(int id,
                            int passportTally,
                            int passportNumber,
                            String firstName,
                            String middleName,
                            String lastName,
                            List<Direction> directionList,
                            int resultPoints) {
        super(id, passportTally, passportNumber, firstName, middleName, lastName, directionList);
        this.resultPoints = resultPoints;
    }

    @Override
    public int getResultPoints() {
        return resultPoints;
    }

    @Override
    public void setResultPoints(int resultPoints) {
        this.resultPoints = resultPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (CollegeCandidate) o;
        return getId() == candidate.getId() &&
                getPassportTally() == candidate.getPassportTally() &&
                getPassportNumber() == candidate.getPassportNumber() &&
                getFirstName().equals(candidate.getFirstName()) &&
                getMiddleName().equals(candidate.getMiddleName()) &&
                getLastName().equals(candidate.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getPassportTally(),
                getPassportNumber(),
                getFirstName(),
                getMiddleName(),
                getLastName(),
                resultPoints);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Candidate{");
        sb.append("id=").append(getId());
        sb.append(", passportTally=").append(getPassportTally());
        sb.append(", passportNumber=").append(getPassportNumber());
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", middleName='").append(getMiddleName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", directionList=").append(getDirectionList());
        sb.append(", resultPoints=").append(resultPoints);
        sb.append('}');
        return sb.toString();
    }
}
