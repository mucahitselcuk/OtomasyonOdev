package Page;

import Methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductPage extends Methods {


    Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct() {
        waitBySeconds(5);
        sendKeys(By.id("search"), "Ceket");
        waitBySeconds(5);
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        waitBySeconds(5);
    }

    public void clickMore() {
        scrollWithAction(By.cssSelector(".button.-secondary.-sm.relative"));
        waitBySeconds(5);
        clickElement(By.cssSelector(".button.-secondary.-sm.relative"));
        waitBySeconds(5);
    }

    public void productList() {
        clickElement(By.cssSelector(".tool__button.-sorting"));
        waitBySeconds(2);
        clickElement(By.cssSelector("ul.sortingList>li:nth-of-type(4)"));
        waitBySeconds(2);
        clickElement(By.cssSelector(".product__header"));
        waitBySeconds(2);
        clickElement(By.id("size_46/6N"));
        waitBySeconds(2);
        clickElement(By.cssSelector(".product__button.-addToCart.btn.-black"));
        waitBySeconds(2);

    }


}