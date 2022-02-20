package integration.dao;

import dao.CarServiceCenterDao;
import dao.CityDao;
import entities.CarServiceCenter;
import entities.City;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarServiceCenterDaoTest {

    private static CityDao cityDao = new CityDao();
    private static CarServiceCenterDao carServiceCenterDao = new CarServiceCenterDao();

    static City city = new City("name", "1242", true, null);

    static CarServiceCenter center = new CarServiceCenter(
            "test_name", "832", null, city, "1111", null, null
    );

    @BeforeClass
    public static void setUp() {
        cityDao.save(city);
        carServiceCenterDao.save(center);
    }

    @AfterClass
    public static void tearDown() {
        carServiceCenterDao.delete(center);
        cityDao.delete(city);
    }

    @Test
    public void getAllAsList() {
        List<CarServiceCenter> expected = new ArrayList<>();
        expected.add(center);
        assertEquals(expected, carServiceCenterDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(center, carServiceCenterDao.getById("test_name"));
    }

    @Test
    public void update() {
        String expected = "38328";
        center.setPhone(expected);

        carServiceCenterDao.update(center);
        assertEquals(expected, carServiceCenterDao.getById("test_name").getPhone());
    }
}
