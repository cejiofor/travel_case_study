Feature: User adds a sub-item to the cart

Scenario: User adds an Evening dress to the cart
Given the user navigates to the store home page
When the user hovers over the "WOMEN" header
Then a submenu is displayed
When the user clicks on "Evening Dresses"
Then the page will redirect to the "Evening Dress" page
When the user adds the "Printed Dress" to the cart
Then an iFrame opens
And the user can click continue
When the user clicks on the cart button
Then the page will redirect to the cart page
