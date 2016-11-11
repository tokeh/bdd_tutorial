Meta:

Narrative:
As an administrator
I I want to add a book to the library
So that users of the library can search for it

Scenario: Add single book
Given a library to store and manage books
When an administrator adds a single book with the title 'Totally awesome book'
Then the library contains a book with the title 'Totally awesome book'