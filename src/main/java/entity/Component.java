package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */

@Entity
@Table(name = "Component")
public class Component {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seats")
    private int seats;

    @ManyToOne(fetch = FetchType.LAZY)
    //private List<ComponentType> componenttypes;
    private ComponentType componentType;


    public Component(int seats, ComponentType componentType) {
        this.seats = seats;
        this.componentType = componentType;
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

    public ComponentType getComponenttype() {
        return componentType;
    }

    public void setComponenttypes(ComponentType componentType) {
        this.componentType = componentType;
    }
}
