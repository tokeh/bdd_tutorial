package feature

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util

import cucumber.api.Format
import cucumber.api.java.en.{And, Given, Then, When}
import org.tokeh.bdd_tutorial.Book
import org.tokeh.bdd_tutorial.Library
import org.scalatest.Matchers._

class BookSearchStepsScala {
  private val library: Library = new Library
  private var result: util.ArrayList[Book] = null

  @Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
  def addNewBook(title: String, author: String, @Format("dd.MM.yyyy") published: Date) {
    this.library.addBook(new Book(title, author, convertDateToLocalDate(published)))
  }

  @When("^the customer searches for books published between (\\d+) and (\\d+)$")
  def setSearchParameters(@Format("yyyy") from: Date, @Format("yyyy") to: Date) {
    result = library.findBooks(convertDateToLocalDate(from), convertDateToLocalDate(to))
  }

  @Then("(\\d+) books should have been found$")
  def verifyAmountOfBooksFound(booksFound: Int) {
    result.size should equal(booksFound)
  }

  @And("Book (\\d+) should have the title '(.+)'$")
  def verifyBookAtPosition(position: Int, title: String) {
    result.get(position - 1).getTitle should equal (title)
  }

  def convertDateToLocalDate(date: Date) : LocalDate = date.toInstant.atZone(ZoneId.systemDefault).toLocalDate
}
