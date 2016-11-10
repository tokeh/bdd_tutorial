package org.tokeh.bdd_tutorial;

import org.joda.time.DateTime;

import java.time.LocalDate;

public class Book {
  private final String title;
  private final String author;
  private final DateTime published;

  public Book(final String title, final String author, final DateTime published) {
    this.title = title;
    this.author = author;
    this.published = published;
  }

  public String getTitle() {
    return this.title;
  }

  public DateTime getPublished() {
    return this.published;
  }
}
