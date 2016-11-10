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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class BookSearchSteps {
  private final Library library = new Library();
  private List<Book> result;

  @Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
  public void addNewBook(final String title, final String author, @Format("dd.MM.yyyy") final Date published) {
    this.library.addBook(new Book(title, author, convertToLocalDate(published)));
  }

  @When("^the customer searches for books published between (\\d+) and (\\d+)$")
  public void setSearchParameters(@Format("yyyy") final Date from, @Format("yyyy") final Date to) {
    result = library.findBooks(convertToLocalDate(from), convertToLocalDate(to));
  }

  @Then("(\\d+) books should have been found$")
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(result.size(), booksFound);
  }

  @And("Book (\\d+) should have the title '(.+)'$")
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(result.get(position - 1).getTitle(), title);
  }

  private LocalDate convertToLocalDate(final Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
