package persistence;

import entity.Component;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Chung on 03-Dec-17.
 */
public class ComponentDAO extends BaseDAO{

    public Session getNewSession(){
     return super.getConnection().openSession();
    }

    public List<Component> selectComponent (Session session, Query query){
        Transaction tx = null;
        List<Component> components = new ArrayList<Component>();
        try{
            tx = session.beginTransaction();
            List results = query.list();
            for(Iterator iterator = results.iterator(); iterator.hasNext();){
                Component component = (Component) iterator.next();
                components.add(component);

            }
            tx.commit();
        }catch(HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            session.close();
            return null;
        }

        session.close();
        if(components.isEmpty()){
            return null;
        }
        return components;
    }
}
