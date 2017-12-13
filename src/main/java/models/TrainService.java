package models;

import entity.Train;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.TrainDAO;

import java.util.List;

/**
 * Created by Chung on 04-Dec-17.
 */
public class TrainService {
    private TrainDAO trainDAO;

    public void setTrainDAO(TrainDAO trainDAO){
        this.trainDAO = trainDAO;
    }

    public List<Train> getAllTrains(){
        return trainDAO.getAll();
    }

    public Train getTrainById(int id) {
        return trainDAO.findById(id);
    }

    public Train getTrainByName(String name){
        return trainDAO.getTrainByName(name);
    }

    public void addTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.insert(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void updateTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.update(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void deleteTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.delete(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }

    }

}
