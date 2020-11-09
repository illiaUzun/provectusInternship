package com.provectus.uzunillia.bookstore.repos;

import com.provectus.uzunillia.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE title IN (SELECT title FROM book GROUP BY title HAVING COUNT(title) > 1);")
    List<Book> findAllWithDuplicatedNames();
}
