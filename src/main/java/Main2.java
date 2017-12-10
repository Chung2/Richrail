import entity.Component;
import entity.ComponentType;
import entity.Train;
//import models.ServiceProvider;
import models.ServiceProvider;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;
import persistence.ComponentDAO;
import persistence.ComponentTypeDAO;
import persistence.TrainDAO;
import persistence.jpa.ComponentDAOJpaImpl;
import persistence.jpa.ComponentTypeDAOJpaImpl;
import persistence.jpa.HibernateUtil;
import persistence.jpa.TrainDAOJpaImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung on 27-Nov-17.
 */
public class Main2 {

    public static void main(String [] args){
        System.out.println("Hello develop2");
        System.out.println("010010000011001101101100011011000011000000100000010101110011000001110010011011000110010000100001001000010010000100100001");

        ComponentType ct = ServiceProvider.getComponentTypeService().getComponentTypeByName("lokomotief");
        System.out.println(ct.getId() + " " + ct.getName());

        List<ComponentType> ctlist = ServiceProvider.getComponentTypeService().getAllComponentTypes();
        for (ComponentType ctresult : ctlist) {
            System.out.println(ctresult.getId() + " " + ctresult.getName());
        }

        Component c = ServiceProvider.getComponentService().getComponentById(1);
        System.out.println(c.getId() + " " + c.getComponentType().getName());

        List<Component> componentList = ServiceProvider.getComponentService().getComponentsByTrainId(1);
        for (Component c2 : componentList) {
            System.out.println(c2.getId() + " " + c2.getComponentType().getName() );
        }

        Train t = ServiceProvider.getTrainService().getTrainById(1);
        for (Component c3 : t.getComponents()) {
            System.out.println(c3.getId() + " " + c3.getComponentType().getName());
        }



//        ServiceProvider.getEntityManager().getTransaction().begin();
//
//        ComponentType ct2 = new ComponentType(3,"test");
////        ct2.setName("test");
////        ct2.setId(3);
//
////        ServiceProvider.getComponentTypeService().addComponentType(ct2);
//       ServiceProvider.getComponentTypeService().deleteComponentType(ct2);
//
//        ServiceProvider.getEntityManager().getTransaction().commit();


//
//        em.close();

    }
}