package dao;

import entities.City;
import service.Dao;
import java.util.List;
import java.util.Objects;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CityDao implements Dao<City, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public City getById(String code) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(City.class, code);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<City> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM City", City.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(City city) {
        Objects.requireNonNull(city, "City must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public City update(City city) {
        try {
            City result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(city);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(City city) {
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
