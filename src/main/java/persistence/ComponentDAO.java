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
public interface ComponentDAO extends BaseDAO<Component> {

    List<Component> getAllByTrainId(int i);
    Component findByCode(String code);
    // TODO delete getComponentById because it already exists in ABstractDaoJpaImpl bestaat (findById)
//    Component getComponentById(int i);
    // TODO maybe delete getComponentByPre (never used)
    Component getComponentByPre(int i, int trainid);


}
