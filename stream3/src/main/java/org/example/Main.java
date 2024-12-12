package org.example;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Book> books = loadBooks("books_dataset.csv");


        List<Book> availableBooks = books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
        System.out.printf("Found %d available books.%n", availableBooks.size());
        availableBooks.stream().limit(10).forEach(System.out::println);

        System.out.println("\n");

        List<Book> oldBooks = books.stream()
                .filter(book -> book.getPublicationDate().getYear() < 1950)
                .collect(Collectors.toList());
        System.out.printf("Found %d books published before 1950.%n", oldBooks.size());
        oldBooks.stream().limit(10).forEach(book ->
                System.out.printf("Title: %s, Author: %s%n", book.getTitle(), book.getAuthor()));

        System.out.println("\n");
        Map<String, List<Book>> booksByGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
        System.out.println("Books grouped by genre:");
        booksByGenre.forEach((genre, booksInGenre) -> {
            System.out.printf("%s: %d books%n", genre, booksInGenre.size());
            booksInGenre.stream().limit(10).forEach(System.out::println);
        });

        System.out.println("\n");
        books.stream()
                .min(Comparator.comparing(Book::getPublicationDate))
                .ifPresent(book -> System.out.println("Oldest book: " + book));


        boolean existsHarry = books.stream()
                .anyMatch(book -> book.getTitle().startsWith("Harry"));
        System.out.println("Is there a book starting with 'Harry'? " + existsHarry);
        System.out.println("\n");

        double averagePrice = books.stream()
                .filter(Book::isAvailable)
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("Average price of available books: " + averagePrice);
        System.out.println("\n");

        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparingInt(Book::getPages)
                        .thenComparingDouble(Book::getPrice))
                .collect(Collectors.toList());
        System.out.printf("Sorted %d books by pages and price.%n", sortedBooks.size());
        sortedBooks.stream().limit(10).forEach(System.out::println);

        System.out.println("\n");
        List<Book> fantasyBooks = books.stream()
                .filter(book -> "Fantasy".equalsIgnoreCase(book.getGenre()))
                .collect(Collectors.toList());
        int totalFantasyPages = fantasyBooks.stream()
                .mapToInt(Book::getPages)
                .sum();
        System.out.printf("Found %d Fantasy books with %d total pages.%n", fantasyBooks.size(), totalFantasyPages);
        fantasyBooks.stream().limit(10).forEach(System.out::println);
        System.out.println("\n");

        books.stream()
                .filter(Book::isAvailable)
                .max(Comparator.comparingDouble(Book::getPrice))
                .ifPresent(book -> System.out.println("Most expensive available book: " + book));

        System.out.println("\n");
        Map<String, Long> authorCounts = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));
        authorCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 17)
                .forEach(entry -> {
                    System.out.printf("Prolific author: %s with %d books.%n", entry.getKey(), entry.getValue());
                    books.stream()
                            .filter(book -> book.getAuthor().equals(entry.getKey()))
                            .limit(10)
                            .forEach(System.out::println);
                });
        System.out.println("\n");

        Map<String, Long> genreCounts = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
        System.out.println("Books by genre:");
        genreCounts.forEach((genre, count) ->
                System.out.printf("%s: %d books%n", genre, count));

        System.out.println("\n");
        double priceThreshold = 20.0;
        List<Book> affordableBooks = books.stream()
                .filter(book -> book.isAvailable() && book.getPrice() < priceThreshold)
                .collect(Collectors.toList());
        System.out.printf("Found %d affordable books below %.2f.%n", affordableBooks.size(), priceThreshold);
        affordableBooks.stream().limit(10).forEach(System.out::println);
        System.out.println("\n");

        Map<Integer, Integer> pagesByYear = books.stream()
                .collect(Collectors.groupingBy(
                        book -> book.getPublicationDate().getYear(),
                        Collectors.summingInt(Book::getPages)
                ));
        System.out.println("Total pages by year:");
        pagesByYear.forEach((year, totalPages) ->
                System.out.printf("Year %d: %d pages%n", year, totalPages));
    }

    private static List<Book> loadBooks(String filePath) throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return new BufferedReader(new InputStreamReader(inputStream)).lines()
                .skip(1)
                .map(Book::fromCsv)
                .collect(Collectors.toList());
    }
}
