package dao;

import entities.Employee;
import service.Dao;

import java.util.List;

public class EmployeeDao implements Dao<Employee, String> {

    @Override
    public Employee get(String email) {
        return null;
    }

    @Override
    public List<Employee> getAllAsList() {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }
}
