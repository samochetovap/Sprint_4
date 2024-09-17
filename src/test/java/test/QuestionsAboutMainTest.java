package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object_model.HomePage;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Проверка вопросов о важном
 */
public class QuestionsAboutMainTest {

    private WebDriver driver;

    @Before
    public void initDriver() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkTextExpansion() {

        HomePage homePage = new HomePage(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        List<WebElement> questionsAboutMainFields = homePage.getQuestionsAboutMainFields();

        questionsAboutMainFields.forEach(questionsAboutMainField -> {
            executor.executeScript("arguments[0].scrollIntoView(true);", questionsAboutMainField);
            Actions actions = new Actions(driver);
            actions.moveToElement(questionsAboutMainField);
            actions.perform();
            executor.executeScript("arguments[0].click();", questionsAboutMainField);
            WebElement expandedElement = homePage.getQuestionAboutMainAriaControlField(questionsAboutMainField);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOf(expandedElement));



            //по замечанию ревьюера смотрим и сравниваем вопрос - ответ текст элементов во избежание орфографических ошибок и тд

            boolean isStringExists = false;
            //чекаем тексты вопросов и их ответы
            String questionStr = questionsAboutMainField.getText();
            if(questionStr.equals("Сколько это стоит? И как оплатить?")){
                assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Хочу сразу несколько самокатов! Так можно?")){
                assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Как рассчитывается время аренды?")){
                assertEquals("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Можно ли заказать самокат прямо на сегодня?")){
                assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Можно ли продлить заказ или вернуть самокат раньше?")){
                assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Вы привозите зарядку вместе с самокатом?")){
                assertEquals("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }else if(questionStr.equals("Можно ли отменить заказ?")){
                assertEquals("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
                //здесь опечатка на сайте по этому текст упадет с ошибкой
            }else if(questionStr.equals("Я живу за МКАДом, привезёте?")){
                assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.", expandedElement.findElement(By.xpath("./p")).getText());
                isStringExists = true;
            }
            assertTrue("Строка с вопросом не найдена в ожидаемом списке: " + questionStr, isStringExists);

            //проверка на раскрытие элементов
            assertEquals("Элемент с id = " + questionsAboutMainField.getAttribute("id") + " не поставил aria-disabled в false после нажатия", "true", questionsAboutMainField.getAttribute("aria-disabled"));
            assertEquals("Элемент с id = " + questionsAboutMainField.getAttribute("id") + " не поставил aria-expanded в false после нажатия", "true", questionsAboutMainField.getAttribute("aria-expanded"));
            assertTrue("Элемент с id = " + expandedElement.getAttribute("id") + " не виден, тк присутствует hidden", expandedElement.isDisplayed());
        });

    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
