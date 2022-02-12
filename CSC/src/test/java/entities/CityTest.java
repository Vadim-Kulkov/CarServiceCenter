package entities;

import org.junit.*;

import static org.junit.Assert.*;

public class CityTest {

    private static City city;

    @BeforeClass
    public static void setUp() {
        city = new City();
    }

    @AfterClass
    public static void tearDown() {
        city = null;
    }

    @Test
    public void setCode() {
        String expected = "expected_value";
        city.setCode(expected);
        assertEquals(expected, city.getCode());
    }

    @Test
    public void setDefaultCity() {
        Boolean expected = true;
        city.setDefaultCity(expected);
        assertEquals(expected, city.getDefaultCity());
    }

    @Test
    public void setName() {
        String expected = "expected_value";
        city.setName(expected);
        assertEquals(expected, city.getName());
    }
}
