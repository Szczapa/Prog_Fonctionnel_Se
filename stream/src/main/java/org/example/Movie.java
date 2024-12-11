package org.example;

import java.time.LocalDate;

public class Movie {
    // Titre,Date de sortie,Réalisateur,Genre,Nombre d'entrées
    private String title;
    private LocalDate releaseDate;
    private String director;
    private String genre;
    private int entries;

    public Movie(String title, LocalDate releaseDate, String director, String genre, int entries) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.genre = genre;
        this.entries = entries;
    }

    public static Movie fromCsv(String line) {
        String[] parts = line.split(",");
        return new Movie(
                parts[0].trim(),
                LocalDate.parse(parts[1].trim()),
                parts[2].trim(),
                parts[3].trim(),
                Integer.parseInt(parts[4].trim())
        );
    }

    public int getEntries() {
        return entries;
    }

    public void setEntries(int entries) {
        this.entries = entries;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + ", (" + releaseDate + "), " + director + ", (" + genre + "), " + entries;
    }
}
