package com.project_name.pages;

import com.project_name.utilities.BrowserUtils;
import com.project_name.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage{

    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h3[@class='price-container']")
    public WebElement itemPrise;

    @FindBy(xpath = "//a[.='Add to cart']")
    private WebElement addToCartButton;



    public void addToCartButtonClick(){
        BrowserUtils.waitForClickablility(addToCartButton, 5);
        addToCartButton.click();
    }
}
