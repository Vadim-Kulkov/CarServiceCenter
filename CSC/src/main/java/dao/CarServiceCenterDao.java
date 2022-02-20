package dao;

import entities.CarServiceCenter;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class CarServiceCenterDao implements Dao<CarServiceCenter, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CarServiceCenter getById(String name) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(CarServiceCenter.class, name);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<CarServiceCenter> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("SELECT u FROM CarServiceCenter u", CarServiceCenter.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(CarServiceCenter carServiceCenter) {
        Objects.requireNonNull(carServiceCenter, "CarServiceCenter must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(carServiceCenter);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public CarServiceCenter update(CarServiceCenter carServiceCenter) {
        try {
            CarServiceCenter result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(carServiceCenter);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(CarServiceCenter carServiceCenter) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(carServiceCenter) ? carServiceCenter
                    : entityManager.merge(carServiceCenter));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
