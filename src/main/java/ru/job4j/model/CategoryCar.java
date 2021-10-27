package ru.job4j.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class CategoryCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public CategoryCar() {
    }

    public CategoryCar(String name) {
        this.name = name;
    }

    public CategoryCar(int id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryCar categoryCar = (CategoryCar) o;
        return id == categoryCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
