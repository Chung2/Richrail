package persistence;

import entity.ComponentType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */
public interface ComponentTypeDAO extends BaseDAO<ComponentType> {

    public ComponentType getComponentTypeByName (String name);

}
