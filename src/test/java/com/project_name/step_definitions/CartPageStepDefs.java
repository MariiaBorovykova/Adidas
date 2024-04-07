package com.project_name.step_definitions;

import com.github.javafaker.Faker;
import com.project_name.pages.CartPage;
import com.project_name.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageStepDefs {

    CartPage cartPage = new CartPage();

    Faker faker = new Faker();
    String expectedTotalAmount;

    @Then("Cart should display both {string} and {string}")
    public void cart_should_display_both_and(String itemName1, String itemName2) {
        BrowserUtils.waitForPageToLoad(3);
        cartPage.verificationIfTheItemInTheCart(itemName1);
        cartPage.verificationIfTheItemInTheCart(itemName2);
    }

    @When("Customer removes {string} from the cart")
    public void customerRemovesFromTheCart(String itemName) {
        BrowserUtils.waitForPageToLoad(3);
        cartPage.deleteItemFromTheCart(itemName);
    }

    @Then("Cart should display {string}")
    public void cartShouldDisplay(String itemName) {
        BrowserUtils.waitForPageToLoad(3);
        cartPage.verificationIfTheItemInTheCart(itemName);
        BrowserUtils.waitFor(1);
    }

    @When("Customer clicks on Place order")
    public void customerClicksOn() {
        cartPage.buttonClick(cartPage.placeOrderButton);
    }

    @And("Customer fills in all web form fields")
    public void customerFillsInAllWebFormFields() {
        expectedTotalAmount = cartPage.getTotalAmount();
        System.out.println(expectedTotalAmount);
        String name = faker.name().fullName();
        String country = faker.address().country();
        String city = faker.address().city();
        String card = faker.finance().creditCard();
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        int index = faker.number().numberBetween(0, 11);
        String month = months[index];
        String year = String.valueOf(faker.number().numberBetween(1950, 2000));
        BrowserUtils.waitForVisibility(cartPage.listOfInputPlaceOrder.get(0),3);
        cartPage.fillPlaceOrderForm(name,country, city, card, month, year);
    }

    @Then("Customer clicks on Purchase")
    public void customer_clicks_on_purchase() {
        cartPage.buttonClick(cartPage.purchaseButton);
    }

    @Then("Customer should see a purchase confirmation pop-up")
    public void customerShouldSeeAPurchaseConfirmationPopUp() {
        System.out.println("getPurchaseInformationPopUp() = " + cartPage.getPurchaseInformationPopUp());
    }

    @And("Purchase ID and Amount should be captured and logged")
    public void purchaseIDAndAmountShouldBeCapturedAndLogged() {

    }



}
