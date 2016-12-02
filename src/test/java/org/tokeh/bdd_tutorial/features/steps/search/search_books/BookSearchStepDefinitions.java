package org.tokeh.bdd_tutorial.features.steps.search.search_books;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.tokeh.bdd_tutorial.Book;
import org.tokeh.bdd_tutorial.features.steps.search.serenity.SearchSteps;

import java.util.List;

public class BookSearchStepDefinitions {
  @Steps
  SearchSteps search;

  @Given("^a library with books$")
  public void ensureLibraryIsPresentAndFilled() {
    this.search.ensureLibraryIsPresentAndFilled();
  }

  // Data table as list of lists. First row is ommitted because it contains the column titles.
  @Given("^the library contains the following books:$")
  public void addBooksAsTable(final DataTable books) {
    this.search.addNewBooks(books);
  }

  @Given("^.+book with the title '(.+)', written by '(.+)', published on (.+)$")
  public void addNewBook(final String title, final String author, final String published) {
    this.search.addNewBook(title, author, published);
  }

  @Given("^a book with the title (.*?), published on (.*?), written by (.*?)$")
  public void addNewBookParameterized(String title, String published, String author) {
    Serenity.getCurrentSession().clear();
    this.search.addNewBook(title, author, published);
  }

  @When("^the customer searches for books published between (\\d+) and (\\d+)$")
  public void searchByTimeSpan(final int from, final int to) {
    this.search.searchByTimeSpan(from, to);
  }

  @Then("(\\d+) books? should have been found$")
  public void verifyAmountOfBooksFound(final int booksFound) {
    this.search.verifyAmountOfBooksFound(booksFound);
  }

  @Then("Book (\\d+) should have the title '(.+)'$")
  public void verifyBookAtPosition(final int position, final String title) {
    this.search.verifyBookAtPosition(position, title);
  }

  @Then("a Book with the title (.*?) published on (.*?) should have been found")
  public void verifyBookAtPositionParameterized(String title, String published) {
    this.search.verifyBookAtFirstPosition(title, published);
  }

  @When("^the customer searches for books written by '(.+)'$")
  public void searchBookByAuthor(final String author) {
    this.search.searchByAuthor(author);
  }

  /*
   * OR: tags comma separated in one string
   * AND: tags comma separated in separate strings (~ for NOT)
   */
  @Before("@test#1")
  public void beforeScenarioTest1() {
    System.out.println("This happened before scenario tagged with @test#1");
  }

  @After("@test#1, @test#2")
  public void afterScenarioTest2() {
    System.out.println("This happened after scenario tagged with @test#1 OR @test#2");
  }

  @Before({"@test#1", "~@test#2"})
  public void beforeScenarioTest3() {
    System.out.println("This happened before scenario tagged with @test#1 AND NOT @test#2");
  }
}
