package org.tokeh.bdd_tutorial;

import java.time.LocalDate;

public class Book {
  private final String title;
  private final String author;
  private final LocalDate published;

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
}
