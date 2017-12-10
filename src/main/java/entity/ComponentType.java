package entity;


import javax.persistence.*;
import java.lang.reflect.Field;
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

    public ComponentType() {}

    public ComponentType(int id ,String name) {
        this.id = id;
        this.name = name;
    }

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
