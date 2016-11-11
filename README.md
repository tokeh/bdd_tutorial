# Behavior Driven Development Tutorial
## Branches
- __master__: irrelevant
- __cucumber_java__: Features in [Cucumber](https://cucumber.io/) and implementation in Java
- __cucumber_scala__: Features in [Cucumber](https://cucumber.io/) and implementation in Scala
- __jbehave_java__: Features in [JBehave](http://jbehave.org/) and implementation in Java
- __jbehave_java_serenity__: Features in [JBehave](http://jbehave.org/), implementation in Java and reporting with [Serenity](http://www.thucydides.info/docs/serenity/)
- __jbehave_scala__: Features in [JBehave](http://jbehave.org/) and implementation in Scala
- __plain_scalatest__: Features and implementation combined in Scala / [ScalaTest](http://www.scalatest.org/)

## Running the tests
All tests are run with the Gradle __test__ task. In order to generate the html reports when using Serenity just run the Gradle __aggregate__ task after running the __test__ task.

##Trouble shooting
Sometimes it's helpful to run the Gradle __clean__ task if the application behaves strangely after switching between branches.

##Evaluation
###Cucumber with Java
Features are written in *Gherkin* and stores in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:

```
Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book ...
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
```
The mapping between scenarios and implementation is achieved by *Java Annotations* and *pattern matching*. The mapping from scenario values to parameters is done the same way. That allows writing steps (e.g. __Given__... __And__...) that have the same text but different values and mapping them to the same implementation.
```java
@Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
public void addNewBook(final String title, final String author, final String published) {
  ...
}
```
The only disadvantage is that if scenarios are changed the texts of the annotations in the implementation have to be changed as well. Finding the texts that have to be changed is quite easy because all scenarios that can't be matched are highlighted in the editor (if the right plug-ins are installed) and the corresponding tests are failing.

###Cucumber with Scala


###JBehave with Java


###JBehave with Java and Serenity


###Jbehave with Scala


###Plain ScalaTest