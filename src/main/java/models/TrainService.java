package models;

import entity.Component;
import entity.Train;
import log.Logging;
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
        try {
            List list = trainDAO.getAll();
            Logging.writeLine("[SELECT] Getting all trains");
            return list;
        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            return null;
        }
    }

    public Train getTrainById(int id) {
        try {
            Train train = trainDAO.findById(id);
            Logging.writeLine("[SELECT] Getting train by id: " + id);
            return train;
        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            return null;
        }
    }

    public Train getTrainByName(String name){
        try {
            Train train = trainDAO.getTrainByName(name);
            Logging.writeLine("[SELECT] Getting train by id: " + name);
            return train;
        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            return null;
        }
    }

    public void addTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.insert(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[INSERT] Adding new train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );
        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    public void updateTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.update(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[UPDATE] Updating train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );
        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    // TO DO: if train gets deleted, component needs to get updated so components wont get deleted
    public void deleteTrain(Train train) {
        try {
            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.delete(train);
            ServiceProvider.getEntityManager().getTransaction().commit();
            Logging.writeLine("[DELETE] Deleting train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );

        } catch (Exception e) {
            Logging.writeLine(e.getMessage());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }

    }

}
