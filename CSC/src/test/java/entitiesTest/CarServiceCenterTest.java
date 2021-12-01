package entitiesTest;

import entities.CarServiceCenter;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class CarServiceCenterTest {

    @Test
    public void setName() {
        String test_value = "value";
        String actual_value = "value";
        CarServiceCenter carServiceCenter = new CarServiceCenter();
        carServiceCenter.setName(test_value);
        assertEquals(actual_value, carServiceCenter.getName());
    }
}
