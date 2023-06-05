package com.pk.javarestservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends ReactiveCrudRepository<Order, String> {
}
