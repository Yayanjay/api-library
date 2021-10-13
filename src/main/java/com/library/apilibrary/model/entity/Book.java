package com.library.apilibrary.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(length = 50)
    private String bookName;
    
    @Column()
    private String bookAuthor;

    @Column()
    private String bookImage;

    @Column()
    private String bookDesc;

    @ManyToOne
    @JoinColumn(name = "genreId", referencedColumnName = "genreId", insertable = false, updatable = false)
    private Genre genreId;

    public Book(String bookName, String bookAuthor, String bookImage, String bookDesc, Genre genres) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookDesc = bookDesc;
        this.genreId = genres;
    }

    public Book(String bookName2, String bookAuthor2, String bookimage2, String bookDesc2, int genreId2) {
    }


}
