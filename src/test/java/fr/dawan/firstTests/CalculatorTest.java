package fr.dawan.firstTests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Début des test !");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Fin des tests !");
    }

    @BeforeEach
    public void setUp(){
        Calculator calculator = new Calculator();
        System.out.println("Instanciation d'un nouveau calculator");
    }

    @AfterEach
    public void tearDown(){
        calculator = null;
        System.out.println("Nettoyage ... ");
    }

    @Test
    void add() {
        // Arrange: préparation des données nécssaires au test
        //Calculator calcultor = new Calculator();
        int a = 2;
        int b = 3;

        // Act: Exécutaion de la méthode à tester
        int result = calculator.add(a, b);

        // Assert: Vérif. du résultat
        // assertEquals(5, result);  // si je veux sans commentaires
        assertEquals(5, result, "L'addition de 2 + 3 devrait retourner 5");
    }

    @Test
    void substract() {
        // Arrange
        //Calculator calcultor = new Calculator();
        int a = 5;
        int b = 3;

        // Act
        int result = calculator.substract(5, 3);

        // Assert
        assertEquals(2, result, "La soustraction de 5 - 3 devrait retourner 2" );
    }

    @Test
    void isPositive() {
        // Arrange
      //  Calculator calcultor = new Calculator();
        int number = 5;

        // Act
        boolean result = calculator.isPositive(number);

        // Assert
        assertTrue(result, "Le chiffre 5 devrait être positif");
    }

    @Test
    void getMessage() {
        // Arrange
        //Calculator calcultor = new Calculator();
        // Act
        String result = calculator.getMessage();
        // Assert
        assertEquals("Hello, Junit !", result, "Le message doit être 'Hello, Junit !'");
    }

    @Test
    void getNullableValue() {
        // Arrange
        //Calculator calculator = new Calculator();
        boolean returnNull = true;
        // Act
        Integer result = calculator.getNullableValue(returnNull);

        // Assert
        assertNull(result, "La valeur retournée devrait être null");
    }

    @Test
    void divide() {
        // Arrange
//        Calculator calcultor = new Calculator();
        int a = 10;
        int b = 2;

        // Act
        int result = calculator.divide(a, b);

        // Assert
        assertEquals(5, result, "la valeur attendue de 10/2 est 5");

    }

    @Test
    void squareRoot() {
        // Arrange
        // Calculator calcultor = new Calculator();
        int number = -1;

        // Act et assert
        assertThrows(IllegalArgumentException.class, () -> calculator.squareRoot(number), "La méthode devrait " +
                "lever une exection");
    }

    @Test
    void getNumbers() {
        // Arrange
        Calculator calcultor = new Calculator();

        // Act
        int[] result = calcultor.getNumbers();

        // Assert
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result, "Le tableau retourné devrait être [1, 2, 3, 4, 5]");
    }
}