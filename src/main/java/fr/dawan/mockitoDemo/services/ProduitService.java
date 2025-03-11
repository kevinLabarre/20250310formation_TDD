package fr.dawan.mockitoDemo.services;

import fr.dawan.mockitoDemo.entities.Produit;
import fr.dawan.mockitoDemo.repositories.IProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

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

    void updateProduit(Produit p){

        if (p== null) {
            throw new NullPointerException("Produit ne peut pas être null");
        }

        // On fait la verif du 'productNotFound' sur le getAll, car nous n'avons pas de getById
        List<Produit> produits  = repository.getAll();
        int index = produits.indexOf(p);

        if(index == -1){
            throw new NoSuchElementException("Produit non trouvé");
        }

        repository.updateProduit(p);
    }

    public Produit findById(int id){
        return repository.findById(id);
    }

}
