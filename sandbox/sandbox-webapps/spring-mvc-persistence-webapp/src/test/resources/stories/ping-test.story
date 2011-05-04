User can traverse to child page

Narrative:
In order to provide a basic scenario for behavioural tests
As a application developer
I want to ensure that user stories correctly execute


Scenario: User opens ping test page and returns to Home

Given user is on Home page
When user opens Ping Test page
Then Ping Test page is shown
When user returns to home page
Then Home page is shown