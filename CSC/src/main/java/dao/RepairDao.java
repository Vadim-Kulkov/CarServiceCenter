package dao;

import entities.Repair;
import service.Dao;
import utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class RepairDao implements Dao<Repair, String> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Repair getById(String description) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.find(Repair.class, description);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Repair> getAllAsList() {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            return entityManager.createQuery("FROM Repair", Repair.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Repair repair) {
        Objects.requireNonNull(repair, "Repair must be not null");

        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Repair update(Repair repair) {
        try {
            Repair result;
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            result = entityManager.merge(repair);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Repair repair) {
        try {
            entityManager = JpaUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(repair) ? repair : entityManager.merge(repair));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
