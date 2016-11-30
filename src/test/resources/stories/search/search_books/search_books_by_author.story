Narrative:
As a user
I want to find books by author
So that I can see what books are available that have been written by a certain author.

Scenario: All books written by certain author have been found
Meta:
@tags path: happy_path, type: scenario
@path happy_path
@type scenario

GivenStories: stories/search/search_author/search_authors_by_name.story

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books written by 'Anonymous'
Then 1 book should have been found
And Book 1 should have the title 'One good book'

Scenario: Author hasn't been found
Meta:
@tags path: special_case, type: scenario
@path special_case
@type scenario

Given a book with the title 'One good book', written by 'Anonymous', published on 14.03.2013
And another book with the title 'Some other book', written by 'Tim Tomson', published on 23.08.2014
And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published on 01.01.2012
When the customer searches for books written by 'Not an author'
Then 0 book should have been found