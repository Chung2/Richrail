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
public class ComponentTypeDAO extends BaseDAO {

    public Session getNewSession(){
        return super.getConnection().openSession();
    }

    public List<ComponentType> selectComponentTypes (Session session, Query query) {

        Transaction tx = null;
        List<ComponentType> componentTypes = new ArrayList<ComponentType>();
        try{
            tx = session.beginTransaction();
            List results = query.list();
            for (Iterator iterator = results.iterator(); iterator.hasNext();) {
                ComponentType componentType = (ComponentType) iterator.next();
                componentTypes.add(componentType);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            session.close();
            return null;
        }

        session.close();

        if (componentTypes.isEmpty()) {
            return null;
        }
        return componentTypes;

    }

}
