package models;

import entity.ComponentType;
import log.Logging;
import org.hibernate.query.Query;
import org.hibernate.Session;
import persistence.ComponentTypeDAO;

import java.io.PrintWriter;
import java.io.StringWriter;
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
        List list;
        try {
            list = cdao.getAll();
            Logging.writeLine("[SELECT] Getting all componentsTypes");
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            list = null;
        }
        return list;
    }

    public ComponentType getComponentTypeById(int id) {
        ComponentType componentType;
        try {
            componentType = cdao.findById(id);
            Logging.writeLine("[SELECT] Getting componentsType by ID :" + id);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            componentType = null;
        }
        return componentType;
    }

    public ComponentType getComponentTypeByName(String name) {
        ComponentType componentType;
        try {
            componentType = cdao.findByType(name);
            Logging.writeLine("[SELECT] Getting componentsType by NAME :" + name);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            componentType = null;
        }
        return componentType;
    }

    public void addComponentType(ComponentType ct) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            cdao.insert(ct);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[INSERT] Adding new componentType ( ID :" + ct.getId() + " , NAME: " + ct.getName() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void updateComponentType(ComponentType ct) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            cdao.update(ct);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[UPDATE] Updating componentType ( ID :" + ct.getId() + " , NAME: " + ct.getName() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void deleteComponentType(ComponentType ct) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            cdao.delete(ct);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[DELETE] Deleting componentType ( ID :" + ct.getId() + " , NAME: " + ct.getName() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }
}
