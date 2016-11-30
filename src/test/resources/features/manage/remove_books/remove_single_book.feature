Feature: Remove single book from library
  As an administrator
  I want to remove books from the library
  So that users can't find them by search if they are not part of the library anymore

  @path_happy_path @type_dummy
  Scenario: Remove a single book from the library
  #Dummy scenario to show pending scenarios in Serenity report
    Given a system state
    When I do something
    Then system is in a different state
