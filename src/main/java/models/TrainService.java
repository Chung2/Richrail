package models;

import entity.Component;
import entity.Train;
import log.Logging;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.TrainDAO;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
        List list;
        try {
            list = trainDAO.getAll();
            Logging.writeLine("[SELECT] Getting all trains");
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            list = null;
        }
        return list;
    }

    public Train getTrainById(int id) {
        Train train;
        try {
            train = trainDAO.findById(id);
            Logging.writeLine("[SELECT] Getting train by ID: " + id);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            train = null;
        }
        return train;
    }

    public Train getTrainByName(String name){
        Train train;
        try {
            train = trainDAO.findByName(name);
            Logging.writeLine("[SELECT] Getting train by NAME: " + name);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            train = null;
        }
        return train;
    }

    public void addTrain(Train train) {
        try {

            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.insert(train);
            ServiceProvider.getEntityManager().getTransaction().commit();

            Logging.writeLine("[INSERT] Adding new train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    // Update a Train Object in the database
    public void updateTrain(Train train) {
        try {

            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.update(train);
            ServiceProvider.getEntityManager().getTransaction().commit();

            Logging.writeLine("[UPDATE] Updating train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }
    }

    // Delete a Train Object in the database
    public void deleteTrain(Train train) {
        try {

            List<Component> components = train.getComponents();

            // Remove all current components from the train
            for (Component component : components) {
                component.setTrain(null);
                ServiceProvider.getComponentService().updateComponent(component);
            }

            // Remove the components from the train
            train.setComponents(new ArrayList<Component>());

            ServiceProvider.getEntityManager().getTransaction().begin();
            trainDAO.delete(train);
            ServiceProvider.getEntityManager().getTransaction().commit();

            Logging.writeLine("[DELETE] Deleting train ( ID :" + train.getId() + " , NAME: " + train.getName() + " )" );

        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Logging.writeLine(errors.toString());
            ServiceProvider.getEntityManager().getTransaction().rollback();
        }

    }

}
