package com.provectus.uzunillia.bookstore.domain;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Type( type = "string-array" )
    @Column(
            name = "author",
            columnDefinition = "text[]"
    )
    private String[] author;

    @Type( type = "string-array" )
    @Column(
            name = "genre",
            columnDefinition = "text[]"
    )
    private String[] genre;

    public Book() {
    }

    public Book(String title, String description, BigDecimal price, String[] author, String[] genre) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String showGenres() {
        return Arrays.toString(genre).replaceAll("[\\_]", " ").replace('[', ' ').replace(']', ' ');
    }

    public String showAuthors() {
        return Arrays.toString(author).replaceAll("[\\_]", " ").replace('[', ' ').replace(']', ' ');
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", author=" + Arrays.toString(author) +
                ", genre=" + Arrays.toString(genre) +
                '}';
    }
}
