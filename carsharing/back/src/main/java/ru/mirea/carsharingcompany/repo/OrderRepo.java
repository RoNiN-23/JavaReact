package ru.mirea.carsharingcompany.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.carsharingcompany.domain.Order;

public interface OrderRepo extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByUserId(Long userId);
    Order findOrderById(Long id);
}