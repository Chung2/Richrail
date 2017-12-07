//package models;
//
//import entity.Train;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import persistence.TrainDAO;
//
//import java.util.List;
//
///**
// * Created by Chung on 04-Dec-17.
// */
//public class TrainService {
//    private TrainDAO trainDAO;
//
//    public void setTrainDAO(TrainDAO trainDAO){
//        this.trainDAO = trainDAO;
//    }
//
//    public List<Train> getAllTrains(){
//        Session session = trainDAO.getNewSession();
//        Query query = session.createQuery("FROM Train");
//        return trainDAO.selectTrain(session, query);
//    }
//}
