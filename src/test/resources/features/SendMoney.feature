Feature: Send money to a friend
  This feature describes a scenario to send money to a friend using Revolut

  Scenario: Send money to a friend
    Given Danny has 200 euros on his Revolut account
    And Larry has 50 euros on his Revolut account
    When Danny sends 100 euros to Larry
    Then Danny will have 100 euros on his Revolut account
    And Larry will have 150 euros on this Revolut account