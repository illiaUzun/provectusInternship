package com.provectus.uzunillia.bookstore.service;

import com.provectus.uzunillia.bookstore.domain.Order;
import com.provectus.uzunillia.bookstore.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Save an order.
     *
     * @param order entity to save
     * @return the persisted entity
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Get all the orders.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Get one order by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Order> findOne(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Delete the order by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
