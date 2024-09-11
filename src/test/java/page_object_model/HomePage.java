package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {


    private final WebDriver driver;

    //элементы списка которые нужно проверить на раскрытие текста
    private final By questionsAboutMainFields = By.xpath(".//*/div[@data-accordion-component = 'AccordionItemButton']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getQuestionsAboutMainFields(){
        return driver.findElements(questionsAboutMainFields);
    }

    //получить раскрываемый элемент по элементу на который жмем
    public WebElement getQuestionAboutMainAriaControlField(WebElement questionsAboutMainField){
        return driver.findElement(By.id(questionsAboutMainField.getAttribute("aria-controls")));
    }

}
