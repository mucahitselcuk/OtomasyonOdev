package Methods;


import driver.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Methods extends BaseTest {

    private static final Logger logger = LogManager.getLogger(Methods.class);
    protected WebDriver driver;
    WebDriverWait wait;

    public Methods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    //Js driver get metodu
    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    //Belirtilen saniye kadar bekle
    public static void waitBySeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }
    }

    //Element var olana kadar bekle
    public void waitUntilPresence(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //Element tıklanabilir olana kadar bekle
    public void waitUntilElementClickable(By by) {
        logger.info("elementin tıklanabilir olması bekleniyor");
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //Elementi bul
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    //Elementin üzerinde bekle
    public void hoverElement(By by) {
        Actions action = new Actions(driver);
        logger.info("Üstüne gelinen element: " + getText(by) + by);
        action.moveToElement(findElement(by)).build().perform();
    }

    //Elementin konumuna göre scroll
    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d,%d);", x, y);
        getJSExecutor().executeScript(jsStmt, true);
        logger.info("Sayfa elementin bulunduğu konuma scroll edildi");
    }

    //Elementin indexine göre scroll
    public void scrollByIndex(By by, int index) {

        List<WebElement> productElements = driver.findElements(by);
        WebElement productElement = productElements.get(index - 1);
        scrollTo(productElement.getLocation().getX(), productElement.getLocation().getY());
        logger.info("Sayfa " + index + ".elemente scroll edildi");
    }

    //Scroll
    public void scrollWithAction(By element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(element)).build().perform();
    }

    public void clickElement(By by) {
        waitUntilPresence(by);
        WebElement element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        try {
            waitUntilElementClickable(by);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            Assert.fail("Element tıklanabilir değil!");
        }
        element.click();
    }

    public void sendKeys(By by, String text) {
        waitUntilPresence(by);
        WebElement element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        waitUntilElementClickable(by);
        element.sendKeys(text);
        logger.info("İlgili elemente " + text + " değeri gönderildi...");
    }

    public boolean isElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("Aranan element görünür");
            return true;
        } catch (Exception e) {
            logger.info("False" + e.getMessage());
            return false;
        }
    }

    public String getAttribute(By by, String attributeName) {
        waitUntilPresence(by);
        return findElement(by).getAttribute(attributeName);
    }

    public String getText(By by) {
        waitUntilPresence(by);
        return findElement(by).getText();
    }

    public String getValue(By by) {
        waitUntilPresence(by);
        return getJSExecutor().executeScript("return arguments[0].value;", findElement(by)).toString();
    }
}
