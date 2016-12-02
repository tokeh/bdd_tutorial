package org.tokeh.bdd_tutorial.steps.search.search_books;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.jbehave.core.model.ExamplesTable;
import org.tokeh.bdd_tutorial.Book;
import org.tokeh.bdd_tutorial.Library;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SearchSteps {
  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @Step
  public void ensureLibraryIsPresentAndPopulated() {
    Library library = this.getLibrary();
    assertNotEquals(0, library.getNumberOfBooks());
  }

  @Step
  public void addNewBook(final String title, final String author, final String published) {
    Library library = this.getLibrary();
    library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
    this.storeLibrary(library);
  }

  @Step
  public void addNewBooks(final ExamplesTable books) {
    for (Map<String, String> row : books.getRows()) {
      this.addNewBook(row.get("title"), row.get("author"), row.get("published"));
    }
  }

  @Step
  public void searchByTimeSpan(final int from, final int to) {
    this.storeBookResults(this.getLibrary().findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to)));
  }

  @Step
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(this.getBookResults().size(), booksFound);
  }

  @Step
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(this.getBookResults().get(position - 1).getTitle(), title);
  }

  @Step
  public void verifyBookAtFirstPosition(final String title, final String published) {
    assertEquals(this.getBookResults().get(0).getTitle(), title);
    assertEquals(this.getBookResults().get(0).getPublished().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), published);
  }

  @Step
  public void searchByAuthor(final String author) {
    this.storeBookResults(this.getLibrary().findBooksByAuthor(author));
  }

  @Step
  public void searchAuthorByName(final String author) {
    this.storeAuthorResults(this.getLibrary().searchAuthorByName(author));
  }

  @Step
  public void verifyNumberOfAuthorsFound(final int authorsFound) {
    assertEquals(authorsFound, this.getAuthorResults().size());
  }

  @Step
  public void verifyAuthorAtPosition(final int position, final String author) {
    assertEquals(author, this.getAuthorResults().get(position - 1));
  }

  @Step
  public void searchForAllAuthors() {
    this.storeAuthorResults(this.getLibrary().searchForAllAuthors());
  }

  private Library getLibrary() {
    Library library = new Library();
    Serenity.getCurrentSession().putIfAbsent("library", library);
    return (Library) Serenity.getCurrentSession().get("library");
  }

  private void storeLibrary(final Library library) {
    Serenity.getCurrentSession().put("library", library);
  }

  private List<Book> getBookResults() {
    return (List<Book>) Serenity.getCurrentSession().get("bookResults");
  }

  private void storeBookResults(final List<Book> bookResults) {
    Serenity.getCurrentSession().put("bookResults", bookResults);
  }

  private List<String> getAuthorResults() {
    return (List<String>) Serenity.getCurrentSession().get("authorResults");
  }

  private void storeAuthorResults(final List<String> authorResults) {
    Serenity.getCurrentSession().put("authorResults", authorResults);
  }
}
