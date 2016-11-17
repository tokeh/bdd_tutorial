Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  @test#1
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'

  @test#2
  Scenario: Search books by author
    Given another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
    And a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
    When the customer searches books that have been written by 'Tim Tomson'
    Then 1 books should have been found
    And Book 1 should be written by 'Tim Tomson'

  Scenario: Search author by name
    # Dummy scenario to show pending scenarios in Serenity report
    Given a system state
    When I do something
    Then system is in a different state