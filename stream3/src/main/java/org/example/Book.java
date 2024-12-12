package org.example;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String genre;
    private LocalDate publicationDate;
    private int pages;
    private boolean available;
    private double price;


    public Book() {
    }

    public Book(String title, String author, String genre, LocalDate publicationDate, int pages, boolean available, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.available = available;
        this.price = price;

    }


    public static Book fromCsv(String line) {
        String[] parts = line.split(",");
        return new Book(
                parts[0].trim(),                              // Title
                parts[1].trim(),                              // Author
                parts[2].trim(),                              // Genre
                LocalDate.parse(parts[3].trim()),             // Publication Date
                Integer.parseInt(parts[4].trim()),            // Pages
                Boolean.parseBoolean(parts[5].trim()),        // Available
                Double.parseDouble(parts[6].trim())          // Price

        );
    }


    @Override
    public String toString() {
        return "Titre : " + title + ", Auteur : " + author + ", genre : " + genre + ", publication date : " + publicationDate + ", pages : " + pages + ", disponibilité : " + available;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
