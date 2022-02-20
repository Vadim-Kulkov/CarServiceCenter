package integration.dao;

import dao.CarServiceCenterDao;
import dao.CityDao;
import dao.EmployeeDao;
import entities.CarServiceCenter;
import entities.City;
import entities.Employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeDaoTest {

    private static EmployeeDao employeeDao = new EmployeeDao();
    private static CityDao cityDao = new CityDao();
    private static CarServiceCenterDao centerDao = new CarServiceCenterDao();

    static City city = new City("test_name", "1232", true, null);

    static CarServiceCenter center = new CarServiceCenter(
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

    @BeforeClass
    public static void setUp() {
        cityDao.save(city);
        centerDao.save(center);
        employeeDao.save(employee);
    }

    @AfterClass
    public static void tearDown() {
        employeeDao.delete(employee);
        centerDao.delete(center);
        cityDao.delete(city);
    }

    @Test
    public void getAllAsList() {
        List<Employee> expected = new ArrayList<>();
        expected.add(employee);
        assertEquals(expected, employeeDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(employee, employeeDao.getById("test_email"));
    }

    @Test
    public void update() {
        String expected = "new_name";
        employee.setFirstName(expected);

        employeeDao.update(employee);
        assertEquals(expected, employeeDao.getById("test_email").getFirstName());
    }
}
