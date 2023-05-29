package com.pk.javarestservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BookClient {
  private final WebClient client;

  public BookClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:8080").build();
  }

  public Mono<Book> getAllBooks() {
    return this.client
            .get()
            .uri("/all")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Book.class);
  }
}
