# Behavior Driven Development Tutorial
## Branches
- __master__: irrelevant
- __cucumber_java__: Features in [Cucumber](https://cucumber.io/) and implementation in Java
- __cucumber_scala__: Features in [Cucumber](https://cucumber.io/) and implementation in Scala
- __jbehave_java__: Features in [JBehave](http://jbehave.org/) and implementation in Java
- __jbehave_java_serenity__: Features in [JBehave](http://jbehave.org/), implementation in Java and
reporting with [Serenity](http://www.thucydides.info/docs/serenity/)
- __jbehave_scala__: Features in [JBehave](http://jbehave.org/) and implementation in Scala
- __plain_scalatest__: Features and implementation combined in Scala/[ScalaTest](http://www.scalatest.org/)

## Running the tests
All tests are run with the Gradle __test__ task. In order to generate the html reports when using Serenity just
run the Gradle __aggregate__ task after running the __test__ task.

##Trouble shooting
Sometimes it's helpful to run the Gradle __clean__ task if the application behaves strangely after switching between branches.

##Evaluation
###Cucumber with Java
Features are written in *Gherkin* and stores in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:
```
Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways
  to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book ...
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
```
The mapping between scenarios and implementation is achieved by *Java Annotations* and *pattern matching*. The mapping from
scenario values to parameters is done the same way. That allows writing steps (e.g. __Given__... __And__...) that have the same
text but different values and mapping them to the same implementation.
```java
@Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
public void addNewBook(final String title, final String author, final String published) {
  ...
}
```
The only disadvantage is that if scenarios are changed the texts of the annotations in the implementation have to be changed
as well. Finding the texts that have to be changed is quite easy because all scenarios that can't be matched are highlighted
in the editor (if the right plug-ins are installed) and the corresponding tests are failing.

###Cucumber with Scala
Features are written in *Gherkin* and stores in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:
```
Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways
  to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book ...
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
```
The mapping between scenarios and implementation is achieved by the *Cucumber Scala DSL (Given-When-Then)* and *pattern matching*.
The mapping from scenario values to parameters is done the same way. That allows writing steps (e.g. __Given__... __And__...) that
have the same text but different values and mapping them to the same implementation. The usage of ScalaTest allows an easier and
better readable test implementation.
```scala
Given(""".+book with the title '(.+)', written by '(.+)', published on (.+)""") {
  (title: String, author: String, published: String) =>
    this.library.addBook(new Book(title, author, LocalDate.parse(published, dateFormatter)))
}
```
The only disadvantage is that if scenarios are changed the texts of the annotations in the implementation have to be changed as well.
Finding the texts that have to be changed is quite easy because all scenarios that can't be matched are highlighted in the editor
(if the right plug-ins are installed) and the corresponding tests are failing.

###JBehave with Java
.story files
```
Meta:

Narrative:
As a user
I want to find books by author
So that I can see what books were published in a specific time span

Scenario: scenario description
Given a system state
When I do something
Then system is in a different state
```
Glue code
```java
@Override
protected List<String> storyPaths() {
  return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"),
      "**/*.story", "**/exclude_*.story");
}

@Override
public Configuration configuration() {
  return new MostUsefulConfiguration()
    .useStoryLoader(new LoadFromClasspath(this.getClass()))
    .useStoryReporterBuilder(new StoryReporterBuilder()
    .withFormats(Format.XML, Format.IDE_CONSOLE, Format.CONSOLE, Format.HTML, Format.TXT));
}

@Override
public InjectableStepsFactory stepsFactory() {
  return new InstanceStepsFactory(configuration(), new BookSearchSteps());
}
```
Implementation
```java
@Given("a book with the title $title, written by $author, published on $published")
@Alias("another book with the title $title, written by $author, published on $published")
public void addNewBook(final String title, final String author, final String published) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
}
```

###JBehave with Java and Serenity
.story files
```
Meta:

Narrative:
As a user
I want to find books by author
So that I can see what books were published in a specific time span

Scenario: scenario description
Given a system state
When I do something
Then system is in a different state
```
narrative.txt in folders
Glue code: empty JUnit runner
```java
public class AcceptanceTests extends SerenityStories { }
```
Reusable Steps
```java
@Step
public void addNewBook(final String title, final String author, final String published) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
}
```
Step definitions
```java
@Steps
BookSearchSteps bookSearch;

@Given("a book with the title $title, written by $author, published on $published")
@Alias("another book with the title $title, written by $author, published on $published")
public void addNewBook(final String title, final String author, final String published) {
  this.bookSearch.addNewBook(title, author, published);
}
```

###JBehave with Scala
Glue code
```scala

```
Implementation
```scala

```

###Plain ScalaTest
```scala
feature("Book search") {
  info("To allow a customer to find his favourite books quickly,")
  info("the library must offer multiple ways to search for a book")
  
  scenario("Search books by publication year") {

    Given("a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013")
      ... code ...
    And("another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014")
      ... code ...
    And("another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012")
      ... code ...

    When("the customer searches for books published between 2013 and 2014")
      ... code ...

    Then("2 books should have been found")
      result.size should equal(2)
    And("Book 1 should have the title 'Some other book'")
      result.get(0).getTitle should equal ("Some other book")
    And("Book 2 should have the title 'One good book'")
      result.get(1).getTitle should equal ("One good book")
  }
}
```
