import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_model.HomePage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionsAboutMainTest {

    private WebDriver driver;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkTextExpansion() {

        HomePage homePage = new HomePage(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        List<WebElement> questionsAboutMainFields = homePage.getQuestionsAboutMainFields();

        //проверяем что аттрибут aria-disabled стал true и aria-expanded стал true
        //и проверим его раскрывающийся элемент
        questionsAboutMainFields.forEach(questionsAboutMainField -> {
            executor.executeScript("arguments[0].click();", questionsAboutMainField);
            WebElement expandedElement = homePage.getQuestionAboutMainAriaControlField(questionsAboutMainField);
            assertEquals("Элемент с id = " + questionsAboutMainField.getAttribute("id") + " не поставил aria-disabled в false после нажатия", "true", questionsAboutMainField.getAttribute("aria-disabled"));
            assertEquals("Элемент с id = " + questionsAboutMainField.getAttribute("id") + " не поставил aria-disabled в false после нажатия", "true", questionsAboutMainField.getAttribute("aria-expanded"));
            assertTrue("Элемент с id = " + expandedElement.getAttribute("id") + " не виден, тк присутвтует hidden", expandedElement.isDisplayed());
        });

    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
