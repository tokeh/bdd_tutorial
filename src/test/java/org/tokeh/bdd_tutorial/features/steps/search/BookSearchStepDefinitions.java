package org.tokeh.bdd_tutorial.features.steps.search;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.tokeh.bdd_tutorial.features.steps.search.serenity.BookSearchSteps;

public class BookSearchStepDefinitions {
  @Steps
  BookSearchSteps bookSearch;

  // Pattern variants funktionieren leider nicht (in IntelliJ).
  @Given("a book with the title $title, written by $author, published on $published")
  public void addNewBook(final String title, final String author, final String published) {
    this.bookSearch.addNewBook(title, author, published);
  }

  @When("the customer searches for books published between $from and $to$")
  public void searchByTimeSpan(final int from, final int to) {
    this.bookSearch.searchByTimeSpan(from, to);
  }

  @Then("$booksFound books should have been found")
  public void verifyAmountOfBooksFound(final int booksFound) {
    this.bookSearch.verifyAmountOfBooksFound(booksFound);
  }

  @Then("Book $position should have the title $title")
  public void verifyBookAtPosition(final int position, final String title) {
    this.bookSearch.verifyBookAtPosition(position, title);
  }
}
