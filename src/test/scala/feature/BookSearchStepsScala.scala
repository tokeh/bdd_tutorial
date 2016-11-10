package feature

import java.util

import cucumber.api.scala.{EN, ScalaDsl}
import org.joda.time.DateTime
import org.scalatest.Matchers._

import org.tokeh.bdd_tutorial.Book
import org.tokeh.bdd_tutorial.Library

class BookSearchStepsScala extends ScalaDsl with EN {
  private val library: Library = new Library
  private var result: util.ArrayList[Book] = null

  /*Given(""".+book with the title '(.+)', written by '(.+)', published on (.+)""") {
    (title: String, author: String, published: String) => this.library.addBook(new Book(title, author, DateTime.parse(published)))
  }

  When("""^the customer searches for books published between (\d+) and (\d+)$""") {
    (from: String, to: String) => result = library.findBooks(DateTime.parse(from), DateTime.parse(to))
  }

  Then("""(d+) books should have been found$""") {
    (booksFound: Int) => result.size should equal(booksFound)
  }

  And("""Book (d+) should have the title '(.+)'$""") {
    (position: Int, title: String) => result.get(position - 1).getTitle should equal (title)
  }*/
}
