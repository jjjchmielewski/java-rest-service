package com.pk.javarestservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class Router {

  @Bean
  public RouterFunction<ServerResponse> all(BookHandler bookHandler) {
    return RouterFunctions
            .route(GET("/books/all").and(accept(MediaType.APPLICATION_JSON)), bookHandler::all);
  }

  @Bean
  public RouterFunction<ServerResponse> author(BookHandler bookHandler) {
    return RouterFunctions
            .route(
                    GET("/books")
                            .and(queryParam("author", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    bookHandler::author
            );
  }

  @Bean
  public RouterFunction<ServerResponse> title(BookHandler bookHandler) {
    return RouterFunctions
            .route(
                    GET("/books")
                            .and(queryParam("title", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    bookHandler::title
            );
  }

  @Bean
  public RouterFunction<ServerResponse> date(BookHandler bookHandler) {
    return RouterFunctions
            .route(
                    GET("/books")
                            .and(queryParam("year", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    bookHandler::date
            );
  }

  @Bean
  public RouterFunction<ServerResponse> isbn(BookHandler bookHandler) {
    return RouterFunctions
            .route(
                    GET("/books")
                            .and(queryParam("isbn", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    bookHandler::isbn
            );
  }

  @Bean
  public RouterFunction<ServerResponse> cartAdd(CartHandler cartHandler) {
    return RouterFunctions
            .route(
                    POST("/cart")
                            .and(queryParam("isbn", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    cartHandler::add
            );
  }

  @Bean
  public RouterFunction<ServerResponse> cartRemove(CartHandler cartHandler) {
    return RouterFunctions
            .route(
                    DELETE("/cart")
                            .and(queryParam("isbn", t -> true))
                            .and(accept(MediaType.APPLICATION_JSON)),
                    cartHandler::delete
            );
  }

  @Bean
  public RouterFunction<ServerResponse> cart(CartHandler cartHandler) {
    return RouterFunctions
            .route(GET("/cart").and(accept(MediaType.APPLICATION_JSON)), cartHandler::get);
  }
}
