package com.pk.javarestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {
  private OrdersRepository ordersRepository;

  @Autowired
  public OrderHandler(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  public Mono<ServerResponse> all(ServerRequest request) {
    return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(ordersRepository.findAll(), Order.class);
  }

  public Mono<ServerResponse> id(ServerRequest request) {
    if(request.queryParam("id").isPresent() && !request.queryParam("id").get().isBlank()) {
      return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(ordersRepository.findById(request.queryParam("id").get()), Order.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate or missing id query param"));
    }
  }

}
