Feature: User adds items to cart and views cart

Scenario: The user adds items to cart
Given the user navigates to the store page
When the user clicks on an item
Then an iFrame opens up
When the user clicks on the "cart" button
Then the user is redirected to the "Cart" page

#
#Scenario: The user views the cart
#When the user clicks on the "cart" button
#Then the user is redirected to the "Cart" page