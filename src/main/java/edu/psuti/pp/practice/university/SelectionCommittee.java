/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

import java.time.LocalDate;
import java.util.*;

public class SelectionCommittee extends University {

    LocalDate dateOfCommittee;

    private final Map<Direction, List<Candidate>> useCandidateList;
    private final Map<Direction, List<Candidate>> collegeCandidateList;

    public SelectionCommittee(LocalDate dateOfCommittee) {
        this(dateOfCommittee, new HashMap<>(), new HashMap<>());
    }

    public SelectionCommittee(LocalDate dateOfCommittee,
                              Map<Direction, List<Candidate>> useCandidateList,
                              Map<Direction, List<Candidate>> collegeCandidateList) {
        this.dateOfCommittee = dateOfCommittee;
        this.useCandidateList = useCandidateList;
        this.collegeCandidateList = collegeCandidateList;
    }

    public LocalDate getDateOfCommittee() {
        return dateOfCommittee;
    }

    public void setDateOfCommittee(LocalDate dateOfCommittee) {
        this.dateOfCommittee = dateOfCommittee;
    }

    public Map<Direction, List<Candidate>> getUseCandidateList() {
        return useCandidateList;
    }

    public Map<Direction, List<Candidate>> getCollegeCandidateList() {
        return collegeCandidateList;
    }

    public List<Candidate> getUseCandidatesListByDirection(Direction direction) {
        return useCandidateList.get(direction);
    }

    public List<Candidate> getCollegeCandidatesListByDirection(Direction direction) {
        return collegeCandidateList.get(direction);
    }

    public void getEnrolledListByDirection(Direction direction) {
        sortCandidateListByDirection(direction);

        List<Candidate> useCandidates = new ArrayList<>(useCandidateList.get(direction));
        List<Candidate> collegeCandidates = new ArrayList<>(collegeCandidateList.get(direction));

        int freeUsePlaces = direction.getUsePlaces();
        int freeCollegePlaces = direction.getCollegePlaces();

        useCandidates.subList(freeUsePlaces, useCandidates.size()).clear();
        collegeCandidates.subList(freeCollegePlaces, collegeCandidates.size()).clear();
    }

    public void addCandidateToDirections(Candidate candidate) {
        if (candidate.getClass().equals(UseCandidate.class)) {
            for (Direction direction : candidate.getDirectionList()) {
                if (direction.getPassingPoints() <= candidate.getResultPoints()) {
                    addCandidate(direction, candidate);
                }
            }
        } else {
            for (Direction direction : candidate.getDirectionList()) {
                if (direction.getCollegePassingPoints() <= candidate.getResultPoints()) {
                    addCandidate(direction, candidate);
                }
            }
        }
        for (Direction direction : candidate.getDirectionList()) {
            sortCandidateListByDirection(direction);
        }
    }

    private void addCandidate(Direction direction, Candidate candidate) {
        collegeCandidateList.get(direction).add(candidate);
    }

    public void sortCandidateListByDirection(Direction direction) {
        Collections.sort(useCandidateList.get(direction), SalaryComparator);
        Collections.sort(useCandidateList.get(direction), SalaryComparator);
    }

    public static Comparator<Candidate> SalaryComparator = new Comparator<Candidate>() {
        @Override
        public int compare(Candidate e1, Candidate e2) {
            return Integer.compare(e2.getResultPoints(), e1.getResultPoints());
        }
    };


    public void addDirection(Direction direction) {
        this.useCandidateList.put(direction, new ArrayList<>());
        this.collegeCandidateList.put(direction, new ArrayList<>());
    }

    /*
        public static void main(String[] args) {
            Faculty f = new Faculty("FIST");
            List<Direction> c = new ArrayList<>();
            Direction UU1 = new Direction("IVT", 100, 50, 2);
            Direction UU2 = new Direction("IKG", 100, 50, 2);
            f.addDirection(UU1);
            f.addDirection(UU2);
            c.add(UU1);
            c.add(UU2);
            Candidate c1 = new CollegeCandidate(4, 33, 55, "A", "B",
                    "S",
                    c,
                    50);
            Candidate c2 = new CollegeCandidate(1, 22, 66, "D", "E",
                    "F",
                    c,
                    50);
            Candidate c3 = new UseCandidate(5, 11, 77, "G", "K", "L",
                    c,
                    50);
            Candidate c4 = new UseCandidate(6, 44, 88, "M", "N", "O",
                    c,
                    50);
            c1.setResultPoints(110);
            c2.setResultPoints(33);
            c3.setResultPoints(14);
            c4.setResultPoints(200);
            SelectionCommittee sc = new SelectionCommittee(LocalDate.of(1914, 12, 31));
            sc.addDirection(UU1);
            sc.addDirection(UU2);
            sc.addCandidateToDirections(c1);
            sc.addCandidateToDirections(c2);
            sc.addCandidateToDirections(c3);
            sc.addCandidateToDirections(c4);

            sc.sortCandidateListByDirection(UU1);
            sc.sortCandidateListByDirection(UU2);

            List<Candidate> PP1 = sc.useCandidateList.get(UU1);
            List<Candidate> PP2 = sc.useCandidateList.get(UU2);
            for (Candidate QQ1 : PP1) {
                System.out.println(QQ1.getResultPoints());
            }
            System.out.println();
            for (Candidate QQ3 : PP2) {
                System.out.println(QQ3.getResultPoints());
            }
        }
    */
    public void removeDirection(Direction direction) {
        this.useCandidateList.remove(direction);
        this.collegeCandidateList.remove(direction);
    }

    public void removeCandidateFromDirection(Candidate candidate, Direction direction) {
        if (candidate.getClass().equals(UseCandidate.class)) {
            this.useCandidateList.get(direction).remove(candidate);
        } else {

            this.collegeCandidateList.get(direction).remove(candidate);
        }
        sortCandidateListByDirection(direction);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SelectionCommittee{");
        sb.append("dateOfCommittee=").append(dateOfCommittee);
        sb.append(", useCandidateList=").append(useCandidateList);
        sb.append('}');
        return sb.toString();
    }
}