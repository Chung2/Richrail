package persistence.jpa;

import entity.Train;
import persistence.TrainDAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by nickw on 7-12-2017.
 */
public class TrainDAOJpaImpl extends AbstractDaoJpaImpl<Train> implements TrainDAO{

    public TrainDAOJpaImpl(EntityManager entityManager){
        super(entityManager);
    }

    public Train getTrainByName(String s){
        List<Train> trains = (List<Train>) em.createQuery("From Train where name = :s").setParameter("s",s).getResultList();

        if(!trains.isEmpty()){
            return trains.get(0);
        }

        return null;
    }

    // method bestaat al in AbstractDaoJpaImpl (getAll() )
    public List<Train> getAllTrains(){
        List<Train> trains = (List<Train>) em.createQuery("from Train").getResultList();
        if(!trains.isEmpty()){
            return trains;
        }

        return null;
    }


}
