package dao;

import entities.Employee;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class EmployeeDao implements Dao<Employee, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee getById(String email) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(Employee.class, email);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Employee> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM Employee", Employee.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Employee employee) {
        Objects.requireNonNull(employee, "Employee must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Employee update(Employee employee) {
        try {
            Employee result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(employee);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Employee city) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(city) ? city : entityManager.merge(city));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
