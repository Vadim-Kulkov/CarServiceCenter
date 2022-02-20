package integration.entities;

import entities.CarServiceCenter;
import entities.Employee;
import entities.Repair;
import org.junit.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeTest {

    private static Employee employee;
    private static Repair repair;
    private static CarServiceCenter center;

    @BeforeClass
    public static void setUp() {
        employee = new Employee();
        repair = new Repair();
        center = new CarServiceCenter();
    }

    @AfterClass
    public static void tearDown() {
        employee = null;
        repair = null;
        center = null;
    }

    @Test
    public void setRepairs() {
        List<Repair> expected = new ArrayList<>();
        expected.add(repair);
        employee.setRepairs(expected);
        assertEquals(expected, employee.getRepairs());
    }

    @Test
    public void setBirthDate() {
        LocalDate expected = LocalDate.now();
        employee.setBirthDate(expected);
        assertEquals(expected, employee.getBirthDate());
    }

    @Test
    public void setCenter() {
        employee.setCenter(center);
        assertEquals(center, employee.getCenter());
    }

    @Test
    public void setEmail() {
        String expected = "expected_value";
        employee.setEmail(expected);
        assertEquals(expected, employee.getEmail());
    }

    @Test
    public void setFirstName() {
        String expected = "expected_value";
        employee.setFirstName(expected);
        assertEquals(expected, employee.getFirstName());
    }

    @Test
    public void setLastName() {
        String expected = "expected_value";
        employee.setLastName(expected);
        assertEquals(expected, employee.getLastName());
    }

    @Test
    public void setSalary() {
        BigDecimal expected = BigDecimal.valueOf(999_999.999);
        employee.setSalary(expected);
        assertEquals(expected, employee.getSalary());
    }
}
