package models;

import entity.Component;
import persistence.ComponentDAO;
import persistence.ComponentTypeDAO;

/**
 * Created by nickw on 30-11-2017.
 */
public class ServiceProvider {

    private static ComponentTypeService componentTypeService = new ComponentTypeService();
    private static ComponentTypeDAO cdao = new ComponentTypeDAO();

    private static ComponentService componentService = new ComponentService();
    private static ComponentDAO comdao = new ComponentDAO();

    public static ComponentTypeService getComponentTypeService() {
        componentTypeService.setComponentTypeDAO(cdao);
        return componentTypeService;
    }

    public static ComponentService getComponentService(){
        componentService.setComponentDAO(comdao);
        return componentService;
    }
}
