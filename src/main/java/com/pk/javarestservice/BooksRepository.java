package com.pk.javarestservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public interface BooksRepository extends ReactiveCrudRepository<Book, String> {

  Flux<Book> findByAuthor(String author);

  Flux<Book> findByTitle(String title);

  Mono<Book> findByIsbn(String isbn);

  Flux<Book> findByDateBetween(
          Date dateStart,
          Date dateEnd
  );
}
