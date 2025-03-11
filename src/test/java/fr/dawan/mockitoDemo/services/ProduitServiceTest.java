package fr.dawan.mockitoDemo.services;

import fr.dawan.mockitoDemo.entities.Produit;
import fr.dawan.mockitoDemo.repositories.IProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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

        // SI j'utilise 'produit' en paramètre: l'action configurée sera exécutée que si j'appelle le delete avec exactement
        // le produit passé en paramètre
        doNothing().when(mockRepository).deleteProduit(produit);
//        doNothing().when(mockRepository).deleteProduit(any(Produit.class));

        produitService.deleteProduit(produit);
        // Assert
        verify(mockRepository, times(1)).deleteProduit(produit); // Vérifie que deleteProduit a bien été appelé une fois
    }


    @Nested
    public class updateProduit_test {

        //    Modifier le update en 3 tests -->
//        - Mise à jour réussie
//        - Produit non trouvé dans la liste
//        - Produit passé en param est null, je déclanche une erreur
        @Test
        void updateProduitOk() {
            // Arrange
            Produit produit = new Produit(2, "ProduitTest");
            // Act
            when(mockRepository.getAll()).thenReturn(prods);
            doNothing().when(mockRepository).updateProduit(produit);
            produitService.updateProduit(produit);
            // Assert
            verify(mockRepository, times(1)).updateProduit(produit); // Vérifie que updateProduit a bien été appelé une fois
        }

        @Test
        void productNotFound() {
            Produit nonExistant = new Produit(265, "ProduitTest");

            when(mockRepository.getAll()).thenReturn(prods);

        //    Pas besoin de ce 'stubbing -> car je sors de la méthode avec un throw avant de déclencher le repo.updateProduit()
//            doNothing().when(mockRepository).updateProduit(any(Produit.class));

            assertThrows(NoSuchElementException.class, () -> produitService.updateProduit(nonExistant), "Devrait " +
                    "déclencher une erreur, car tentative de mettre un produit inxistant à jour");
        }

        @Test
        void updateNullProduit(){
            Produit nullProduit = null;

            // Pas besoin de ce 'stubbing -> car je sors de la méthode avec un throw avant de déclencher le repo.updateProduit()
            // doNothing().when(mockRepository).updateProduit(nullProduit);

            assertThrows(NullPointerException.class, () -> produitService.updateProduit(nullProduit));

        }

    }


//    - Créer un test 'findById' qui récupère un des des produits le la liste (Ne créer qu'une méthode vide dans le service !)
//    - Créer des tests pour le findById
//    - Coder la méthode pour faire passer le test

    @Nested
    public class findById_test {

        @Test
        void findById_ValidId() {
            when(mockRepository.findById(2)).thenReturn(prods.get(1));

            Produit found = produitService.findById(2);

            assertNotNull(found);
            assertEquals(2, found.getId());
            assertEquals("écran Hp", found.getDescription());
        }


        @Test
        void findById_InvalidId() {
            when(mockRepository.findById(99)).thenThrow(NoSuchElementException.class);

            assertThrows(NoSuchElementException.class, () -> produitService.findById(99));
        }


    }


}