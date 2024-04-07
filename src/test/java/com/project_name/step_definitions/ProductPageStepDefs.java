package com.project_name.step_definitions;

import com.project_name.pages.ProductPage;
import com.project_name.utilities.BrowserUtils;
import com.project_name.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;

public class ProductPageStepDefs {

    ProductPage productPage = new ProductPage();

    @When("Customer adds it to the cart")
    public void customer_adds_it_to_the_cart() {
        productPage.addToCartButtonClick();
    }

    @Then("Customer should see a pop-up confirmation")
    public void customer_should_see_a_pop_up_confirmation() {
        BrowserUtils.waitFor(1);
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
    }


    @And("Customer moves to the {string} page")
    public void customer_moves_to_thePage(String moduleName) {
        BrowserUtils.waitFor(1);
        productPage.navigationToModuleOfMenuBar(moduleName).click();
    }


}
