Narrative:
As a user
I want to find books by author
So that I can see what books are available that have been written by a certain author.

Scenario: All books written by certain author have been found
Meta:
@tags path: happy_path, type: scenario
@path happy_path
@type scenario

GivenStories: stories/search/GivenStories/populate_library.story
Given a library with books
When the customer searches for books written by 'Anonymous'
Then 1 book should have been found
And Book 1 should have the title 'One good book'

Scenario: Author hasn't been found
Meta:
@tags path: special_case, type: scenario
@path special_case
@type scenario

GivenStories: stories/search/GivenStories/populate_library.story
Given a library with books
When the customer searches for books written by 'Not an author'
Then 0 book should have been found