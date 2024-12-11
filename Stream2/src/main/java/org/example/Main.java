package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Liste de commandes
        List<Commande> commandes = Arrays.asList(
                new Commande(1, "Alice", Arrays.asList("Ordinateur", "Souris"), 1200.50, true),
                new Commande(2, "Bob", Arrays.asList("Clavier", "Écran"), 300.75, false),
                new Commande(3, "Charlie", Arrays.asList("Imprimante"), 150.00, true),
                new Commande(4, "Alice", Arrays.asList("USB", "Casque"), 75.50, false),
                new Commande(5, "Bob", Arrays.asList("Tablette"), 200.00, true)
        );

        // 1
        commandes.forEach(System.out::println);

        //2
        commandes.stream().map(Commande::getClient).distinct().forEach(System.out::println);

        System.out.println("\n");


        //3
        double montantTotal = commandes.stream().mapToDouble(Commande::getMontantTotal).sum();
        System.out.println("Montant total :" + montantTotal + " €");

        //4
        commandes.forEach(commande -> {
            System.out.println("Commande ID: " + commande.getId());
            System.out.println("Articles: " + String.join(", ", commande.getArticles()));
            System.out.println("Prix total: " + commande.getMontantTotal());
            System.out.println("\n");
        });

        //5
        commandes.stream().filter(Commande::isEstLivree).forEach(System.out::println);

        System.out.println("\n");

        //6
        Map<String, Double> totalParClient = commandes.stream()
                .collect(Collectors.groupingBy(
                        Commande::getClient,
                        Collectors.summingDouble(Commande::getMontantTotal)
                ));
        totalParClient.forEach((client, total) -> System.out.println(client + " : " + total));

        System.out.println("\n");

        //7
        List<String> articlesUniques = commandes.stream()
                .flatMap(commande -> commande.getArticles().stream())
                .distinct() // Pour éviter les doublons
                .collect(Collectors.toList());
        System.out.println("Articles uniques : " + articlesUniques);


        //8
        System.out.println("\n");
        boolean tousLivres = commandes.stream()
                .collect(Collectors.groupingBy(Commande::getClient))
                .values()
                .stream()
                .allMatch(cmds -> cmds.stream().anyMatch(Commande::isEstLivree));
        System.out.println("Tous les clients ont une commande livrée : " + tousLivres);


    }
}