package steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.tokeh.bdd_tutorial.Book;
import org.tokeh.bdd_tutorial.Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookSearchSteps {

  private final Library library = new Library();
  private List<Book> result;
  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @Steps
  

  // Pattern variants funktionieren leider nicht (in IntelliJ).
  @Given("a book with the title $title, written by $author, published on $published")
  @Alias("another book with the title $title, written by $author, published on $published")
  public void addNewBook(final String title, final String author, final String published) {
    this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
  }

  @When("the customer searches for books published between $from and $to$")
  public void setSearchParameters(final int from, final int to) {
    result = library.findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to));
  }

  @Then("$booksFound books should have been found")
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(result.size(), booksFound);
  }

  @Then("Book $position should have the title $title")
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(result.get(position - 1).getTitle(), title);
  }
}