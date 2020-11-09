package com.provectus.uzunillia.bookstore.service;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Save a book.
     *
     * @param book entity to save
     * @return the persisted entity
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Get all the books.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Get all the books with duplicated names.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Book> getAllDuplicatedBooks() {
        return bookRepository.findAllWithDuplicatedNames();
    }

    /**
     * Get one book by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Book> findOne(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * List filter util method
     *
     * @param title  - title of book to search
     * @param genre  - title of book to search
     * @param author - title of book to search
     * @return List of book by searching parameters
     */
    public List<Book> filterList(String title, String genre, String author) {
        ArrayList<Book> filteredList = new ArrayList<>();
        for (Book book : findAll()) {
            boolean isTitleMatches = Objects.requireNonNull(book.getTitle().toLowerCase()).startsWith(title.toLowerCase());
            boolean isGenreMatches = Objects.requireNonNull(Arrays.toString(book.getGenre())).contains(genre);
            boolean isAuthorMatches = Objects.requireNonNull(Arrays.toString(book.getAuthor()).toLowerCase()).contains(author.toLowerCase());

            if (isTitleMatches) {
                if (isAuthorMatches) {
                    if (isGenreMatches) {
                        filteredList.add(book);
                    }
                }
            }
        }
        return filteredList;
    }
}
