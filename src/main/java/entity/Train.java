package entity;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */
@Entity
@Table(name = "TRAIN")
public class Train {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainid;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "train", orphanRemoval = false)
    private List<Component> Components = new ArrayList<Component>();

    public Train() {
    }

    public int getId() {
        return trainid;
    }

    public void setId(int trainid) {
        this.trainid = trainid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return Components;
    }

    public void setComponents(List<Component> components) {
        this.Components = components;
    }

    public void addComponent(Component component) {
        component.setTrain(this);
        this.Components.add(component);
    }

}


