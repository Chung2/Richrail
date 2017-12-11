package entity;


import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */

@Entity
@Table(name = "component")
public class Component {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seats")
    private int seats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "componenttype", foreignKey = @ForeignKey(name = "FK_component_componenttype"))
    private ComponentType componentType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train", nullable = false)
    private Train train;

    @OneToOne
    @JoinColumn(name = "predecessor", nullable = true)
    private Component predecessor;

    @OneToOne(mappedBy = "predecessor")
    private Component component;

    public Component() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

//    public Train getTrain() {
//        return train;
//    }
//
//    public void setTrain(Train train) {
//        this.train = train;
//    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Component getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Component predecessor) {
        this.predecessor = predecessor;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Component) {
            Component anderComponent = (Component) object;

            if (((Component) object).predecessor != null || anderComponent.getPredecessor() != null){
            if (this.componentType.getName().equals(anderComponent.componentType.getName())
                    && this.predecessor.id == anderComponent.predecessor.id
                    && this.train.getId() == anderComponent.train.getId()
                    && this.seats == anderComponent.seats
                    && this.predecessor.train.getId() == anderComponent.predecessor.train.getId()) {
                result = true;
            }
            }
            else{
                System.out.print("voorganger is null");
            }
        }

        return result;
    }
    }

