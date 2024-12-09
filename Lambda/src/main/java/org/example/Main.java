package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit("Ordinateur", 1500.00, 10));
        produits.add(new Produit("Téléphone", 800.00, 0));
        produits.add(new Produit("Tablette", 500.00, 5));


        FiltreProduit filtreEnStock = produit -> produit.getQuantite() > 0;
        List<Produit> produitsEnStock = filtrerProduits(produits, filtreEnStock);
        System.out.println("Produits en stock : " + produitsEnStock);


        FiltreProduit filtreNom = produit -> produit.getNom().equalsIgnoreCase("Ordinateur");
        List<Produit> produitsParNom = filtrerProduits(produits, filtreNom);
        System.out.println("Produits avec le nom 'Ordinateur' : " + produitsParNom);


        TransformationProduit augmenterPrix = produit -> {
            produit.setPrix(produit.getPrix() * 1.1);
            return produit;
        };


        TransformationProduit diminuerPrix = produit -> {
            produit.setPrix(produit.getPrix() * 0.9);
            return produit;
        };


        TransformationProduit restocker = produit -> {
            produit.setQuantite(produit.getQuantite() + 10);
            return produit;
        };


        TransformationProduit diminuerStock = produit -> {
            produit.setQuantite(Math.max(produit.getQuantite() - 1, 0));
            return produit;
        };


        OperationProduit operationsSuccessives = produit -> {
            produit = diminuerPrix.appliquer(produit);
            produit.setNom("[PROMO] " + produit.getNom());
            return produit;
        };


        System.out.println("\nAvant transformation : " + produits);
        for (Produit produit : produits) {
            augmenterPrix.appliquer(produit);
            diminuerStock.appliquer(produit);
        }
        System.out.println("Après augmentation de prix et diminution de stock : " + produits);


        for (Produit produit : produits) {
            operationsSuccessives.appliquer(produit);
        }
        System.out.println("Après opérations successives : " + produits);
    }

    // Méthode pour filtrer les produits
    public static List<Produit> filtrerProduits(List<Produit> produits, FiltreProduit filtre) {
        List<Produit> resultat = new ArrayList<>();
        for (Produit produit : produits) {
            if (filtre.tester(produit)) {
                resultat.add(produit);
            }
        }
        return resultat;
    }
}

