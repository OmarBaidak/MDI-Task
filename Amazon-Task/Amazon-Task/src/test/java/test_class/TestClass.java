package test_class;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;

public class TestClass {
    WebDriver driver;
    HomePage homePageObj;
    SignInPage signInPageObj;
    String expectedPrice;
    String actualPrice;

    @BeforeTest
    public void Setup () throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.eg/");
        homePageObj = new HomePage(driver);
        signInPageObj = new SignInPage(driver);
        homePageObj.switchToArabic();
    }

    @Test
    public void e2eTest() throws InterruptedException {
        homePageObj.goToLogin();
        signInPageObj.enterEmail("01030293209");
        signInPageObj.clickContinue();
        signInPageObj.enterPassword("Omar@1234");
        signInPageObj.clickSignIn();
        homePageObj.filter();
        homePageObj.getElements();
        expectedPrice = homePageObj.priceInElementScreen();
        homePageObj.addcart();
        homePageObj.enterFullName("Omar Saad Baidak ");
        homePageObj.enterNumber("1144477035");
        homePageObj.enterStreet("Ibn Zolak Helioplis ");
        homePageObj.enterBuildingNumber("5");
        homePageObj.enterCity("Cairo ");
        homePageObj.enterDistrict("Heliopolis-Manshyet El Bakry");
        homePageObj.clicksubmit();
        homePageObj.selectValu();
        actualPrice = homePageObj.priceInPaymentScreen();
        Assert.assertEquals(expectedPrice,actualPrice);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
