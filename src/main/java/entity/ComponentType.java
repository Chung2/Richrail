package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */
@Entity
@Table(name = "ComponentType")
public class ComponentType {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ComponentType")
//    private List<Component> Components;

    public ComponentType() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
