Feature: Customer shopping journey

  Scenario: Customer navigates through product categories
    Given Customer is on the home page
    When Customer navigates to "Phones" category
    Then Customer should see available phones
    When Customer navigates to "Laptops" category
    Then Customer should see available laptops
    When Customer navigates to "Monitors" category
    Then Customer should see available monitors

  @wip
  Scenario: Customer adds items to the cart
    Given Customer is on the home page
    When Customer navigates to "Laptops" category
    And Customer selects "Sony vaio i5"
    And Customer adds it to the cart
    Then Customer should see a pop-up confirmation
    And Customer moves to the "Home" page
    When Customer navigates to "Laptops" category
    Then Customer selects "Dell i7 8gb"
    And Customer adds it to the cart
    Then Customer should see a pop-up confirmation
    And Customer moves to the "Cart" page
    And Cart should display both "Sony vaio i5" and "Dell i7 8gb"
    When Customer removes "Dell i7 8gb" from the cart
    Then Cart should display "Sony vaio i5"
    Then Customer clicks on Place order
    And Customer fills in all web form fields
    And Customer clicks on Purchase
    Then Customer should see a purchase confirmation pop-up
    And Purchase ID and Amount should be captured and logged
    Then Purchase amount should equal the expected amount
