User can create new release versions through interaction

Narrative:
In order to support GLL package construction
As a build & release manager
I want to be able to create unique identifiers

Scenario: User can create a new version
Given user is on Release Management page
When user creates a new release with a description of CrazyNewRelease
Then a new release is displayed with a description of CrazyNewRelease

Scenario: User can create a new version with a unique release number
Given user is on Release Management page
When user creates a new release with a description of Foo
And user creates a new release with a description of Bar
Then the release number of Foo is different to the release number of Bar

Scenario: User can choose versions of components that make up the GGL release version
Given user is on Release Management page
When user requests a new release
Then a number of component versions can be selected to that parent version

Scenario: Tests are treated as a component
Given user is on Release Management page
When user requests a new release
Then they are forced to select a test version

Scenario: Test pack association is not treated in the same as components

Scenario: Be able to see whats in each version

Scenario: Be able to see a history of past versions and their contents

Scenario: Be able to run test packs

Scenario: Be able to see the status of each version (installed)

