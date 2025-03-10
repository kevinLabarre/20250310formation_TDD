package fr.dawan.mockitoDemo.repositories;

import fr.dawan.mockitoDemo.entities.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitRepository implements IProduitRepository {

    private List<Produit> produits = new ArrayList<>();

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public void addProduit(Produit p) {
        produits.add(p);
    }

    @Override
    public void deleteProduit(Produit p) {
        produits.remove(p);
    }

    @Override
    public void updateProduit(Produit p) {
        int index = produits.indexOf(p);
        produits.get(index).setId(p.getId());
        produits.get(index).setDescription(p.getDescription());
    }
}
