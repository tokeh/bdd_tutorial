package org.tokeh.bdd_tutorial;

import java.time.LocalDate;
import java.util.*;

public class Library {
  private final HashMap<String, Book> books;

  public Library() {
    this.books = new HashMap<String, Book>();
  }

  public void addBook(final Book book) {
    this.books.put(book.getTitle(), book);
  }

  public ArrayList<Book> findBooks(final LocalDate from, final LocalDate to) {
    ArrayList<Book> list = new ArrayList<Book>();
    for (Map.Entry entry : this.books.entrySet()) {
      Book book = (Book) entry.getValue();

      if (book.getPublished().getYear() >= from.getYear()
        && book.getPublished().getYear() <= to.getYear()) {
        list.add(book);
      }
    }

    Collections.sort(list, (Book book1, Book book2) ->
        Integer.compare(book2.getPublished().getYear(), book1.getPublished().getYear()));
    return list;
  }
}
