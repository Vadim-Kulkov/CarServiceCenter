package entities;

import org.junit.*;

import static org.junit.Assert.*;

public class IndividualTest {

    private static Individual individual;

    @BeforeClass
    public static void setUp() throws Exception {
        individual = new Individual();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        individual = null;
    }

    @Test
    public void setPassportNo() {
        String expected = "expected_value";
        individual.setPassportNo(expected);
        assertEquals(expected, individual.getPassportNo());
    }
}
