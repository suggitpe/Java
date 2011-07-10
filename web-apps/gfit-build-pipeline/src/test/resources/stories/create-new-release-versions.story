User can create new release versions through interaction

Narrative:
In order to support GLL package construction
As a build & release manager
I want to be able to create unique identifiers

Scenario: User can create unique identifiers from Release Management page
Given user is on Release Management page
When user requests a new release
Then the new release version form is shown
When the user enters a release version comment of Foo
And clicks Add New Release Version
Then the release management page is shown
And a unique identifier is displayed for the release described as Foo
When user requests a new release
Then the new release version form is shown
When the user enters a release version comment of Bar
And clicks Add New Release Version
Then the release management page is shown
And a unique identifier is displayed for the release described as Bar

Scenario: User can choose versions of components that make up the GGL release version
Given user is on Release Management page
When user requests a new release
Then a number of component versions can be selected to that parent version

Scenario: Tests are treated as a component
Given user is on Release Management page
When user requests a new release
Then they are forced to select a test version

