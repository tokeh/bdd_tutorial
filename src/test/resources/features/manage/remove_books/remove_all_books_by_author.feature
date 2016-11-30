Feature: Remove all books by author
  As an administrator
  I want to remove all books by an author
  So that I don't have to remove them individually

  @path_happy_path @type_dummy
  Scenario: Remove all books written by an author
  #Dummy scenario to show pending scenarios in Serenity report
    Given a system state
    When I do something
    Then system is in a different state
