User can view release content

Narrative:
In order to support GLL package maintenance
As a build & release manager
I want to be able to view & alter the content of a release

Scenario: User can view release content
Given user is on Release Management page
And an existing release with a description of Viewable1
When user opens release with a description of Viewable1
Then the user can see the contents of the release described as Viewable1

Scenario: User can alter release content
Given user is on Release Management page
And an existing release with a description of Viewable2
When user opens release with a description of Viewable2
Then the user can change the description to Altered
