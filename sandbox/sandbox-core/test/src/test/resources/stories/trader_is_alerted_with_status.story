Trader is alerted of status

Narrative:
Description I want to track the prices of stocks so that I can trade more effectively.  As such I need to be alerted when stock prices go above a threshold.
In order that I make a profit
As a person who likes to dabble in the stock market
I want to monitor stock market prices against thresholds

Scenario: when a stock price is below the threshold, the alert will be off 
Given a stock of symbol STK1 and a threshold of 10.0
When the stock is traded at 5.0
Then the alert status should be OFF

Scenario: when a stock price is above a threshold, the alert will be on
Given a stock of symbol STK1 and a threshold of 10.0
When the stock is traded at 15.0
Then the alert status should be ON

Scenario: when a stock price is the same as the threshold, the alert will be on
Given a stock of symbol STK1 and a threshold of 10.0
When the stock is traded at 10.0
Then the alert status should be ON

Scenario: parameterised scenario
Given a stock of <symbol> and a threshold <threshold>
When the stock is traded with <price>
Then the alert status will be <status>

Examples:
|symbol|threshold|price|status|
|STK1  |20.0     |5.0  |OFF   |
|STK1  |20.0     |10.0 |OFF   |
|STK1  |20.0     |15.0 |OFF   |
|STK1  |20.0     |19.9 |OFF   |
|STK1  |20.0     |20.0 |ON    |
|STK1  |20.0     |25.0 |ON    |
