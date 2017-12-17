package entity;


import javafx.scene.image.Image;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.Blob;
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

    @Column(name = "image")
    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
