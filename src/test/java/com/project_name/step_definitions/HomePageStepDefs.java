package com.project_name.step_definitions;

import com.project_name.pages.BasePage;
import com.project_name.utilities.BrowserUtils;
import com.project_name.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageStepDefs extends BasePage {


    @Given("Customer is on the home page")
    public void customer_is_on_the_home_page() {
        verifyTitleName("STORE");
    }

    @When("Customer navigates to {string} category")
    public void customer_navigates_to_category(String category) {
        BrowserUtils.waitFor(1);
        navigateToCategory(category);
        BrowserUtils.waitFor(1);
    }

    @Then("Customer should see available phones")
    public void customer_should_see_available_phones() {
        BrowserUtils.waitFor(1);
        List<String> items = new ArrayList<>(Arrays.asList("Samsung galaxy s6", "Nokia lumia 1520", "Nexus 6", "Samsung galaxy s7", "Iphone 6 32gb", "Sony xperia z5", "HTC One M9"));
        verifyListOfItems(items);
    }

    @Then("Customer should see available laptops")
    public void customer_should_see_available_laptops() {
        BrowserUtils.waitFor(1);
        System.out.println("getListOfItems() = " + getListOfItems());
    }

    @Then("Customer should see available monitors")
    public void customer_should_see_available_monitors() {
        BrowserUtils.waitFor(1);
        System.out.println("getListOfItems() = " + getListOfItems());
    }

    @And("Customer selects {string}")
    public void customerSelects(String itemName) {
        BrowserUtils.clickWithJS(getWebElementFromList(listOfItems, itemName));
    }
}
