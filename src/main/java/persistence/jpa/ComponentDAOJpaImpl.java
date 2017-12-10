package persistence.jpa;

import entity.Component;
import org.hibernate.Hibernate;
import persistence.ComponentDAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by nickw on 7-12-2017.
 */
public class ComponentDAOJpaImpl extends AbstractDaoJpaImpl<Component> implements ComponentDAO{

    public ComponentDAOJpaImpl(EntityManager entityManager){
        super(entityManager);
    }

    public Component getComponentByTrain(String s){
        List<Component> components = (List<Component>) em.createQuery("from Component where train =:s").setParameter("s",s).getResultList();
        if(!components.isEmpty()){
            return components.get(0);
        }
        return null;
    }

    // Bij klasse AbstractDaoJpaImpl zit al de function: findById
    public Component getComponentById(int i){
        List<Component> components = (List<Component>) em.createQuery("from Component where id =:i").setParameter("i",i).getResultList();
        if(!components.isEmpty()){
            return components.get(0);
        }
        return null;
    }

    public List<Component> getComponentsByTrain(int i){
        System.out.println(i);
        List<Component> components = (List<Component>) em.createQuery("from Component where train ="+i).getResultList();

        if(!components.isEmpty()){
            return components;
        }
        return null;
    }

    public Component getComponentByPre(int i, int trainid){
        List<Component> components = (List<Component>) em.createQuery("from Component where id="+i+" and train ="+trainid).getResultList();
        if(!components.isEmpty()){
            return components.get(0);
        }
        return null;
    }
}
