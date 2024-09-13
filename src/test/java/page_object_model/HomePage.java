package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final JavascriptExecutor executor;
    //элементы списка которые нужно проверить на раскрытие текста
    private final By questionsAboutMainFields = By.xpath(".//*/div[@data-accordion-component = 'AccordionItemButton']");

    private final By topOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g' and text() = 'Заказать']");

    private final By bottomOrderButton = By.xpath("/html/body/div/div/div/div[4]/div[2]/div[5]/button");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }

    public List<WebElement> getQuestionsAboutMainFields() {
        return driver.findElements(questionsAboutMainFields);
    }

    //получить раскрываемый элемент на который кликаем
    public WebElement getQuestionAboutMainAriaControlField(WebElement questionsAboutMainField) {
        return driver.findElement(By.id(questionsAboutMainField.getAttribute("aria-controls")));
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(bottomOrderButton)).get(0).click();
    }
}
