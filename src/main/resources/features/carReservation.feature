Feature: car reservation

  @Test
  Scenario: As a  user I want to be able to reserve a car
    Given toyota car model
    When  car selected
    And  click reservation and go through checkout flow
    And validate prices in checkout page with car details page
    And enter user information through checkout page
    And go to payment and enter Credit card number
    Then  car will be reserved successfully
