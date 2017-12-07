//package models;
//
//import entity.Component;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import persistence.ComponentDAO;
//
//import java.util.List;
//
///**
// * Created by Chung on 03-Dec-17.
// */
//public class ComponentService {
//    private ComponentDAO comdao;
//
//    public void setComponentDAO(ComponentDAO comdao){
//        this.comdao = comdao;
//    }
//
//    public List<Component> getAllComponents(){
//        Session session = comdao.getNewSession();
//        Query query = session.createQuery("FROM Component");
//        return comdao.selectComponent(session,query);
//    }
//
////    public List<Component> getAllComponents(){
////        return comdao.findAll();
////    }
//}
