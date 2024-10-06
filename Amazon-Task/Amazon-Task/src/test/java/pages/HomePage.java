package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;
    By chooseLanguage = By.xpath("//*[@id='icp-nav-flyout']");
    By englishButton = By.xpath("(//*[@class='a-radio a-radio-fancy'])[2]");
    By saveChangesButton = By.xpath("//span[@id='icp-save-button']");
    By loginBtn = By.xpath("//*[@id='nav-link-accountList']");
    By allTab = By.id("nav-hamburger-menu");
    By seeAll = By.xpath("//a[@class='hmenu-item hmenu-compressed-btn']");
    By VideoGame = By.xpath("//a[@data-ref-tag='nav_em_1_1_1_21']");
    By AllVideo = By.xpath("(//a[text()='All Video Games'])[2]");
    By checkBox = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/ul/li/span/a/div[1]/label/i");
    By newButton = By.xpath("//*[@id='p_n_condition-type/28071525031']/span/a/span");
    By dropButton = By.xpath("//span[@class='a-button-text a-declarative']");
    By highToLow = By.id("s-result-sort-select_2");
    By nextPage = By.partialLinkText("Next");
    By addBtn = By.id("add-to-cart-button");
    By proccedBtn = By.id("sc-buy-box-ptc-button");
    By fullNameField = By.id("address-ui-widgets-enterAddressFullName");
    By NumberField = By.id("address-ui-widgets-enterAddressPhoneNumber");
    By streetField = By.id("address-ui-widgets-enterAddressLine1");
    By buildingField = By.id("address-ui-widgets-enter-building-name-or-number");
    By cityField = By.id("address-ui-widgets-enterAddressCity");
    By DistrictField = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    By submitButton = By.xpath("(//*[@type='submit'])[3]");
    By priceElementScreen = By.xpath("//*[@id='apex_offerDisplay_desktop']/div/div/div/div/span/span[1]");
    By pricePaymentScreen = By.xpath("//table[1]/tbody/tr[6]/td[2]");
    By valuButton = By.xpath("(//*[@name='ppw-instrumentRowSelection'])[1]");
    Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToArabic() throws InterruptedException {
        driver.findElement(chooseLanguage).click();
        driver.findElement(englishButton).click();
        driver.findElement(saveChangesButton).click();
        Thread.sleep(1000);
    }

    public void goToLogin() {
        driver.findElement(loginBtn).click();
    }

    public void filter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(allTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAll)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(VideoGame)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AllVideo)).click();
        driver.findElement(checkBox).click();
        driver.findElement(newButton).click();
        driver.findElement(dropButton).click();
        driver.findElement(highToLow).click();


    }

    public void getElements() throws InterruptedException {
        Thread.sleep(3000);
        int numbersLessThanValue =0;
        List<WebElement> elements1 = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        List<WebElement> elements2 = driver.findElements(By.xpath("//*[@data-cy='secondary-offer-recipe']/div/span[2]"));

        List<WebElement> combinedElements = new ArrayList<>();
        combinedElements.addAll(elements1);
        combinedElements.addAll(elements2);

        System.out.println(combinedElements.size());

        for (int i = 0; i < combinedElements.size(); i++) {
            WebElement element = combinedElements.get(i);

            // Extract the text from the element and convert it to an integer
            String text = element.getText().replaceAll("[A-Za-z ,]","");
            double value = Double.parseDouble(text);

            // Compare the value with 15 and perform actions accordingly
            if (value > 15000) {
                System.out.println("Greater than 15k");
            } else {
                numbersLessThanValue ++;
                combinedElements.get(i).click();
                break;
            }
        }
        if (numbersLessThanValue == 0) {
            driver.findElement(nextPage).click();
            getElements();
        }
    }

    public String priceInElementScreen(){
        return driver.findElement(priceElementScreen).getText().replaceAll(" ","");
    }

    public void addcart() {

        driver.findElement(addBtn).click();
        driver.findElement(proccedBtn).click();

    }
    public void enterFullName(String text) {
        driver.findElement(fullNameField).sendKeys(text);
    }
    public void enterNumber(String text) {
        driver.findElement(NumberField).sendKeys(text);
    }
    public void enterStreet(String text) {
        driver.findElement(streetField).sendKeys(text);
    }
    public void enterBuildingNumber(String text) {
        driver.findElement(buildingField).sendKeys(text);
    }
    public void enterCity(String text) throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(cityField).sendKeys(text);
    }
    public void enterDistrict(String text) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(DistrictField).sendKeys(text);
        Thread.sleep(2000);
        actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }


    public void clicksubmit() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
    }

    public String priceInPaymentScreen() {
        return driver.findElement(pricePaymentScreen).getText().replaceAll(" ","");
    }

    public void selectValu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(valuButton)).click();
    }
}
