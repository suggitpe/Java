User can create new release versions through interaction

Narrative:
In order to support GLL package construction
As a build & release manager
I want to be able to create unique identifiers

Scenario: should be able to add a description to a release
When I create a new release with a description of NewSooperDooperRelease
Then a new release is created with a description of NewSooperDooperRelease

Scenario: should assign a unique id to the release
Given an existing release
When I create a new release
Then the version numbers of the releases are different

Scenario: should be able to assign a component version to a release
Given an existing version 1.1 of component MyNewComponent
And an existing version 1.2 of component MyNewComponent
When I create a new release with version 1.2 of component MyNewComponent
Then the release should contain a version 1.2 of component MyNewComponent

Scenario: Test pack association is not treated in the same as components

Scenario: Be able to see a history of past versions and their contents

Scenario: Be able to run test packs

Scenario: Be able to see the status of each version (installed)

