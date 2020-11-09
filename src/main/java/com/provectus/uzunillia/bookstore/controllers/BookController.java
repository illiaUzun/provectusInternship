package com.provectus.uzunillia.bookstore.controllers;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.domain.enums.Genre;
import com.provectus.uzunillia.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main controller class
 * <p>
 * contains REST api methods to access
 * execute Book services
 */
@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public void setService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String indexAdmin(@RequestParam(value = "title", defaultValue = "", required = false) String title,
                             @RequestParam(value = "author", defaultValue = "", required = false) String author,
                             @RequestParam(value = "genre", defaultValue = "", required = false) String genre,
                             Model model) {
        List<Book> books = bookService.filterList(title, genre, author);
        model.addAttribute("books", books);
        model.addAttribute("allGenres", Genre.values());
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("genreSearch", genre);
        return "index";
    }

    @GetMapping("/duplicates")
    public String duplicatedBooks(@RequestParam(value = "title", defaultValue = "", required = false) String title,
                                  @RequestParam(value = "author", defaultValue = "", required = false) String author,
                                  @RequestParam(value = "genre", defaultValue = "", required = false) String genre,
                                  Model model) {
        List<Book> books = bookService.getAllDuplicatedBooks();
        model.addAttribute("books", books);
        model.addAttribute("allGenres", Genre.values());
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("genreSearch", genre);
        return "index";
    }


    @GetMapping("/user")
    public String indexUser(@RequestParam(value = "title", defaultValue = "", required = false) String title,
                            @RequestParam(value = "author", defaultValue = "", required = false) String author,
                            @RequestParam(value = "genre", defaultValue = "", required = false) String genre,
                            Model model) {
        List<Book> books = bookService.filterList(title, genre, author);
        model.addAttribute("books", books);
        model.addAttribute("allGenres", Genre.values());
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("genreSearch", genre);
        return "indexUser";
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
        model.addAttribute("allGenres", Genre.values());
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
