package entity;

import javax.persistence.*;
import java.lang.reflect.Field;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
//    @JoinColumn (name="train")
    private List<Component> Components;

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
        Components = components;
    }

}


