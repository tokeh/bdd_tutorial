package org.tokeh.bdd_tutorial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
  private String title;
  private String author;
  private LocalDate published;

  public Book(final String title, final String author, final String published) {
    this(title, author, LocalDate.parse(published, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
  }

  public Book(final String title, final String author, final LocalDate published) {
    this.title = title;
    this.author = author;
    this.published = published;
  }

  public String getTitle() {
    return this.title;
  }

  public LocalDate getPublished() {
    return this.published;
  }

  public String getAuthor() {
    return this.author;
  }
}