package dao;

import entities.Individual;
import service.Dao;

import java.util.List;

public class IndividualDao implements Dao<Individual, String> {

    @Override
    public Individual get(String code) {
        return null;
    }

    @Override
    public List<Individual> getAllAsList() {
        return null;
    }

    @Override
    public void save(Individual individual) {
    }

    @Override
    public void update(Individual individual) {

    }

    @Override
    public void delete(Individual individual) {

    }
}
