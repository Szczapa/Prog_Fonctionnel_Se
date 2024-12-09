package org.example;
@FunctionalInterface
public interface TransformationProduit {
    Produit appliquer(Produit produit);
}
