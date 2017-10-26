Feature: Access store.demoqa.com site
  Use selenium java with cucumber-jvm and navigate to website

  Scenario: Print title, url
    When I open demo store website
    Then I validate title