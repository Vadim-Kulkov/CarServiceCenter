package dao;

import entities.Customer;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class CustomerDao implements Dao<Customer, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getById(String phone) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(Customer.class, phone);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM Customer", Customer.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Customer customer) {
        Objects.requireNonNull(customer, "Customer must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer update(Customer customer) {
        try {
            Customer result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
