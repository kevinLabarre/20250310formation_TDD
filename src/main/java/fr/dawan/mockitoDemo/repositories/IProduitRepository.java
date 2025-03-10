package fr.dawan.mockitoDemo.repositories;

import fr.dawan.mockitoDemo.entities.Produit;

import java.util.List;

public interface IProduitRepository {

    List<Produit> getAll();

    void addProduit(Produit p);

    void deleteProduit(Produit p);

    void updateProduit(Produit p);


}
