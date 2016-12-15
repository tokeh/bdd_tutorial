@jira
@issue:BDDTHESIS-1
Feature: Search books by year
  As a user
  I want to find books by publication year
  So that I can see what books are available that have been published in a certain time span.

  @path_happy_path @type_scenario
  Scenario: Some books have been found
    Given the library contains the following books:
      | title              | author          | published  |
      | One good book      | Anonymous       | 14.03.2013 |
      | Some other book    | Tim Tomson      | 23.08.2014 |
      | How to cook a dino | Fred Flintstone | 01.01.2012 |
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'

  @path_special_case @type_scenario
  Scenario: No books have been found
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
    When the customer searches for books published between 2009 and 2010
    Then 0 books should have been found

  @path_special_case @type_scenario
  Scenario Outline: All books have been found
    Given a book with the title <title>, published on <published>, written by <author>
    When the customer searches for books published between 2012 and 2014
    Then a Book with the title <title> published on <published> should have been found

    Examples:
      | title              | author          | published  |
      | One good book      | Anonymous       | 14.03.2013 |
      | Some other book    | Tim Tomson      | 23.08.2014 |
      | How to cook a dino | Fred Flintstone | 01.01.2012 |
