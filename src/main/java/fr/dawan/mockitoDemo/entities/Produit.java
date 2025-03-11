package fr.dawan.mockitoDemo.entities;

import java.util.Objects;

public class Produit {
    private int id;

    private String description;

    public Produit(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Produit(){}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id == produit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
