package com.provectus.uzunillia.bookstore.service;

import com.provectus.uzunillia.bookstore.domain.Book;
import com.provectus.uzunillia.bookstore.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

}
