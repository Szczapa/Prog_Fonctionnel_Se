package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<Integer, ICalculator> calculatorMap = Map.of(
                0, (double a, double b) -> a + b,
                1, (double a, double b) -> a - b,
                2, (double a, double b) -> a * b,
                3, (double a, double b) -> b != 0 ? a / b : Double.NaN
        );

        Consumer<String> MenuAffichage = message -> {
            System.out.println("\nQuel calcul voulez vous ?");
            System.out.println("1. addition");
            System.out.println("2. subtraction");
            System.out.println("3. multiplication");
            System.out.println("4. division");
            System.out.println("0. Exit");
        };

        while (true) {
            MenuAffichage.accept("Menu Affichage");
            int option = sc.nextInt();
            if (option == 0) {
                break;
            }

            if (!calculatorMap.containsKey(option)) {
                System.out.println("Erreur choix invalide");
                continue;
            }

            System.out.println("Entrée la valeur 1");
            Double a = sc.nextDouble();

            System.out.println("Entrée la valeur 2");
            Double b = sc.nextDouble();

            ICalculator operate = calculatorMap.get(option);
            double resultat = operate.calculator(a, b);


            if (Double.isNaN(resultat) || Double.isInfinite(resultat)) {
                System.out.println("Erreur: Division par 0");
            } else {
                System.out.println("Résultat: " + resultat);
            }
        }
    }
}