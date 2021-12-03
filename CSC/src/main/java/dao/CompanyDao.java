package dao;

import entities.Company;
import service.Dao;

import java.util.List;

public class CompanyDao implements Dao<Company, String> {

    @Override
    public Company get(String inn) {
        return null;
    }

    @Override
    public List<Company> getAllAsList() {
        return null;
    }

    @Override
    public void save(Company company) {

    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void delete(Company company) {

    }
}
