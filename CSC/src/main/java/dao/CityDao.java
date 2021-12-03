package dao;

import entities.City;
import org.hibernate.Session;
import org.hibernate.query.Query;
import service.Dao;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CityDao implements Dao<City, String> {

    private Session session;

    @Override
    public City get(String code) {
        return session.find(City.class, code);
    }

    @Override
    public List<City> getAllAsList() {
        Query<City> query = session.createQuery("select e from City e");
        return query.getResultList();
    }

    @Override
    public void save(City city) {
        try {
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(City city) {
        try {
            session.beginTransaction();
            session.update(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(City city) {
        try {
            session.beginTransaction();
            session.remove(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
