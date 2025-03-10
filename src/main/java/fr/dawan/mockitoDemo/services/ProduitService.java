package fr.dawan.mockitoDemo.services;

import fr.dawan.mockitoDemo.entities.Produit;
import fr.dawan.mockitoDemo.repositories.IProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProduitService {

    @Autowired
    private IProduitRepository repository;

    public ProduitService(IProduitRepository repository) {
        this.repository = repository;
    }

    public ProduitService() {
    }

    List<Produit> getAll(){
        return repository.getAll();
    }

    void addProduit(Produit p){
        repository.addProduit(p);
    }

    void deleteProduit(Produit p){repository.deleteProduit(p);}

    void updateProduit(Produit p){repository.updateProduit(p);}


}
