package feature

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util

import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitRunner
import org.tokeh.bdd_tutorial.{Book, Library}

@RunWith(classOf[JUnitRunner])
class BookSearch extends FeatureSpec with GivenWhenThen {
  private val library: Library = new Library
  private var result: util.ArrayList[Book] = null

  val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

  feature("Book search") {
    info("To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book")

    scenario("Search books by publication year") {

      Given("a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013")
        this.library.addBook(new Book("One good book", "Anonymous", LocalDate.parse("14.03.2013", dateFormatter)))
      And("another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014")
        this.library.addBook(new Book("Some other book", "Tim Tomson", LocalDate.parse("23.08.2014", dateFormatter)))
      And("another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012")
        this.library.addBook(new Book("How to cook a dino", "Fred Flintstone", LocalDate.parse("01.01.2012", dateFormatter)))

      When("the customer searches for books published between 2013 and 2014")
        result = library.findBooks(LocalDate.now().withYear(2013), LocalDate.now().withYear(2014))

      Then("2 books should have been found")
        result.size should be(2)
      And("Book 1 should have the title 'Some other book'")
        result.get(0).getTitle should {
          not be empty and
            be ("Some other book")
        }
      And("Book 2 should have the title 'One good book'")
        result.get(1).getTitle should {
          not be empty and
            be ("One good book")
        }
    }
  }
}
