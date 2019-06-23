package com.provectus.uzunillia.bookstore.controllers;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.domain.Order;
import com.provectus.uzunillia.bookstore.domain.enums.Genre;
import com.provectus.uzunillia.bookstore.service.BookService;
import com.provectus.uzunillia.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {

    private BookService bookService;
    private OrderService orderService;

    @Autowired
    public void setService(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "title", defaultValue = "", required = false) String title,
                        @RequestParam(value = "genre", defaultValue = "", required = false) String genre,
                        @RequestParam(value = "author", defaultValue = "", required = false) String author,
                        Model model) {
        List<Book> books = filter(title, genre, author);
        model.addAttribute("books", books);
        model.addAttribute("allGenres", Genre.values());
        return "index";
    }
    private List<Book> filter(String title, String genre, String author) {
        ArrayList<Book> filteredList = new ArrayList<>();

        if (bookService.findAll() != null) {
            for (Book book : bookService.findAll()) {
                boolean isTitleMatches = Objects.requireNonNull(book.getTitle()).toLowerCase().startsWith(title);
                boolean isGenreMatches = Objects.requireNonNull(Arrays.toString(book.getGenre())).contains(genre);
                boolean isAuthorMatches = Objects.requireNonNull(Arrays.toString(book.getAuthor())).contains(author);

                if (isTitleMatches) {
                    if (isAuthorMatches) {
                        if (isGenreMatches) {
                            filteredList.add(book);
                        }
                    }
                }
            }
        }
        return filteredList;
    }
    @GetMapping("/book")
    public String viewBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findOne(id).get());
        return "book";
    }

    @PutMapping("/add_book")
    public void addAuthor(@RequestParam("id") Long id, @ModelAttribute Book book) {
        bookService.save(book);
    }

    @GetMapping("/add_book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allGenres", Genre.values());
        return "add_book";
    }

    @PostMapping(value = "/add_book")
    public String submitAdd(@RequestParam MultiValueMap<Integer, String> formData, @ModelAttribute Book book) {
        System.out.println(formData.toString());
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
        bookService.delete(id);
        return "redirect:/";
    }
}
