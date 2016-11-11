package org.tokeh.bdd_tutorial.features.steps.search;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.tokeh.bdd_tutorial.features.steps.search.serenity.BookSearchSteps;

public class BookSearchStepDefinitions {
  @Steps
  BookSearchSteps bookSearch;

  @Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
  public void addNewBook(final String title, final String author, final String published) {
    this.bookSearch.addNewBook(title, author, published);
  }

  @When("^the customer searches for books published between (\\d+) and (\\d+)$")
  public void searchByTimeSpan(final int from, final int to) {
    this.bookSearch.searchByTimeSpan(from, to);
  }

  @Then("(\\d+) books should have been found$")
  public void verifyAmountOfBooksFound(final int booksFound) {
    this.bookSearch.verifyAmountOfBooksFound(booksFound);
  }

  @And("Book (\\d+) should have the title '(.+)'$")
  public void verifyBookAtPosition(final int position, final String title) {
    this.bookSearch.verifyBookAtPosition(position, title);
  }
}
