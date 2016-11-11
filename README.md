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
These examples are only tested in IntelliJ IDEA Community Edition.

##Evaluation
###Cucumber with Java
Features are written in *Gherkin* and stored in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:
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
The mapping between scenarios and implementation is achieved by *Java annotations* and *pattern matching*. The mapping from
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

###Cucumber with Java and Serenity
Features are written in *Gherkin* and stored in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:
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
Serenity takes care of a lot of the configuration glue code. Only an empty *JUnit runner* per feature is needed.
The runner contains the path to the feature file. The runner has to be in the same package as the implementation.
```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/search/book_search.feature")
public class SearchTests { }
```
Serenity extends Cucumber in a way that not only *Given-When-Then* steps can be used but also Serenity *Steps*. These steps are
small reusable chunks of code that are grouped in a normal Java class. This concept supports maintainability and flexibility
especially in large code bases.
```java
@Step
public void addNewBook(final String title, final String author, final String published) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
}
```
The implementation of the scenario steps looks almost like the one of plain Cucumber. Unlike the plain Cucumber code this implementation
is calling the reusable Serenity *Steps* on the *@Step* object instead of calling all the test code directly.
```java
@Steps
BookSearchSteps bookSearch;

@Given(".+book with the title '(.+)', written by '(.+)', published on (.+)")
public void addNewBook(final String title, final String author, final String published) {
  this.bookSearch.addNewBook(title, author, published);
}
```
The Serenity report can be found under __/target/site/serenity/index.html__

###Cucumber with Scala
Features are written in *Gherkin* and stored in *.feature* files. Gherkin uses the BDD *Given-When-Then* pattern:
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
have the same text but different values and mapping them to the same implementation. The usage of *ScalaTest* allows an easier and
better readable test implementation.
```scala
Given(""".+book with the title '(.+)', written by '(.+)', published on (.+)""") {
  (title: String, author: String, published: String) =>
    ...
}
```
The only disadvantage is that if scenarios are changed the texts of the annotations in the implementation have to be changed as well.
Finding the texts that have to be changed is quite easy because all scenarios that can't be matched are highlighted in the editor
(if the right plug-ins are installed) and the corresponding tests are failing.

###JBehave with Java
JBehave uses *.story* files to store the different scenarios. Just like Cucumber features they are written in native language and
use the BDD *Given-When-Then* pattern:
```
Meta:

Narrative:
As a user
I want to find books by publication year
So that I can see what books are available by an author

Scenario: Search books by publication year
Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2013 and 2014
Then 2 books should have been found
And Book 1 should have the title 'Some other book'
And Book 2 should have the title 'One good book'
```
JBehave also needs some configuration glue code. That code configures where JBehave has to look for the story files, where the
step definitions are and how format the output.
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
The implementation works similar to Cucumber. Scenarios are matched by pattern matching where the text has to be exactly the same.
If the same implementation should be used for different steps of the scenario the *@Alias* annotation can be used.
Variables are marked with a *$* and mapped to the corresponding method parameters.
```java
@Given("a book with the title $title, written by $author, published on $published")
@Alias("another book with the title $title, written by $author, published on $published")
public void addNewBook(final String title, final String author, final String published) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
}
```

###JBehave with Java and Serenity
JBehave uses *.story* files to store the different scenarios. Just like Cucumber features they are written in native language and
use the BDD *Given-When-Then* pattern:
```
Meta:

Narrative:
As a user
I want to find books by publication year
So that I can see what books are available by an author

Scenario: Search books by publication year
Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2013 and 2014
Then 2 books should have been found
And Book 1 should have the title 'Some other book'
And Book 2 should have the title 'One good book'
```
In addition to the *narrative* part of the *.story* files all parts like *features*, *capabilities*, etc. can have narrative.txt
files which contain a description in native language. These descriptions are used for the test reports by Serenity.

Serenity takes care of a lot of the configuration glue code. Only an empty *JUnit runner* that extends *SerenityStories* is needed.
```java
public class AcceptanceTests extends SerenityStories { }
```
Serenity extends JBehave in a way that not only *Given-When-Then* steps can be used but also Serenity *Steps*. These steps are
small reusable chunks of code that are grouped in a normal Java class. This concept supports maintainability and flexibility
especially in large code bases.
```java
@Step
public void addNewBook(final String title, final String author, final String published) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateTimeFormatter)));
}
```
The implementation of the scenario steps looks almost like the one of plain JBehave. Unlike the plain JBehave code this implementation
is calling the reusable Serenity *Steps* on the *@Step* object instead of calling all the test code directly.
```java
@Steps
BookSearchSteps bookSearch;

@Given("a book with the title $title, written by $author, published on $published")
@Alias("another book with the title $title, written by $author, published on $published")
public void addNewBook(final String title, final String author, final String published) {
  this.bookSearch.addNewBook(title, author, published);
}
```
The Serenity report can be found under __/target/site/serenity/index.html__

###JBehave with Scala
JBehave uses *.story* files to store the different scenarios. Just like Cucumber features they are written in native language and
use the BDD *Given-When-Then* pattern:
```
Meta:

Narrative:
As a user
I want to find books by publication year
So that I can see what books are available by an author

Scenario: Search books by publication year
Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2013 and 2014
Then 2 books should have been found
And Book 1 should have the title 'Some other book'
And Book 2 should have the title 'One good book'
```
JBehave also needs some configuration glue code. That code configures where JBehave has to look for the story files, where the
step definitions are and how format the output.
```scala
protected def storyPaths: List[String] = new StoryFinder().findPaths(
  CodeLocations.codeLocationFromPath("src/test/resources"), "**/*.story", "**/exclude_*.story")

override def configuration: Configuration = new MostUsefulConfiguration()
  .useStoryLoader(new LoadFromClasspath(this.getClass))
  .useStoryReporterBuilder(new StoryReporterBuilder()
  .withFormats(Format.XML, Format.IDE_CONSOLE, Format.CONSOLE, Format.HTML, Format.TXT))

override def stepsFactory: InjectableStepsFactory = new InstanceStepsFactory(configuration, new BookSearchSteps)
```
The implementation works similar to Cucumber. Scenarios are matched by pattern matching where the text has to be exactly the same.
If the same implementation should be used for different steps of the scenario the *@Alias* annotation can be used.
Variables are marked with a *$* and mapped to the corresponding method parameters.
```scala
@Given("a book with the title $title, written by $author, published on $published")
@Alias("another book with the title $title, written by $author, published on $published")
def addNewBook(title: String, author: String, published: String) {
  this.library.addBook(new Book(title, author, LocalDate.parse(published, dateFormatter)))
}
```
When using Scala the Java JBehave annotations still have to be used. The combination of JBehave and Scala is only reasonable when
it is used in a scenario where there is a real advantage of using a Scala test library like ScalaTest instead of a Java based one like JUnit.

###Plain ScalaTest
Unlike Cucumber and JBehave ScalaTest doesn't use separate files for feature and scenario description and implementation.
In ScalaTest all these parts are combined in one place. This comes in handy if descriptions are changed so they only have
to be changed in one place. The downside is that it is not as readable as Cucumber feature files or JBehave story files,
especially for non-technical folks. Another, and possibly even bigger, downside is that ScalaTest isn't compatible to Serenity,
which is excellent for reporting.
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
