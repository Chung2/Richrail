package entity;

import javax.persistence.*;

/**
 * Created by nickw on 30-11-2017.
 */
@Entity
@Table(name = "TRAIN")
public class Train {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
