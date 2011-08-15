User can traverse to funky child page

Narrative:
In order to provide a basic scenario for behavioural tests
As a application developer
I want to ensure that user stories correctly execute across pages


Scenario: User opens funky page and returns to Home

Given user is on Home page
When user opens funky page
Then funky page is shown
When user returns to home page
Then Home page is shown

Scenario: foo

Given user is on Home page
When user makes a foolish error
Then the web app does not fall over
