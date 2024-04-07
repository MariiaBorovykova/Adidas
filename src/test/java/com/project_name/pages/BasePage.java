package com.project_name.pages;
import com.project_name.utilities.BrowserUtils;
import com.project_name.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class   BasePage {


    @FindBy(xpath = "//a[@id='itemc']")
    public List<WebElement> categories;
    
    @FindBy(xpath = "//h4[@class='card-title']/a")
    public List<WebElement> listOfItems;

    @FindBy(xpath = "//div[@id='navbarExample']//a")
    private List<WebElement> menuBar;


    @FindBy(css = "div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    public static void verifyTitleName(String expectedTitle){
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void navigateToCategory(String categoryName){
        for (WebElement eachCategory : categories) {

            if (eachCategory.getText().equals(categoryName)){
                System.out.println("Click on " + eachCategory.getText());
                eachCategory.click();
            }
        }
    }
    public List<String> getListOfItems(){
        List<String> items = new ArrayList<>();
        for (WebElement eachItem : listOfItems) {
            items.add(eachItem.getText());
        }
        return items;
    }
    public void verifyListOfItems(List<String> expectedListOfItems){

            Assert.assertEquals(expectedListOfItems, getListOfItems());

    }

    public WebElement getWebElementFromList(List<WebElement> list, String nameOfItem){
        for (WebElement each : list) {
            System.out.println(each.getText());
            if (each.getText().equals(nameOfItem)){
                return each;
            }
        }
        return null;
    }
    public void navigateToItem(String itemName){
        for (WebElement eachItem : listOfItems) {
            if (eachItem.getText().equals(itemName)){
                eachItem.click();
            }
        }
    }

    /**
    Navigation through the Menue on the top of the page
     */

    public WebElement navigationToModuleOfMenuBar(String moduleName){
        for (WebElement eachModule : menuBar) {
            System.out.println(eachModule.getText());
            if (eachModule.getText().contains(moduleName)){
                return eachModule;
            }
        }
        return null;
    }
    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear();
//        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }


    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     */
    public void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param tab
     * @param module
     */
    public void navigateToModule(String tab, String module) {
        String tabLocator = "//span[normalize-space()='" + tab + "' and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[normalize-space()='" + module + "' and contains(@class, 'title title-level-2')]";
        try {
            BrowserUtils.waitForClickablility(By.xpath(tabLocator), 5);
            WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
            new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(tabLocator), 5);
        }
        try {
            BrowserUtils.waitForPresenceOfElement(By.xpath(moduleLocator), 5);
            BrowserUtils.waitForVisibility(By.xpath(moduleLocator), 5);
            BrowserUtils.scrollToElement(Driver.getDriver().findElement(By.xpath(moduleLocator)));
            Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
        } catch (Exception e) {
//            BrowserUtils.waitForStaleElement(Driver.get().findElement(By.xpath(moduleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.getDriver().findElement(By.xpath(moduleLocator)),  5);
        }
    }

}
