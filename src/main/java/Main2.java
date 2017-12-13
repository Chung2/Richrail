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
//
//        ComponentType ct = ServiceProvider.getComponentTypeService().getComponentTypeByName("lokomotief");
//        System.out.println(ct.getId() + " " + ct.getName());
//
//        List<ComponentType> ctlist = ServiceProvider.getComponentTypeService().getAllComponentTypes();
//        for (ComponentType ctresult : ctlist) {
//            System.out.println(ctresult.getId() + " " + ctresult.getName());
//        }
//
//        Component c = ServiceProvider.getComponentService().getComponentById(1);
//        System.out.println(c.getId() + " " + c.getComponentType().getName());
//
//        List<Component> componentList = ServiceProvider.getComponentService().getComponentsByTrainId(1);
//        for (Component c2 : componentList) {
//            System.out.println(c2.getId() + " " + c2.getComponentType().getName() );
//        }
//
//        Train t = ServiceProvider.getTrainService().getTrainById(1);
//        for (Component c3 : t.getComponents()) {
//            System.out.println(c3.getId() + " " + c3.getComponentType().getName());
//        }


// INSERT EXAMPLE

//        ComponentType ct2 = new ComponentType();
//        ct2.setName("test");
//        ServiceProvider.getComponentTypeService().addComponentType(ct2);

// DELETE EXAMPLE
//        ComponentType ct3 = ServiceProvider.getComponentTypeService().getComponentTypeById(4);
//        ServiceProvider.getComponentTypeService().deleteComponentType(ct3);

// UPDATE EXAMPLE
//        Component componentpredessesor = ServiceProvider.getComponentService().getComponentById(2);
//        ComponentType test = ServiceProvider.getComponentTypeService().getComponentTypeById(3);
//        test.setName("Wagon2");
//        ServiceProvider.getComponentTypeService().updateComponentType(test);

        ComponentType ct1 = ServiceProvider.getComponentTypeService().getComponentTypeById(1);
        ComponentType ct2 = ServiceProvider.getComponentTypeService().getComponentTypeById(2);
        ComponentType ct3 = ServiceProvider.getComponentTypeService().getComponentTypeById(3);

//        Train train = new Train();
//        train.setName("Main2");
//
//        Component component1 = new Component();
//        component1.setSeats(10);
//        component1.setComponentType(ct1);
////        component1.setTrain(train);
//        component1.setOrder(1);
//
//        Component component2 = new Component();
//        component2.setSeats(100);
//        component2.setComponentType(ct2);
////        component2.setTrain(train);
//        component2.setOrder(2);
//
//        Component component3 = new Component();
//        component3.setSeats(200);
//        component3.setComponentType(ct3);
////        component3.setTrain(train);
//        component3.setOrder(3);
//
//        train.addComponent(component1);
//        train.addComponent(component2);
//        train.addComponent(component3);
//
// //       train.setComponents(components);
//
//        ServiceProvider.getTrainService().addTrain(train);

        Train train = ServiceProvider.getTrainService().getTrainById(1);
        Component component = new Component();
        component.setSeats(10);
        component.setComponentType(ct3);
        component.setCode("");
        component.setTrain(train);

        ServiceProvider.getComponentService().addComponent(component);

//        Component cpt = ServiceProvider.getComponentService().getComponentById(6);
//        cpt.setSeats(200);
//        ServiceProvider.getComponentService().updateComponent(cpt);

//
//        em.close();

    }
}