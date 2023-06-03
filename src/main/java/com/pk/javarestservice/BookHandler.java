package com.pk.javarestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class BookHandler {
  private BooksRepository booksRepository;

  @Autowired
  public BookHandler(BooksRepository booksRepository) {
    this.booksRepository = booksRepository;
  }

  public Mono<ServerResponse> all(ServerRequest request) {
    return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(booksRepository.findAll(), Book.class);
  }

  public Mono<ServerResponse> author(ServerRequest request) {
    if (request.queryParam("author").isPresent() && !request.queryParam("author").get().isBlank()) {
      return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(booksRepository.findByAuthor(request.queryParam("author").get()), Book.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate author query param"));
    }
  }

  public Mono<ServerResponse> title(ServerRequest request) {
    if (request.queryParam("title").isPresent() && !request.queryParam("title").get().isBlank()) {
      return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(booksRepository.findByTitle(request.queryParam("title").get()), Book.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate title query param"));
    }
  }

  public Mono<ServerResponse> isbn(ServerRequest request) {
    if (request.queryParam("isbn").isPresent() && !request.queryParam("isbn").get().isBlank()) {
      return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(booksRepository.findByIsbn(request.queryParam("isbn").get()), Book.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate isbn query param"));
    }
  }

  public Mono<ServerResponse> date(ServerRequest request) {
    if (request.queryParam("year").isPresent() && !request.queryParam("year").get().isBlank()) {
      int year = Integer.parseInt(request.queryParam("year").get());
      Date dateStart = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
      Date dateEnd = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();

      return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(booksRepository.findByDateBetween(dateStart, dateEnd), Book.class);
    } else {
      return ServerResponse
              .badRequest()
              .body(BodyInserters.fromValue("Inappropriate year query param"));
    }
  }
}
