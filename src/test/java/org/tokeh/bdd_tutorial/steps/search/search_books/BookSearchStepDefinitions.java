package org.tokeh.bdd_tutorial.steps.search.search_books;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BookSearchStepDefinitions {

  @Steps
  SearchSteps search;

  @Given("{a|another} book with the title '$title', written by '$author', published on $published")
  public void addNewBook(final String title, final String author, final String published) {
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

  @When("the customer searches for books written by '$author'")
  public void searchBookByAuthor(final String author) {
    this.search.searchByAuthor(author);
  }
}