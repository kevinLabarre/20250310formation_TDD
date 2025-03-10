package fr.dawan.firstTests;

public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int substract(int a, int b){
        return a - b;
    }

    public boolean isPositive(int Number) {
        return Number > 0;
    }

    public String getMessage() {
        return "Hello, Junit !";
    }

    // Retourner "null" ou "42"
    // Si je lui passe un 'true' --> null sinon elle renvoie 42
    public Integer getNullableValue(boolean returnNull){
        return returnNull ? null : 42;
    }

    public double divide(double a, double b){

        if(b == 0){
            throw new ArithmeticException("La division par zéro est impossible");
        }

        return a / b;
    }

    public double squareRoot(int number){
        if (number < 0){
            throw new IllegalArgumentException("Le nombre ne peut pas être négatif");
        }
        return Math.sqrt(number);
    }

    public int[] getNumbers(){
        return new int[]{1, 2, 3, 4, 5};
    }

}
