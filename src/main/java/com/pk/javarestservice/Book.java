package com.pk.javarestservice;

import java.time.LocalDate;

public class Book {
  private String title;
  private String author;
  private LocalDate date;
  private Double isbn;

  public Book(String title, String author, LocalDate date, Double isbn) {
    this.title = title;
    this.author = author;
    this.date = date;
    this.isbn = isbn;
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getIsbn() {
    return isbn;
  }

  public void setIsbn(Double isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return title + " by " + author + "\nISBN: " + isbn +"\nPublished on " + date;
  }
}
