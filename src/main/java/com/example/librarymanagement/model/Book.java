package com.example.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(unique = true, length = 20)
    private String isbn;
    private String genre;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private Long quantityInStock;

    public Book(){};

    public Book(Long id, String title, String isbn, Author author, Long quantityInStock, String genre) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.quantityInStock = quantityInStock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = bookId;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void decrementQuantityInStock(){
        this.quantityInStock--;
    }

    public void incrementQuantityInStock(){
        this.quantityInStock++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }
}
