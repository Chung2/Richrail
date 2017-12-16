package models;

import entity.Component;
import log.Logging;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.ComponentDAO;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Chung on 03-Dec-17.
 */
public class ComponentService {
    private ComponentDAO comdao;

    public void setComponentDAO(ComponentDAO comdao){
        this.comdao = comdao;
    }

    public List<Component> getAllComponents(){
        List list;
        try {
            list = comdao.getAll();
            Logging.writeLine("[SELECT] Getting all components");
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            list = null;
        }
        return list;
    }

    public Component getComponentById(int id) {
        Component component;
        try {
            component = comdao.findById(id);
            Logging.writeLine("[SELECT] Getting component by ID: " + id);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            component = null;
        }
        return component;
    }

    public Component getComponentByCode(String code) {
        Component component;
        try {
            component = comdao.findByCode(code);
            Logging.writeLine("[SELECT] Getting component by CODE: " + code);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            component = null;
        }
        return component;
    }

    public List<Component> getComponentsByTrainId(int id) {
        List list;
        try {
            list = comdao.getAllByTrainId(id);
            Logging.writeLine("[SELECT] Getting all component by TRAIN ID: " + id);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            list = null;
        }
        return list;
    }

    public void addComponent(Component cp) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            comdao.insert(cp);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[INSERT] Adding new component ( ID :" + cp.getId() + " , CODE: " + cp.getCode() + ", SEATS: " + cp.getSeats() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void updateComponent(Component cp) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            comdao.update(cp);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[UPDATE] Updating component ( ID :" + cp.getId() + " , CODE: " + cp.getCode() + ", SEATS: " + cp.getSeats() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void deleteComponent(Component cp) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            comdao.delete(cp);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[DELETE] Deleting component ( ID :" + cp.getId() + " , CODE: " + cp.getCode() + ", SEATS: " + cp.getSeats() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }
}
