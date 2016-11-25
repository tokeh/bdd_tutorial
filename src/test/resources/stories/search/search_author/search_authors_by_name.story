Meta:
@tag extratags: tag

Narrative:
As a user
I want to find authors whose books are in the library
So that I don't have to look through all books individually

Scenario: Author has been found
Meta:
@path happy_path

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the costumer searches for an author with the name 'Tim Tomson'
Then 1 author should have been found
And author 1 should have the name 'Tim Tomson'

Scenario: Author has not been found
Meta:
@path special_case

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the costumer searches for an author with the name 'Tim Anderson'
Then 0 authors should have been found

Scenario: Find all authors
Meta:
@path special_case

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the costumer searches for all authors
Then 3 authors should have been found
And author 1 should have the name 'Anonymous'
And author 2 should have the name 'Fred Flintstone'
And author 3 should have the name 'Tim Tomson'