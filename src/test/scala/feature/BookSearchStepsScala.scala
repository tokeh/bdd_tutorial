package feature

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util

import cucumber.api.Format
import cucumber.api.java.en.{And, Given, Then, When}
import cucumber.api.scala.{EN, ScalaDsl}
import org.tokeh.bdd_tutorial.Book
import org.tokeh.bdd_tutorial.Library
import org.scalatest.Matchers._

class BookSearchStepsScala extends ScalaDsl with EN {
  val library: Library = new Library
  var result: util.ArrayList[Book] = null

  val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

  Given(""".+book with the title '(.+)', written by '(.+)', published on (.+)""") {
    (title: String, author: String, published: String) =>
      this.library.addBook(new Book(title, author, LocalDate.parse(published, dateFormatter)))
  }

  When("""^the customer searches for books published between (\d+) and (\d+)$""") {
    (from: Int, to: Int) => result = library.findBooks(LocalDate.now().withYear(from), LocalDate.now().withYear(to))
  }

  Then("""(\d+) books should have been found$""") {
    (booksFound: Int) => result.size should equal(booksFound)
  }

  And("""Book (\d+) should have the title '(.+)'$""") {
    (position: Int, title: String) => result.get(position - 1).getTitle should equal (title)
  }
}
