Feature: Search books by author
  As a user
  I want to find books by author
  So that I can see what books are available that have been written by a certain author.

  Background:
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012

  @path_happy_path @type_scenario
  Scenario: All books written by certain author have been found
    Given a library with books
    When the customer searches for books written by 'Anonymous'
    Then 1 book should have been found
    And Book 1 should have the title 'One good book'

  @path_special_case @type_scenario
  Scenario: Author hasn't been found
    Given a library with books
    When the customer searches for books written by 'Not an author'
    Then 0 book should have been found
