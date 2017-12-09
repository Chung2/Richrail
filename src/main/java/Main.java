import entity.Component;
import entity.ComponentType;
import entity.Train;
//import models.ServiceProvider;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;
import persistence.ComponentDAO;
import persistence.ComponentTypeDAO;
import persistence.jpa.ComponentDAOJpaImpl;
import persistence.jpa.ComponentTypeDAOJpaImpl;
import persistence.jpa.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung on 27-Nov-17.
 */
public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String [] args){
        System.out.println("Hello develop2");
        System.out.println("010010000011001101101100011011000011000000100000010101110011000001110010011011000110010000100001001000010010000100100001");
//        List<ComponentType> types = ServiceProvider.getComponentTypeService().getAllComponentTypes();
//
//        for (ComponentType type : types) {
//
//            System.out.println(type.getName());
//
//        }

//        List<Component> components = ServiceProvider.getComponentService().getAllComponents();
//        for(Component component : components){
//            System.out.println(component.getSeats());
//            for (ComponentType type2 : component.getComponenttypes()){
//                System.out.println(type2.getName());
//            }
//        }

//        List<Component> components = ServiceProvider.getComponentService().getAllComponents();
//        for(Component component : components) {
//            System.out.println(component.getId() + " " + component.getSeats() + " " + component.getComponentType().getName());
//        }

//        List<Train> trains = ServiceProvider.getTrainService().getAllTrains();
//        for(Train train: trains){
//            System.out.println(train.getId()+" "+train.getName() );
//            for (Component component: train.getComponents()){
//                System.out.println(component.getId()+" "+component.getSeats()+" "+component.getComponentType().getName());
//            }
//        }

//        List<Train> trains = ServiceProvider.getTrainService().getAllTrains();
//        for(Train train : trains) {
//            System.out.println(train.getName());
//            for (Component c : train.getComponents()) {
//                System.out.println(c.getId());
//            }
//        }

        EntityManager em = null;
        try {
            entityManagerFactory = HibernateUtil.getEntityManagerFactory();
            em = entityManagerFactory.createEntityManager();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ComponentDAO componentDAO = new ComponentDAOJpaImpl(em);
        //ComponentTypeDAO ctDAO = new ComponentTypeDAOJpaImpl(em);

        em.getTransaction().begin();
//
//        List<ComponentType> ctl = ctDAO.getAll();
//        for (ComponentType cttl : ctl) {
//            System.out.println(ctDAO.getComponentTypeByName(cttl.getName()).getId());
//        }

        //System.out.println(ctDAO.getComponentTypeByName("lokomotief").getId());
        //System.out.println(componentDAO.getComponentById(1).getSeats());
//        for (Component component: componentDAO.getComponentsByTrain(1)){
//            System.out.println(component.getTrain().getName()+" "+component.getId() +" "+component.getComponentType().getName());
//        }
////        for (ComponentType c : ct) {
//            System.out.println(c.getName());
//        }
        System.out.println(componentDAO.getComponentByPre(2,1).getPredecessor().getComponentType().getName());

        em.getTransaction().commit();

        em.close();

    }
}