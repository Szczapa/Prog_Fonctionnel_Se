package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<Integer, ICalculator> calculatorMap = new HashMap<>();

        ICalculator addition = (double a, double b) -> a + b;
        ICalculator subtraction = (double a, double b) -> a - b;
        ICalculator multiplication = (double a, double b) -> a * b;
        ICalculator division = (double a, double b) -> a / b;


        calculatorMap.put(1, addition);
        calculatorMap.put(2, subtraction);
        calculatorMap.put(3, multiplication);
        calculatorMap.put(4, division);

        Consumer<String> MenuAffichage = message -> {
            System.out.println("\nQuel calcul voulez vous ?");
            System.out.println("1. addition");
            System.out.println("2. subtraction");
            System.out.println("3. multiplication");
            System.out.println("4. division");
            System.out.println("0. Exit");
        };

        while (true){
            MenuAffichage.accept("Menu Affichage");
            int option = sc.nextInt();
            if (option == 0){
                break;
            }

            if (!calculatorMap.containsKey(option)){
                System.out.println("Erreur choix invalide");
                continue;
            }

            System.out.println("Entrée la valeur 1");
            Double a = sc.nextDouble();

            System.out.println("Entrée la valeur 2");
            Double b = sc.nextDouble();

            ICalculator operate = calculatorMap.get(option);
            double resultat = operate.calculator(a,b);


            if(Double.isNaN(resultat) || Double.isInfinite(resultat)){
                System.out.println("Erreur: Division par 0");
            } else {
                System.out.println("Résultat: " + resultat);
            }
        }
    }
}