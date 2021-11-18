package com.library.apilibrary.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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

    @Column(name = "bookName", length = 50)
    private String bookName;
    
    @Column(name = "bookAuthor", length = 25)
    private String bookAuthor;

    @Column(name = "bookImage")
    private String bookImage;

    @Lob
    private String bookDesc;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "genreId", referencedColumnName = "genreId")
    private Genre genreId;

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "typeId")
    private Type typeId;

    public Book(String bookName, String bookAuthor, String bookImage, String bookDesc, Genre genreId, Type typeId) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookDesc = bookDesc;
        this.genreId = genreId;
        this.typeId = typeId;
    }

    

}
