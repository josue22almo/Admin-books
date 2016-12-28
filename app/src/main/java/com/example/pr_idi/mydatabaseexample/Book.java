package com.example.pr_idi.mydatabaseexample;

public class Book {

    // Basic book data manipulation class
    // Contains basic information on the book

    private long id;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private String category;
    private int cercle;
    private String personal_evaluation;

    public Book(long id) {
        this.id = id;
    }

    public Book(long id, String title, String author, int year,
                String publisher, String category, int cercle, String personal_evaluation) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.category = category;
        this.personal_evaluation = personal_evaluation;
        this.cercle = cercle;
    }

    public int getCercle() {
        return cercle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title= title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year= year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher= publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPersonal_evaluation() {
        return personal_evaluation;
    }

    public void setPersonal_evaluation(String personal_evaluation) {
        this.personal_evaluation = personal_evaluation;
    }

    // Will be used by the ArrayAdapter in the ListView
    // Note that it only produces the title and the author
    // Extra information should be created by modifying this
    // method or by adding the methods required
    @Override
    public String toString() {
        return String.format("%s - %s", title, author);
    }
}