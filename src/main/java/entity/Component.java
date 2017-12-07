package entity;


import javax.persistence.*;
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
    @JoinColumn(name = "componenttype",foreignKey = @ForeignKey(name = "FK_component_componenttype"))
    private ComponentType componentType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train", nullable = false)
    private Train train;

    @OneToOne
    @JoinColumn(name="predecessor")
    private Component component;

    @OneToOne(mappedBy = "component")
    private Component predecessor;

    public Component() {}

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

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
