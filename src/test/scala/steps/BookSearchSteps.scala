package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.tokeh.bdd_tutorial.Book
import org.tokeh.bdd_tutorial.Library

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.List

import org.scalatest.Matchers._

class BookSearchSteps {

  private val library: Library = new Library
  private var result: List[Book] = null

  val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

  // Pattern variants funktionieren leider nicht (in IntelliJ).
  @Given("a book with the title $title, written by $author, published on $published")
  @Alias("another book with the title $title, written by $author, published on $published")
  def addNewBook(title: String, author: String, published: String) {
    this.library.addBook(new Book(title, author, LocalDate.parse(published, dateFormatter)))
  }

  @When("the customer searches for books published between $from and $to$")
  def setSearchParameters(from: Int, to: Int) {
    result = library.findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to))
  }

  @Then("$booksFound books should have been found")
  def verifyAmountOfBooksFound(booksFound: Int) {
    result should {
      not be empty and
        have size booksFound
    }
  }

  @Then("Book $position should have the title $title")
  def verifyBookAtPosition(position: Int, title: String) {
    result should not be empty
    result.get(position - 1).getTitle should be (title)
  }
}