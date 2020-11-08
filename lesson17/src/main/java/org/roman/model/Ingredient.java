package org.roman.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "recipe_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Recipe recipe;
}
