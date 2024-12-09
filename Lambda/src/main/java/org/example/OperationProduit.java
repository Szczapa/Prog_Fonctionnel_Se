package org.example;

@FunctionalInterface
public interface OperationProduit {
    Produit appliquer(Produit produit);
}