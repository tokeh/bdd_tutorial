package feature;

import cucumber.api.Format;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.tokeh.bdd_tutorial.Book;
import org.tokeh.bdd_tutorial.Library;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookSearchSteps {
  private final Library library = new Library();
  private List<Book> result;

  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
  public void addNewBook(final String title, final String author, final String published) {
    this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
  }

  @When("^the customer searches for books published between (\\d+) and (\\d+)$")
  public void setSearchParameters(final int from, final int to) {
    result = library.findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to));
  }

  @Then("(\\d+) books should have been found$")
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(result.size(), booksFound);
  }

  @And("Book (\\d+) should have the title '(.+)'$")
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(result.get(position - 1).getTitle(), title);
  }
}
