import entity.Component;
import entity.ComponentType;
import models.ServiceProvider;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung on 27-Nov-17.
 */
public class Main {
    public static void main(String [] args){
        System.out.println("010010000011001101101100011011000011000000100000010101110011000001110010011011000110010000100001001000010010000100100001");
//        List<ComponentType> types = ServiceProvider.getComponentTypeService().getAllComponentTypes();
//
//        for (ComponentType type : types) {
//
//            System.out.println(type.getName());
//
//        }

        List<Component> components = ServiceProvider.getComponentService().getAllComponents();
        for(Component component : components){
            System.out.println(component.getSeats());
            for (ComponentType type2 : component.getComponenttypes()){
                System.out.println(type2.getName());
            }
        }


    }
}