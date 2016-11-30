package org.tokeh.bdd_tutorial.features.steps.search.search_books;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.tokeh.bdd_tutorial.features.steps.search.serenity.SearchSteps;

public class AuthorSearchStepDefinitions {

  @Steps
  SearchSteps search;

  @When("the costumer searches for an author with the name '(.+)'")
  public void searchAuthorByName(final String author) {
    this.search.searchAuthorByName(author);
  }

  @When("the costumer searches for all authors")
  public void searchForAllAuthor() {
    this.search.searchForAllAuthors();
  }

  @Then("(\\d+) authors? should have been found")
  public void verifyNumberOfAuthorsFound(final int authorsFound) {
    this.search.verifyNumberOfAuthorsFound(authorsFound);
  }

  @Then("author (\\d+) should have the name '(.+)'")
  public void verifyAuthorAtPosition(final int position, final String author) {
    this.search.verifyAuthorAtPosition(position, author);
  }
}
