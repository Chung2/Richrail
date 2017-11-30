package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by nickw on 30-11-2017.
 */
public class BaseDAO {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    protected final SessionFactory getConnection(){
        return factory;
    }
}
