package fr.dawan.mockitoDemo.services;

import fr.dawan.mockitoDemo.entities.Produit;
import fr.dawan.mockitoDemo.repositories.IProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


// todo : comment supprimer ce msg d'erreur : Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {

    @Mock
    private IProduitRepository mockRepository;

    @InjectMocks
    private ProduitService produitService;

    private List<Produit> prods;
    @BeforeEach
    public void setUp() {
        // Utilisé avant les annotations @InjectMocks et @Mock
        // mockRepository = mock(IProduitRepository.class);
        //produitService = new ProduitService(mockRepository);

        prods = new ArrayList<Produit>();
        prods.add(new Produit(1, "pc dell"));
        prods.add(new Produit(2, "écran Hp"));
    }

    @Test
    void getAll() {
        when(mockRepository.getAll()).thenReturn(prods);

        List<Produit> myList = produitService.getAll();

        assertEquals(2, myList.size());

    }

    @Test
    void addProduit() {
        Produit p = new Produit(3, "table");

        // Option 1: verify
        doNothing().when(mockRepository).addProduit(any(Produit.class));
        produitService.addProduit(p);
        // Vérification que la méthode add du repo a bien été appelée
        verify(mockRepository, times(1)).addProduit(p);

        // Option 2: capture de params
        ArgumentCaptor<Produit> valueCapture = ArgumentCaptor.forClass(Produit.class);
        doNothing().when(mockRepository).addProduit(valueCapture.capture());
        produitService.addProduit(p);
        assertEquals(p.getDescription(), valueCapture.getValue().getDescription());

        // Option 3: répondre à un appel d'une méthode void
        doAnswer((invocation) -> {
            Produit arg0 = invocation.getArgument(0);

            assertEquals(p.getDescription(), arg0.getDescription());
            assertEquals(p.getId(), arg0.getId());
            return null;
        }).when(mockRepository).addProduit(any(Produit.class));
        produitService.addProduit(p);
    }

    // On pourrait écrire d'autres tests, en essayant de supprimer un produit qui n'existe pas par exemple
    @Test
    void deleteProduit() {
        // Arrange
        Produit produit = new Produit(1, "ProduitTest");
        // Act
        produitService.deleteProduit(produit);
        // Assert
        verify(mockRepository, times(1)).deleteProduit(produit); // Vérifie que deleteProduit a bien été appelé une fois
    }


    // update réussie
    @Test
    void updateProduitOk() {
        // Arrange
        Produit produit = new Produit(2, "ProduitTest");
        // Act
        produitService.updateProduit(produit);
        // Assert
        verify(mockRepository, times(1)).updateProduit(produit); // Vérifie que updateProduit a bien été appelé une fois
    }

    //    Modifier le update en 3 tests -->
//        - Mise à jour réussie
//        - Produit non trouvé dans la liste
//        - Produit passé en param est null, je déclanche une erreur


}