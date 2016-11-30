package org.tokeh.bdd_tutorial;

import java.time.LocalDate;
import java.util.*;

public class Library {
  private final HashMap<String, Book> books;

  public Library() {
    this.books = new HashMap<>();
  }

  public void addBook(final Book book) {
    this.books.put(book.getTitle(), book);
  }

  public ArrayList<Book> findBooks(final LocalDate from, final LocalDate to) {
    ArrayList<Book> list = new ArrayList<>();
    for (Map.Entry entry : this.books.entrySet()) {
      Book book = (Book) entry.getValue();

      if (book.getPublished().getYear() >= from.getYear()
          && book.getPublished().getYear() <= to.getYear()) {
        list.add(book);
      }
    }

    list.sort((Book book1, Book book2) ->
        Integer.compare(book2.getPublished().getYear(), book1.getPublished().getYear()));
    return list;
  }

  public ArrayList<Book> findBooksByAuthor(final String author) {
    ArrayList<Book> list = new ArrayList<>();
    for (Map.Entry entry : this.books.entrySet()) {
      Book book = (Book) entry.getValue();

      if (book.getAuthor().equals(author)) {
        list.add(book);
      }
    }

    list.sort((Book book1, Book book2) ->
        Integer.compare(book2.getPublished().getYear(), book1.getPublished().getYear()));
    return list;
  }

  public List<String> searchAuthorByName(final String author) {
    ArrayList<String> list = new ArrayList<>();
    for (Map.Entry entry : this.books.entrySet()) {
      Book book = (Book) entry.getValue();

      if (book.getAuthor().equals(author)) {
        list.add(book.getAuthor());
      }
    }

    return list;
  }

  public ArrayList<String> searchForAllAuthors() {
    Set<String> set = new HashSet<>();
    for (Map.Entry entry : this.books.entrySet()) {
      Book book = (Book) entry.getValue();

      set.add(book.getAuthor());
    }

    ArrayList<String> list = new ArrayList<>(set);
    Collections.sort(list);
    return list;
  }
}