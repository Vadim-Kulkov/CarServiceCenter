package dao;

import entities.Individual;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class IndividualDao implements Dao<Individual, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Individual getById(String phone) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(Individual.class, phone);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Individual> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM Individual", Individual.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Individual individual) {
        Objects.requireNonNull(individual, "Company must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(individual);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Individual update(Individual individual) {
        try {
            Individual result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(individual);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Individual individual) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(individual) ? individual : entityManager.merge(individual));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
