package com.pk.javarestservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "books")
public class Book {
  @Id
  private String id;
  private String title;
  private String author;
  private Date date;
  private String isbn;

  public Book(String title, String author, Date date, String  isbn) {
    this.title = title;
    this.author = author;
    this.date = date;
    this.isbn = isbn;
  }

  public Book() {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return title + " by " + author + " | ISBN: " + isbn +" | Published on " + date;
  }
}
