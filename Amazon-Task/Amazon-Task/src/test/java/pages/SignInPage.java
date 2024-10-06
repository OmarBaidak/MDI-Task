package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    WebDriver driver;

    By emailField = By.id("ap_email");
    By passwordField = By.id("ap_password");
    By continueButton = By.id("continue");
    By signInButton = By.id("signInSubmit");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail (String text) {
        driver.findElement(emailField).sendKeys(text);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword (String text) {
        driver.findElement(passwordField).sendKeys(text);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
}
