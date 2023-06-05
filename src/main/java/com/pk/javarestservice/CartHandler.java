package com.pk.javarestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartHandler {
  private Map<String, Integer> cart = new HashMap<>();
  private BooksRepository booksRepository;
  private OrdersRepository ordersRepository;

  @Autowired
  public CartHandler(BooksRepository booksRepository, OrdersRepository ordersRepository) {
    this.booksRepository = booksRepository;
    this.ordersRepository = ordersRepository;
  }

  public Mono<ServerResponse> add(ServerRequest request) {
    if (request.queryParam("isbn").isPresent() && !request.queryParam("isbn").get().isBlank()) {

      Mono<Book> book = booksRepository.findByIsbn(request.queryParam("isbn").get());

      return book.flatMap(item -> {
                if (cart.containsKey(item.getIsbn())) {
                  int quantity = cart.get(item.getIsbn()) + 1;
                  cart.put(item.getIsbn(), quantity);
                } else {
                  cart.put(item.getIsbn(), 1);
                }
                return ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(cart));
              }
      ).switchIfEmpty(ServerResponse.notFound().build());

    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate isbn query param"));
    }
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    if (request.queryParam("isbn").isPresent() && !request.queryParam("isbn").get().isBlank()) {
      String isbn = request.queryParam("isbn").get();
      if (cart.containsKey(isbn)) {
        cart.remove(isbn);
        return ServerResponse
                .ok()
                .body(BodyInserters.fromValue(cart));
      } else {
        return ServerResponse
                .notFound()
                .build();
      }

    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate isbn query param"));
    }
  }

  public Mono<ServerResponse> get(ServerRequest request) {
    return ServerResponse
            .ok()
            .body(BodyInserters.fromValue(cart));
  }

  public Mono<ServerResponse> order(ServerRequest request) {
    if (!cart.isEmpty()) {
      List<String> isbns = cart.keySet().stream().toList();
      Order order = new Order();
      for (int i =0; i < isbns.size(); i++) {
        order.addBookOrder(new BookOrder(isbns.get(i), cart.get(isbns.get(i))));
      }

      return ServerResponse
              .ok()
              .body(ordersRepository.save(order), Order.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Cart is empty"));
    }
  }
}
