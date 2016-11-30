Narrative:
As a user
I want to find books by publication year
So that I can see what books are available that have been published in a certain time span.

Scenario: Some books have been found
Meta:
@tags path: happy_path, type: scenario
@path happy_path
@type scenario

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2013 and 2014
Then 2 books should have been found
And Book 1 should have the title 'Some other book'
And Book 2 should have the title 'One good book'

Scenario: No books have been found
Meta:
@tags path: special_case, type: scenario
@path special_case
@type scenario

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2009 and 2010
Then 0 books should have been found

Scenario: All books have been found
Meta:
@tags path: special_case, type: scenario
@path special_case
@type scenario

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books published between 2012 and 2014
Then 3 books should have been found
And Book 1 should have the title 'Some other book'
And Book 2 should have the title 'One good book'
And Book 3 should have the title 'How to cook a dino'