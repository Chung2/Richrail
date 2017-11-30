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
        Session session = cdao.getNewSession();
        Query query = session.createQuery("FROM ComponentType");
        return cdao.selectComponentTypes(session,query);
    }

}
