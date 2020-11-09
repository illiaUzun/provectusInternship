package com.provectus.uzunillia.bookstore.service;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.repos.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = NONE)
@Import(BookServiceTest.Config.class)
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("List all entities with duplicate names")
    public void testFindAllWithDuplicatedNames() {
        final String duplicatedTitle = "duplicated";
        bookRepository.deleteAll();
        bookService.save(getBook(duplicatedTitle));
        bookService.save(getBook(duplicatedTitle));
        final String uniqueTitle = "unique";
        bookService.save(getBook(uniqueTitle));

        bookService.getAllDuplicatedBooks().forEach(book -> assertEquals(duplicatedTitle, book.getTitle()));
    }

    @Test
    @DisplayName("Select a list of entities from the system by custom criteria (first char in title)")
    public void testFilterList() {
        final String duplicatedTitle = "duplicated";
        bookRepository.deleteAll();
        bookService.save(getBook(duplicatedTitle));
        bookService.save(getBook(duplicatedTitle));
        final String uniqueTitle = "unique";
        bookService.save(getBook(uniqueTitle));

        bookService.filterList("u", "", "").forEach(book -> assertEquals(uniqueTitle, book.getTitle()));
    }

    @Test
    @DisplayName("Select a list of entities from the system by title")
    public void searchByCriteria() {
        final String duplicatedTitle = "duplicated";
        bookRepository.deleteAll();
        bookService.save(getBook(duplicatedTitle));
        bookService.save(getBook(duplicatedTitle));
        final String uniqueTitle = "unique";
        bookService.save(getBook(uniqueTitle));

        bookService.filterList(uniqueTitle, "", "").forEach(book -> assertEquals(uniqueTitle, book.getTitle()));
        bookService.filterList(duplicatedTitle, "", "").forEach(book -> assertEquals(duplicatedTitle, book.getTitle()));
    }

    private Book getBook(String duplicatedTitle) {
        return new Book(duplicatedTitle, "description", new BigDecimal(1), new String[]{"author"}, new String[]{"genre"});
    }

    @TestConfiguration
    public static class Config {

        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }
}