package app;

import org.hibernate.Session;
import utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session;
        session = HibernateUtil
                .getSessionFactory()
                .openSession();
        session.close();
    }
}
