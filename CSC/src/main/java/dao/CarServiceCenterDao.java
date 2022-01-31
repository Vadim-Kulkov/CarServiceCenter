package dao;

import entities.CarServiceCenter;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import service.Dao;

import org.hibernate.query.Query;

import java.util.List;

@AllArgsConstructor
public class CarServiceCenterDao implements Dao<CarServiceCenter, String> {

    Session session;

    @Override
    public CarServiceCenter get(String name) {
        session.beginTransaction();
        CarServiceCenter result = session.find(CarServiceCenter.class, name);
        session.getTransaction();
        return result;
    }

    @Override
    public List<CarServiceCenter> getAllAsList() {
        Query<CarServiceCenter> query = session.createQuery("select e from CarServiceCenter e");
        return query.getResultList();
    }

    @Override
    public void save(CarServiceCenter carServiceCenter) {
        try {
            session.beginTransaction();
            session.save(carServiceCenter);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(CarServiceCenter carServiceCenter) {
        try {
            session.beginTransaction();
            session.update(carServiceCenter);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CarServiceCenter carServiceCenter) {
        try {
            session.beginTransaction();
            session.remove(carServiceCenter);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
