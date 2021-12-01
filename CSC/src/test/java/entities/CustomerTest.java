package entities;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

    private static Customer customer;
    private static CarServiceCenter center;

    @BeforeClass
    public static void setUp() throws Exception {
        customer = new Customer();
        center = new CarServiceCenter();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        customer = null;
        center = null;
    }

    @Test
    public void setCenters() {
        List<CarServiceCenter> expected = new ArrayList<>();
        expected.add(center);
        customer.setCenters(expected);
        assertEquals(expected, customer.getCenters());
    }

    @Test
    public void setEmail() {
        String expected = "expected_value";
        customer.setEmail(expected);
        assertEquals(expected, customer.getEmail());
    }

    @Test
    public void setName() {
        String expected = "expected_value";
        customer.setName(expected);
        assertEquals(expected, customer.getName());
    }

    @Test
    public void setPhone() {
        String expected = "expected_value";
        customer.setPhone(expected);
        assertEquals(expected, customer.getPhone());
    }
}