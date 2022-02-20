package integration.entities;

import entities.Company;
import org.junit.*;

import static org.junit.Assert.*;

public class CompanyTest {

    private static Company company;

    @BeforeClass
    public static void setUp() throws Exception {
        company = new Company();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        company = null;
    }

    @Test
    public void setInn() {
        String expected = "expected_value";
        company.setLocalId(expected);
        assertEquals(expected, company.getLocalId());
    }
}
