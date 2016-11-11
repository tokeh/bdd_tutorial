package steps;

import net.thucydides.core.annotations.Step;
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

  // Pattern variants funktionieren leider nicht (in IntelliJ).
  @Step
  public void addNewBook(final String title, final String author, final String published) {
    this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
  }

  @Step
  public void searchByTimeSpan(final int from, final int to) {
    result = library.findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to));
  }

  @Step
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(result.size(), booksFound);
  }

  @Step
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(result.get(position - 1).getTitle(), title);
  }
}
