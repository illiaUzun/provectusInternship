package com.provectus.uzunillia.bookstore.controllers;

import com.provectus.uzunillia.bookstore.domain.Order;
import com.provectus.uzunillia.bookstore.service.BookService;
import com.provectus.uzunillia.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private OrderService orderService;
    private BookService bookService;

    @Autowired
    public void setService(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/orders")
    public String index(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @PostMapping("/add_order")
    public String submitOrder(@RequestParam("id") Long id, @ModelAttribute Order order) {
        order.setBook(bookService.findOne(id).get());
        orderService.save(order);
        return "redirect:/book?id=" + id;
    }
}
