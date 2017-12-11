package models;

import entity.ComponentType;
import org.hibernate.query.Query;
import org.hibernate.Session;
import persistence.ComponentTypeDAO;

import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */
public class ComponentTypeService {

    private ComponentTypeDAO cdao;

    public void setComponentTypeDAO(ComponentTypeDAO cdao) {
        this.cdao = cdao;
    }

    public List<ComponentType> getAllComponentTypes() {
        return cdao.getAll();
    }

    public ComponentType getComponentTypeById(int id) {
        return cdao.findById(id);
    }

    public ComponentType getComponentTypeByName(String name) {
        return cdao.findByType(name);
    }

    public void addComponentType(ComponentType ct) {
        ServiceProvider.getEntityManager().getTransaction().begin();
        cdao.insert(ct);
        ServiceProvider.getEntityManager().getTransaction().commit();
    }

    public void updateComponentType(ComponentType ct) {
        ServiceProvider.getEntityManager().getTransaction().begin();
        cdao.update(ct);
        ServiceProvider.getEntityManager().getTransaction().commit();
    }

    public void deleteComponentType(ComponentType ct) {
        ServiceProvider.getEntityManager().getTransaction().begin();
        cdao.delete(ct);
        ServiceProvider.getEntityManager().getTransaction().commit();
    }
}
