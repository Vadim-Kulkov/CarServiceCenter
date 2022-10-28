package integration.dao;

import dao.IndividualDao;
import entities.Individual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IndividualDaoTest {

    private static IndividualDao individualDao = new IndividualDao();


    static Individual individual = new Individual("test_passport_no");

    @BeforeClass
    public static void setUp() {
        individual.setCenters(null);
        individual.setEmail("test_email");
        individual.setName("test_name");
        individual.setPhone("32811");
        individualDao.save(individual);
    }

    @AfterClass
    public static void tearDown() {
        individualDao.delete(individual);
    }

    @Test
    public void getAllAsList() {
        List<Individual> expected = new ArrayList<>();
        expected.add(individual);
        assertEquals(expected, individualDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(individual, individualDao.getById("32811"));
    }

    @Test
    public void update() {
        String expected = "new_email";
        individual.setEmail(expected);

        individualDao.update(individual);
        assertEquals(expected, individualDao.getById("32811").getEmail());
    }
}
