package fr.dawan.firstTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        // Arrange: préparation des données nécssaires au test
        Calculator calcultor = new Calculator();
        int a = 2;
        int b = 3;

        // Act: Exécutaion de la méthode à tester
        int result = calcultor.add(a, b);

        // Assert: Vérif. du résultat
        // assertEquals(5, result);  // si je veux sans commentaires
        assertEquals(5, result, "L'addition de 2 + 3 devrait retourner 5");

    }

    @Test
    void substract() {
        // Arrange
        Calculator calcultor = new Calculator();
        int a = 5;
        int b = 3;

        // Act
        int result = calcultor.substract(5, 3);

        // Assert
        assertEquals(2, result, "La soustraction de 5 - 3 devrait retourner 2" );
    }
}