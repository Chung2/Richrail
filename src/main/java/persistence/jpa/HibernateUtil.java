package persistence.jpa;

/**
 * Created by nickw on 7-12-2017.
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static String mysqlcfg = "pafr.richrail.jpa.mysql";
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(mysqlcfg );
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}