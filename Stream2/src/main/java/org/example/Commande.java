package org.example;

import java.util.List;

class Commande {
    private int id;
    private String client;
    private List<String> articles;
    private double montantTotal;
    private boolean estLivree;

    public Commande() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public boolean isEstLivree() {
        return estLivree;
    }

    public void setEstLivree(boolean estLivree) {
        this.estLivree = estLivree;
    }

    public Commande(int id, String client, List<String> articles, double montantTotal, boolean estLivree) {
        this.id = id;
        this.client = client;
        this.articles = articles;
        this.montantTotal = montantTotal;
        this.estLivree = estLivree;

    }
    @Override
    public String toString() {
        return "id :" + id + ", Nom du client : "+ client + ". \n" +
                "Montant total : "+ montantTotal +" € \n" +
                "Articles : " + articles + "\n" +
                "Status : " + estLivree + "\n";
    }
}
