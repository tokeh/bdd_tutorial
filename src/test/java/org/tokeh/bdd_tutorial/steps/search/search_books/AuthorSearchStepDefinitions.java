package org.tokeh.bdd_tutorial.steps.search.search_books;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class AuthorSearchStepDefinitions {
  @Steps
  SearchSteps search;

  @When("the costumer searches for an author with the name '$author'")
  public void searchAuthorByName(final String author) {
    this.search.searchAuthorByName(author);
  }

  @When("the costumer searches for all authors")
  public void searchForAllAuthor() {
    this.search.searchForAllAuthors();
  }

  @Then("$authorsFound author{s|} should have been found")
  public void verifyNumberOfAuthorsFound(final int authorsFound) {
    this.search.verifyNumberOfAuthorsFound(authorsFound);
  }

  @Then("author $position should have the name '$author'")
  public void verifyAuthorAtPosition(final int position, final String author) {
    this.search.verifyAuthorAtPosition(position, author);
  }
}
