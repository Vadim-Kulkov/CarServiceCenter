package integration.dao;

import dao.CompanyDao;
import entities.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CompanyDaoTest {

    private static CompanyDao companyDao = new CompanyDao();

    static Company company = new Company("test_local_Id");

    @BeforeClass
    public static void setUp() {
        company.setCenters(null);
        company.setEmail("test_email1");
        company.setName("test_name1");
        company.setPhone("3281");
        companyDao.save(company);
    }

    @AfterClass
    public static void tearDown() {
        companyDao.delete(company);
    }

    @Test
    public void getAllAsList() {
        List<Company> expected = new ArrayList<>();
        expected.add(company);
        assertEquals(expected, companyDao.getAllAsList());
    }

    @Test
    public void save() {
        assertEquals(company, companyDao.getById("3281"));
    }

    @Test
    public void update() {
        String expected = "new_email";
        company.setEmail(expected);

        companyDao.update(company);
        assertEquals(expected, companyDao.getById("3281").getEmail());
    }
}
