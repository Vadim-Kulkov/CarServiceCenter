package unit;

import dao.CarServiceCenterDao;
import dao.CityDao;
import entities.CarServiceCenter;
import entities.City;
import org.junit.*;

public class CarServiceCenterDaoTest {

    private CityDao cityDao = new CityDao();
    private CarServiceCenterDao carServiceCenterDao = new CarServiceCenterDao();

    private final City city = new City("name", "1242", true, null);
    private final CarServiceCenter center = new CarServiceCenter(
            "test_name", "832", null, city, "1111", null, null
    );

    @Before
    public void setUp() {
        cityDao.save(city);
        carServiceCenterDao.save(center);
    }

    @After
    public void tearDown() {
        carServiceCenterDao.delete(center);
        cityDao.delete(city);
    }

    @Test
    public void getById_Should_Return_Not_Null() {
        Assert.assertNotNull(carServiceCenterDao.getById(center.getName()));
    }

    @Test
    public void getById_Should_Return_Saved_Entity() {
        Assert.assertEquals(center, carServiceCenterDao.getById(center.getName()));
    }

    @Test
    public void getAllAsList_Should_Return_List_With_Length_equalTo_1() {
        Assert.assertEquals(1, carServiceCenterDao.getAllAsList().size());
    }

    @Test
    public void save_Positive_Case() {
        CarServiceCenter expected = new CarServiceCenter("expected", "1", null, city, "4", null, null);
        carServiceCenterDao.save(expected);
        Assert.assertEquals(expected, carServiceCenterDao.getById("expected"));
        carServiceCenterDao.delete(expected);
    }

    @Test(expected = NullPointerException.class)
    public void save_Should_Throw_NullPointerException() {
        carServiceCenterDao.save(null);
    }

    @Test
    public void update_Should_Change_Statement() {
        CarServiceCenter testCenter = new CarServiceCenter("expected", "1", null, city, "4", null, null);
        carServiceCenterDao.save(testCenter);
        String new_phone = "22";
        testCenter.setPhone(new_phone);
        carServiceCenterDao.update(testCenter);
        Assert.assertEquals(carServiceCenterDao.getById("expected").getPhone(), new_phone);
        carServiceCenterDao.delete(testCenter);
    }

    @Test
    public void should_Return_Null_After_Delete() {
        CarServiceCenter testCenter = new CarServiceCenter("expected", "1", null, city, "4", null, null);
        carServiceCenterDao.save(testCenter);
        carServiceCenterDao.delete(testCenter);
        Assert.assertNull(carServiceCenterDao.getById(testCenter.getName()));
    }
}
