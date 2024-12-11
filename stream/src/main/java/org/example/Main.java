package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Movie> movies = loadMovies("films.csv");

        movies.stream().limit(10).forEach(System.out::println);
        System.out.println("\n");

        movies.stream().filter(movie -> "Documentaire".equalsIgnoreCase(movie.getGenre())).forEach(movie -> System.out.println(movie.getGenre() + ": " + movie.getTitle() + " (" + movie.getReleaseDate() + ")"));

        System.out.println("\n");

        // 2.2 Films réalisés après 2000
        movies.stream()
                .filter(movie -> movie.getReleaseDate().isAfter(LocalDate.of(2000, 1, 1)))
                .map(Movie::getTitle)
                .forEach(System.out::println);
        // 2.3 Films réalisés par un réalisateur spécifique
        movies.stream().filter(movie -> "Michael Webster".equalsIgnoreCase(movie.getDirector())).forEach(movie -> System.out.println(movie.getTitle()));

        // 3.1 Trier par nombre d'entrées et afficher les 5 premiers
        movies.stream().sorted(Comparator.comparingInt(Movie::getEntries)).limit(5).forEach(System.out::println);

        // 3.2 Trier par date de sortie et afficher les titres
        movies.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate))
                .map(Movie::getTitle)
                .forEach(System.out::println);


        // 3.3 10 films avec le moins d'entrées
        movies.stream().sorted(Comparator.comparingInt(Movie::getEntries)).limit(10).forEach(System.out::println);

        // 4.1 Regrouper par genre et compter les films
        Map<String, Long> moviesByGenre = movies.stream().collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        moviesByGenre.forEach((genre, count) -> System.out.println(genre + ": " + count));

        // 4.2 Regrouper par réalisateur et afficher les titres
        Map<String, List<String>> moviesByDirector = movies.stream().collect(Collectors.groupingBy(Movie::getDirector, Collectors.mapping(Movie::getTitle, Collectors.toList())));
        moviesByDirector.forEach((director, titles) -> {
            System.out.println(director + ": " + titles);
        });
        // 5.1 Total des entrées
        int totalEntries = movies.stream().mapToInt(Movie::getEntries).sum();
        System.out.println("Total des entrées: " + totalEntries);

        // 5.2 Genre avec le plus grand nombre total d'entrées
        Map.Entry<String, Integer> topGenre = movies.stream().collect(Collectors.groupingBy(Movie::getGenre, Collectors.summingInt(Movie::getEntries))).entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
        System.out.println("Genre avec le plus d'entrées: " + topGenre.getKey());

        // 5.3 Moyenne des entrées pour un réalisateur donné
        double averageEntries = movies.stream().filter(movie -> "Michael Webster".equalsIgnoreCase(movie.getDirector())).mapToInt(Movie::getEntries).average().orElse(0);
        System.out.println("Moyenne des entrées: " + averageEntries);

        // 6.1 Transformer les données pour un format personnalisé
        movies.stream()
                .map(movie -> String.format("%s (%s) - Réalisé par %s en %d",
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getDirector(),
                        movie.getReleaseDate().getYear()))
                .forEach(System.out::println);

        // 6.2 Liste des genres uniques
        movies.stream().map(Movie::getGenre).distinct().forEach(System.out::println);

        Map<String, Optional<Movie>> firstMoviesByGenre = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre,
                        Collectors.minBy(Comparator.comparing(Movie::getReleaseDate)) // Use comparing for LocalDate
                ));

        firstMoviesByGenre.forEach((genre, movie) -> {
            System.out.println(genre + ": " + movie.orElse(null));
        });

        // 7.2 Réalisateurs ayant réalisé plus de 50 films
        movies.stream().collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() > 50).forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // 7.3 Total des entrées pour les films entre deux années données
        int entriesBetweenYears = movies.stream()
                .filter(movie -> {
                    int year = movie.getReleaseDate().getYear();
                    return year >= 1990 && year <= 2000;
                })
                .mapToInt(Movie::getEntries)
                .sum();

        System.out.println("Total des entrées (1990-2000): " + entriesBetweenYears);


    }

    private static List<Movie> loadMovies(String filePath) throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return new BufferedReader(new InputStreamReader(inputStream)).lines().skip(1).map(Movie::fromCsv).collect(Collectors.toList());
    }
}