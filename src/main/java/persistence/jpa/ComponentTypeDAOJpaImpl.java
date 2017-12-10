package persistence.jpa;

import entity.ComponentType;
import persistence.ComponentTypeDAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by nickw on 7-12-2017.
 */
public class ComponentTypeDAOJpaImpl extends AbstractDaoJpaImpl<ComponentType> implements ComponentTypeDAO{

    public ComponentTypeDAOJpaImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public ComponentType findByType(String type) {
        System.out.println("INPUT: " + type);
        List<ComponentType> componentTypes = (List<ComponentType>) em.createQuery("from ComponentType where name = :type").setParameter("type", type).getResultList();
//        Query query =  em.createQuery("from ComponentType where name = :name");
//        query.setParameter("name",name);
//        List<ComponentType> componentTypes = query.getResultList();

        for (ComponentType ct : componentTypes) {
            System.out.println("DEBUG STRING: " + ct.getName());
        }

        if (!componentTypes.isEmpty()) {
            return componentTypes.get(0);
        }
        return null;
    }

}
