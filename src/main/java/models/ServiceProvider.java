package models;

import persistence.ComponentTypeDAO;

/**
 * Created by nickw on 30-11-2017.
 */
public class ServiceProvider {

    private static ComponentTypeService componentTypeService = new ComponentTypeService();
    private static ComponentTypeDAO cdao = new ComponentTypeDAO();

    public static ComponentTypeService getComponentTypeService() {
        componentTypeService.setComponentTypeDAO(cdao);
        return componentTypeService;
    }
}
