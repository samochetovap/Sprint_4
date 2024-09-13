package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_model.AboutRentDataPage;
import page_object_model.ConfirmOrderPage;
import page_object_model.HomePage;
import page_object_model.UserInOrderDataPage;

/**
 * Сценарий заказа
 */
@RunWith(Parameterized.class)
public class OrderScenarioTest {

    private WebDriver driver;

    private final String userName;
//
    private final String UserSurName;

    private final String address;

    private final String phone;

    private final String comment;

    private final boolean isStartFromTopButton;

    public OrderScenarioTest(String userName, String UserSurName, String address, String phone, String comment, boolean isStartFromTopButton){
        this.userName = userName;
        this.UserSurName = UserSurName;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.isStartFromTopButton = isStartFromTopButton;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForTest() {
        return new Object[][] {
                { "Алексей", "Алексеев", "Москва, какая то улица, д 111", "88005553535", "какой то комментарий", true},
                { "Иван", "Иванов", "адрес где живет Иван", "88005553535ssss", "какой то комментарий", false},
                { "Иван1", "Иванов", "адрес где живет Иван", "88005553535", "какой то комментарий", false},
                { "Иван", "Иванов", "chto to ne validiruyshee", "88005553535", "какой то комментарий", true},
        };
    }

    @Before
    public void initDriver() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderScenario() {

        HomePage homePage = new HomePage(driver);
        UserInOrderDataPage userInOrderDataPage = new UserInOrderDataPage(driver);
        AboutRentDataPage aboutRentDataPage = new AboutRentDataPage(driver);
        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);

        if(isStartFromTopButton){
            homePage.clickTopOrderButton();
        }else{
            homePage.clickBottomOrderButton();
        }


        userInOrderDataPage.waitForLoadUserInOrderData();

        userInOrderDataPage.setUserNameFieldValue(userName);
        userInOrderDataPage.setUserSurNameField(UserSurName);
        userInOrderDataPage.setUserAddressField(address);
        userInOrderDataPage.selectRandomUserMetroIntoField();
        userInOrderDataPage.setUserPhoneField(phone);

        userInOrderDataPage.clickNextButton();

        aboutRentDataPage.waitForLoadAboutRentData();
        aboutRentDataPage.setupDateField();
        aboutRentDataPage.setupRentTimeField();
        aboutRentDataPage.clickColorBlackCheckBox();
        aboutRentDataPage.setCommentField(comment);

        aboutRentDataPage.clickOrderButton();

        confirmOrderPage.clickYesButton();

        //чтобы словить баг хрома можно запустить этот метод
//        confirmOrderPage.isOderDone();

    }

    @After
    public void quitBrowser() {
        driver.quit();
    }

}
