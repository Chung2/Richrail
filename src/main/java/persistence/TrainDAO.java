package persistence;

import entity.Component;
import entity.Train;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Chung on 04-Dec-17.
 */
public class TrainDAO extends BaseDAO{
    public Session getNewSession(){
        return super.getConnection().openSession();
    }

    public List<Train> selectTrain (Session session, Query query){
        Transaction tx = null;
        List<Train> trains = new ArrayList<Train>();
        try{
            tx = session.beginTransaction();
            List results = query.list();
            for(Iterator iterator = results.iterator(); iterator.hasNext();){
                Train train = (Train) iterator.next();
                trains.add(train);
            }
            tx.commit();

        }catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            session.close();
            return null;
        }
        return trains;

    }
}
