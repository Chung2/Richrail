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

    public List<Component> getAllByTrainId(int i){
        List<Component> components = (List<Component>) em.createQuery("from Component where train ="+i).getResultList();
        if(!components.isEmpty()){
            return components;
        }
        return null;
    }

    @Override
    public Component findByCode(String code) {
        List<Component> components = (List<Component>) em.createQuery("from Component where code = :code").setParameter("code", code).getResultList();
        if (!components.isEmpty()) {
            return components.get(0);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        em.createQuery("DELETE Component where id =:id").setParameter("id",id).executeUpdate();
    }

}
