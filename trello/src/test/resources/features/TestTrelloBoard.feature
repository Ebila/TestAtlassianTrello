Feature: Test the Trello Board Creation
  Scenario: Create a Trello Board using the backend API
    When a request is made to create a new Trello board "TrelloBoard1" using the endpoint
    Then the board is created successfully

  Scenario: Fetch the details of the board that was created
    When the details of the board are fetched then they are returned
