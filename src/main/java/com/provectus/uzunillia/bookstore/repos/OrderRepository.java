package com.provectus.uzunillia.bookstore.repos;

import com.provectus.uzunillia.bookstore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
