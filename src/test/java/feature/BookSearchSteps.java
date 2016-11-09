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
  private final Library library = new org.tokeh.bdd_tutorial.Library();
  private List<Book> result;

  //@Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
  public void addNewBook(final String title, final String author, @Format("dd.MM.yyyy") final Date published) {
    LocalDate localDatePublished = published.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    org.tokeh.bdd_tutorial.Book book = new org.tokeh.bdd_tutorial.Book(title, author, localDatePublished);
    this.library.addBook(book);
  }

  //@When("^the customer searches for books published between (\\d+) and (\\d+)$")
  public void setSearchParameters(@Format("yyyy") final Date from, @Format("yyyy") final Date to) {
    LocalDate localDateFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDateTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    result = library.findBooks(localDateFrom, localDateTo);
  }

  //@Then("(\\d+) books should have been found$")
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(result.size(), booksFound);
  }

  //@And("Book (\\d+) should have the title '(.+)'$")
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(result.get(position - 1).getTitle(), title);
  }
}
