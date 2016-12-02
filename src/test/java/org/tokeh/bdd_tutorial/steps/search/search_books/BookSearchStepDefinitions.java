package org.tokeh.bdd_tutorial.steps.search.search_books;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;

public class BookSearchStepDefinitions {

  @Steps
  SearchSteps search;

  @Given("a library with books")
  public void ensureLibraryIsPresentAndPopulated() {
    this.search.ensureLibraryIsPresentAndPopulated();
  }

  @Given("{a|another} book with the title '$title', written by '$author', published on $published")
  public void addNewBook(final String title, final String author, final String published) {
    this.search.addNewBook(title, author, published);
  }

  @Given("the library contains the following books:$")
  public void addBooksAsTable(final ExamplesTable books) {
    this.search.addNewBooks(books);
  }

  @Given("a book with the title <title>, written by <author>, published on <published>")
  public void addNewBookParameterized(@Named("title") String title, @Named("author") String author, @Named("published") String published) {
    Serenity.getCurrentSession().clear();
    this.search.addNewBook(title, author, published);
  }

  @When("the customer searches for books published between $from and $to")
  public void searchByTimeSpan(final int from, final int to) {
    this.search.searchByTimeSpan(from, to);
  }

  @Then("$booksFound book{s|} should have been found")
  public void verifyAmountOfBooksFound(final int booksFound) {
    this.search.verifyAmountOfBooksFound(booksFound);
  }

  @Then("Book $position should have the title '$title'")
  public void verifyBookAtPosition(final int position, final String title) {
    this.search.verifyBookAtPosition(position, title);
  }

  @Then("a Book with the title <title> published on <published> should have been found")
  public void verifyBookAtPositionParameterized(@Named("title") String title, @Named("published") String published) {
    this.search.verifyBookAtFirstPosition(title, published);
  }

  @When("the customer searches for books written by '$author'")
  public void searchBookByAuthor(final String author) {
    this.search.searchByAuthor(author);
  }
}