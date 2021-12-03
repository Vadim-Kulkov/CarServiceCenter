package entities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarServiceCenterTest {

    private static CarServiceCenter center;
    private static City city;
    private static Customer customer;
    private static Employee employee;
    private static Repair repair;

    @BeforeClass
    public static void setUp() throws Exception {
        center = new CarServiceCenter();
        city = new City();
        customer = new Customer();
        employee = new Employee();
        repair = new Repair();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        center = null;
        city = null;
        customer = null;
        employee = null;
        repair = null;
    }

    @Test
    public void setAddress() {
        String expected = "expected_value";
        center.setAddress(expected);
        assertEquals(expected, center.getAddress());
    }

    @Test
    public void setCity() {
        center.setCity(city);
        assertEquals(city, center.getCity());
    }

    @Test
    public void setCustomers() {
        List<Customer> expected = new ArrayList<>();
        expected.add(customer);
        center.setCustomers(expected);
        assertEquals(expected, center.getCustomers());
    }

    @Test
    public void setEmployees() {
        List<Employee> expected = new ArrayList<>();
        expected.add(employee);
        center.setEmployees(expected);
        assertEquals(expected, center.getEmployees());
    }

    @Test
    public void setPhone() {
        String expected = "expected_valie";
        center.setPhone(expected);
        assertEquals(expected, center.getPhone());
    }

    @Test
    public void setRepairs() {
        List<Repair> expected = new ArrayList<>();
        expected.add(repair);
        center.setRepairs(expected);
        assertEquals(expected, center.getRepairs());
    }

    @Test
    public void setName() {
        String expected = "expected_value";
        center.setName(expected);
        assertEquals(expected, center.getName());
    }
}
