package com.pk.javarestservice;

public class BookOrder {
  private String isbn;
  private Integer quantity;

  public BookOrder(String isbn, Integer quantity) {
    this.isbn = isbn;
    this.quantity = quantity;
  }

  public BookOrder() {
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
