package test_1;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import utils.HibernateUtil;

public class Test1 {
    @Test
    public void getSession() {
        Session actualSession;
        actualSession = HibernateUtil
                .getSessionFactory()
                .openSession();
        Assert.assertEquals(null, actualSession);
    }
}
