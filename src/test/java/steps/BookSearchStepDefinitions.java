package steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.tokeh.bdd_tutorial.Book;
import org.tokeh.bdd_tutorial.Library;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookSearchStepDefinitions {

  private final Library library = new Library();
  private List<Book> result;
  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @Steps
  BookSearchSteps bookSearch;

  // Pattern variants funktionieren leider nicht (in IntelliJ).
  @Given("a book with the title $title, written by $author, published on $published")
  @Alias("another book with the title $title, written by $author, published on $published")
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
    this.verifyBookAtPosition(position, title);
  }
}