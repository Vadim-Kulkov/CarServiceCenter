package integration.dao;

import dao.CityDao;
import entities.City;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CityDaoTest {

    private static CityDao cityDao = new CityDao();

    private static City city = new City("name", "1242", true, null);

    @BeforeClass
    public static void setUp() {
        cityDao.save(city);
    }

    @AfterClass
    public static void tearDown() {
        cityDao.delete(city);
    }

    @Test
    public void getAllAsList() {
        List<City> expected = new ArrayList<>();
        expected.add(city);
        assertEquals(expected, cityDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(city, cityDao.getById("1242"));
    }

    @Test
    public void update() {
        String expected = "new_name";
        city.setName(expected);

        cityDao.update(city);
        assertEquals(expected, cityDao.getById("1242").getName());
    }
}
