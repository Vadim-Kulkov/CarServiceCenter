package dao;

import entities.CarServiceCenter;
import org.hibernate.Session;
import service.Dao;
import utils.HibernateUtil;

import org.hibernate.query.Query;
import java.util.List;

//public class CarServiceCenterDao implements Dao<CarServiceCenter, String> {

//    Session session;
//
//    public CarServiceCenterDao(Session session) {
//        this.session = session;
//    }
//
//    @Override
//    public CarServiceCenter get(String id) {
//        return session.find(CarServiceCenter.class, id);
//    }
//
//    @Override
//    public List<CarServiceCenter> getAll() {
//        Query query = session.createQuery("select e from CarServiceCenter e");
//        return query.getResultList();
//    }
//
//    @Override
//    public void save(CarServiceCenter carServiceCenter) {
//        session.update(carServiceCenter);
//        session.beginTransaction().commit();
//    }
//
//    @Override
//    public void update(CarServiceCenter carServiceCenterDao) {
//        session.update(carServiceCenterDao);
//        session.beginTransaction().commit();
//    }
//
//    @Override
//    public void delete(CarServiceCenter carServiceCenter) {
//        session.remove(carServiceCenter);
//        session.beginTransaction().commit();
//    }
//}
