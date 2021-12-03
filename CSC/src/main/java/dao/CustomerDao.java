package dao;

import entities.Customer;
import service.Dao;

import java.util.List;

public class CustomerDao implements Dao<Customer, String> {

    @Override
    public Customer get(String phone) {
        return null;
    }

    @Override
    public List<Customer> getAllAsList() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
