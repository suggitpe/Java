User can create new release versions through interaction

Narrative:
In order to support GLL package construction
As a build & release manager
I want to be able to create unique identifiers

Scenario: User can create unique identifiers from home page
Given user is on release management page
When user requests a new release
Then a unique identifier is displayed on a new page
When user returns to release management page 
And requests a new release
Then a different unique identifier is displayed on a new page

