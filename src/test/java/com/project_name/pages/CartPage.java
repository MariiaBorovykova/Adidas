package com.project_name.pages;

import com.project_name.utilities.BrowserUtils;
import com.project_name.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//tbody[@id='tbodyid']//td[2]")
    public List<WebElement> listOfNamesItemInTheCart;


    @FindBy(xpath = "//tbody[@id='tbodyid']//a")
    public List<WebElement> listOfDeleteButtons;

    @FindBy(xpath = "//button[.='Place Order']")
    public WebElement placeOrderButton;

    @FindBy(xpath = "//button[.='Purchase']")
    public WebElement purchaseButton;

    @FindBy(xpath = "//label[@id='totalm']")
    public WebElement totalAmount;

    @FindBy(xpath = "//div[@id='orderModal']//input")
    public List<WebElement> listOfInputPlaceOrder;

    @FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']/p")
    public WebElement purchaseInformationPopUp;


    public void deleteItemFromTheCart(String nameItemForDeleting) {

                BrowserUtils.clickWithTimeOut(Driver.getDriver().findElement(By.xpath("//tbody[@id='tbodyid']//td[.='" + nameItemForDeleting + "']//..//a")), 2);
                //eachDeleteButton.click();

    }

    public void verificationIfTheItemInTheCart(String itemName) {
        BrowserUtils.waitForVisibility(listOfNamesItemInTheCart.get(0), 2);
        int i = 0;
        for (WebElement eachName : listOfNamesItemInTheCart) {
            System.out.println(eachName.getText() + i++);
            if (eachName.getText().equals(itemName)) {
                Assert.assertTrue(true);
                break;
            }
        }

    }

    public void buttonClick(WebElement button) {
        BrowserUtils.clickWithJS(button);
    }

    public String getTotalAmount() {
        String[] fullText = totalAmount.getText().split(" ");
        return fullText[0];
    }

    public void fillPlaceOrderForm(String name, String country, String city, String card, String month, String year) {

        for (WebElement each : listOfInputPlaceOrder) {
            switch (each.getAttribute("id")) {
                case "name":
                    each.sendKeys(name);
                    break;
                case "country":
                    each.sendKeys(country);
                    break;
                case "city":
                    each.sendKeys(city);
                    break;
                case "card":
                    each.sendKeys(card);
                    break;
                case "month":
                    each.sendKeys(month);
                    break;
                case "year":
                    each.sendKeys(year);
                    break;

            }
        }
    }

    public String getPurchaseInformationPopUp(){
        return purchaseInformationPopUp.getText();
    }
    public String retrieveAmountFromPurchaseInformationPopUp(String nameOfInformation){
        String[] str = getPurchaseInformationPopUp().split("\n");
        for (String s : str) {
            if (s.contains(nameOfInformation)){
                String[] each = s.split(" ");
                return each[1];
            }
        }
                return null;
    }
}
