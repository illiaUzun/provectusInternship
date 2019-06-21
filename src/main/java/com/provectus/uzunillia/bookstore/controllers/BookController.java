package com.provectus.uzunillia.bookstore.controllers;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.domain.Order;
import com.provectus.uzunillia.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public void setService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/book")
    public String viewBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findOne(id).get());
//        model.addAttribute("order", new Order());
        return "book";
    }

    @GetMapping("/add_book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/add_book")
    public String submitAdd(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/edit_book")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findOne(id).get());
        return "edit_book";
    }

    @PutMapping("/edit_book")
    public String submitEdit(@RequestParam("id") Long id, @ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @DeleteMapping("/delete_book")
    public String deleteBook(@RequestParam("id") Long id) {
        System.out.println("delete");
        bookService.delete(id);
        return "redirect:/";
    }
}
