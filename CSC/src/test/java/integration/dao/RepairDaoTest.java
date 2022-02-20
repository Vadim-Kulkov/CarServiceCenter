package integration.dao;

import dao.CarServiceCenterDao;
import dao.CityDao;
import dao.EmployeeDao;
import dao.RepairDao;
import entities.CarServiceCenter;
import entities.City;
import entities.Employee;
import entities.Repair;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepairDaoTest {

    private static EmployeeDao employeeDao = new EmployeeDao();
    private static CityDao cityDao = new CityDao();
    private static CarServiceCenterDao centerDao = new CarServiceCenterDao();
    private static RepairDao repairDao = new RepairDao();

    private static City city = new City("test_name", "1232", true, null);

    private static CarServiceCenter center = new CarServiceCenter(
            "test_name", "832", null, city, "1111", null, null
    );

    private static Employee employee = new Employee("test_name",
            "test_last_name",
            LocalDate.now(),
            new BigDecimal(100),
            "test_email",
            center,
            null
    );

    private static Repair repair = new Repair("test_description", employee, center);

    @BeforeClass
    public static void setUp() {
        cityDao.save(city);
        centerDao.save(center);
        employeeDao.save(employee);
        repairDao.save(repair);
        List<Repair> repairs = new ArrayList<>();
        repairs.add(repair);
    }

    @AfterClass
    public static void tearDown() {
        repairDao.delete(repair);
        employeeDao.delete(employee);
        centerDao.delete(center);
        cityDao.delete(city);
    }

    @Test
    public void getAllAsList() {
        List<Repair> expected = new ArrayList<>();
        expected.add(repair);
        assertEquals(expected, repairDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(repair, repairDao.getById("test_description"));
    }

    @Test
    public void update() {
        String expected = "483934";
        center.setPhone(expected);
        repair.setCenter(center);
        centerDao.update(center);
        repairDao.update(repair);
        assertEquals(expected, repairDao.getById("test_description").getCenter().getPhone());
    }
}
