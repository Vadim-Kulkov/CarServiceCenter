package dao;

import entities.Company;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class CompanyDao implements Dao<Company, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Company getById(String phone) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(Company.class, phone);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Company> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM Company", Company.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Company company) {
        Objects.requireNonNull(company, "Company must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(company);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Company update(Company company) {
        try {
            Company result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(company);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Company company) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
