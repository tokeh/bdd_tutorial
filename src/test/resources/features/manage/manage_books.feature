Feature: Manage books
  As an administrator
  I want to manage the books that are available in the library
  So that users can search for and borrow them

  Scenario: Add single book
    Given a library to store and manage books
    When an administrator adds a single book with the title 'Totally awesome book'
    Then the library contains a book with the title 'Totally awesome book'

  Scenario: Add multiple books
    # Dummy scenario to show pending scenarios in Serenity report

  Scenario: Remove single books
    # Dummy scenario to show pending scenarios in Serenity report

  Scenario: Remove multiple books
    # Dummy scenario to show pending scenarios in Serenity report