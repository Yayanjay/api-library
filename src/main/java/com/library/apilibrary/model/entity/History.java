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
@Table(name = "histories")
@Data
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "librarian",length = 25)
    private String librarian;

    @Column(name = "date")
    private String date;

    @Column(name = "status", length = 10)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @ManyToOne()
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book bookId;

    public History(String librarian, String date, String status, User userId, Book bookId) {
        this.librarian = librarian;
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.bookId = bookId;
    }

    
}
