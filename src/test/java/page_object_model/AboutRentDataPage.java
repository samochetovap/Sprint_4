package page_object_model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AboutRentDataPage {

    private final WebDriver driver;

    private final By dateField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[1]/div/div/input");

    private final By datePicker = By.xpath(".//div[@class = 'react-datepicker__month-container']/div[2]/div/div[1]");

    private final By rentTimeField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[2]/div/div[1]");

    private final By rentTimeData = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[2]/div[@class = 'Dropdown-menu']/div");

    private final By colorBlackCheckBox = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[3]/label[@for = 'black']");

    private final By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[2]");

    private final By commentField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[4]/input");


    public AboutRentDataPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadAboutRentData() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (!driver.findElement(orderButton).getText().isEmpty()));
    }

    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    public void setupDateField() {
        driver.findElement(dateField).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(datePicker)).get(0).click();
    }

    public void setupRentTimeField() {
        driver.findElement(rentTimeField).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(rentTimeData)).get(0).click();
    }

    public void clickColorBlackCheckBox(){
        driver.findElement(colorBlackCheckBox).click();
    }

    public void setCommentField(String value){
        driver.findElement(commentField).sendKeys(value);
    }

}
