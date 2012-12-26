Feature: Talk To The World
As a presenter
I want to create a web application that "talks to the world"
so that I can present a Cucumber example

  Scenario: "GET" request made to /hello
    When a request is made to "/hello"
    Then the response body should be "Hello, World!"
