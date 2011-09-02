User can view release content

Narrative:
In order to support GLL package maintenance
As a build & release manager
I want to be able to view & alter the content of a release

Scenario: User can view release content
Given an existing release with a description of Viewable1
And user is on Release Management page
When user opens release with a description of Viewable1
Then the user can see the contents of the release described as Viewable1

Scenario: User can alter release content
Given an existing release with a description of Viewable2
And user is on Release Management page
When user opens release with a description of Viewable2
Then the user can change the description from Viewable2 to AlteredVersion

Scenario: User can delete a release
Given an existing release with a description of deleteMe
When the user deletes the release with the description of deleteMe
Then no release exists with a description of deleteMe

