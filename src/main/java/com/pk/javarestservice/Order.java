package com.pk.javarestservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
public class Order {
  @Id
  private String id;
  private List<BookOrder> books;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<BookOrder> getBooks() {
    return books;
  }

  public void setBooks(List<BookOrder> books) {
    this.books = books;
  }

  public void addBookOrder(BookOrder order) {
    this.books.add(order);
  }

  public Order(List<BookOrder> books) {
    this.books = books;
  }

  public Order() {
    this.books = new ArrayList<>();
  }

}

