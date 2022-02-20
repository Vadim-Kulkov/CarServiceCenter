package integration.entities;

import entities.CarServiceCenter;
import entities.Employee;
import entities.Repair;
import org.junit.*;

import static org.junit.Assert.*;

public class RepairTest {

    private static Repair repair;
    private static Employee employee;
    private static CarServiceCenter center;

    @BeforeClass
    public static void setUp() throws Exception {
        repair = new Repair();
        employee = new Employee();
        center = new CarServiceCenter();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        repair = null;
        employee = null;
        center = null;
    }

    @Test
    public void setCenter() {
        repair.setCenter(center);
        assertEquals(center, repair.getCenter());
    }

    @Test
    public void setDescription() {
        String expected = "expected_value";
        repair.setDescription(expected);
        assertEquals(expected, repair.getDescription());
    }

    @Test
    public void setEmployee() {
        repair.setEmployee(employee);
        assertEquals(employee, repair.getEmployee());
    }
}
