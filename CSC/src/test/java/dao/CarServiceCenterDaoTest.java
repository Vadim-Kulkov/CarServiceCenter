package dao;

import entities.CarServiceCenter;
import entities.City;
import org.hibernate.Session;
import org.junit.*;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarServiceCenterDaoTest {

    private static Session session;

    private static CityDao cityDao;
    private static CarServiceCenterDao carServiceCenterDao;

    static City city = new City("name", "1242", true, null);

    static CarServiceCenter center = new CarServiceCenter(
            "test_name", "832", null, city, "1111", null, null
    );

    @BeforeClass
    public static void setUp() throws Exception {
        session = HibernateUtil.getSession();
        carServiceCenterDao = new CarServiceCenterDao(session);
        cityDao = new CityDao(session);
        cityDao.save(city);
        carServiceCenterDao.save(center);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        carServiceCenterDao.delete(center);
        cityDao.delete(city);
        session.close();
    }

    @Test
    public void getAllAsList() {
        List<CarServiceCenter> carServiceCenters = carServiceCenterDao.getAllAsList();
        List<CarServiceCenter> expected = new ArrayList<>();
        expected.add(center);
        assertEquals(expected, carServiceCenters);
    }

    @Test
    public void save() {
        assertEquals(center, carServiceCenterDao.get("test_name"));
    }

    @Test
    public void update() {
        String expected = "38328";
        center.setPhone(expected);

        carServiceCenterDao.update(center);
        assertEquals(expected, carServiceCenterDao.get("test_name").getPhone());
    }
}
