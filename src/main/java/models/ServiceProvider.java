package models;

import persistence.ComponentDAO;
import persistence.ComponentTypeDAO;
import persistence.TrainDAO;
import persistence.jpa.ComponentDAOJpaImpl;
import persistence.jpa.ComponentTypeDAOJpaImpl;
import persistence.jpa.HibernateUtil;
import persistence.jpa.TrainDAOJpaImpl;

import javax.persistence.EntityManager;

/**
 * Created by nickw on 30-11-2017.
 */
public class ServiceProvider {

    private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private static ComponentTypeService componentTypeService = new ComponentTypeService();
    private static ComponentTypeDAO cdao = new ComponentTypeDAOJpaImpl(em);

    private static ComponentService componentService = new ComponentService();
    private static ComponentDAO comdao = new ComponentDAOJpaImpl(em);

    private static TrainService trainService = new TrainService();
    private static TrainDAO trainDAO = new TrainDAOJpaImpl(em);

    static {
        componentTypeService.setComponentTypeDAO(cdao);
        componentService.setComponentDAO(comdao);
        trainService.setTrainDAO(trainDAO);
    }

    public static EntityManager getEntityManager(){
        return em;
    }

    public static ComponentTypeService getComponentTypeService() {
        return componentTypeService;
    }

    public static ComponentService getComponentService(){
        return componentService;
    }

    public static TrainService getTrainService(){
        return trainService;
    }

}
