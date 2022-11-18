package Page;

import Methods.Methods;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends Methods {


    Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct() {
        waitBySeconds(2);
        sendKeys(By.id("search"), "Ceket");
        waitBySeconds(2);
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        waitBySeconds(2);
    }

    public void clickMore() {
        scrollWithAction(By.cssSelector(".button.-secondary.-sm.relative"));
        waitBySeconds(2);
        clickElement(By.cssSelector(".button.-secondary.-sm.relative"));
        waitBySeconds(2);
    }

    public void productList() {
        clickElement(By.cssSelector(".tool__button.-sorting"));
        waitBySeconds(2);
        clickElement(By.cssSelector("ul.sortingList>li:nth-of-type(4)"));
        waitBySeconds(2);
        clickElement(By.cssSelector(".product__header"));
        waitBySeconds(2);
    }

    public void addProduct() {

        //waitUntilElementClickable(By.cssSelector("div[class='product__content -sizes']>:nth-child(1)"));

        clickElement(By.cssSelector("div[class='product__content -sizes']>:nth-child(3)"));
        clickElement(By.cssSelector(".product__button.-addToCart.btn.-black"));
        waitBySeconds(2);
        clickElement(By.cssSelector(".button.-primary.header__basket--checkout.header__basketModal.-checkout"));
        waitBySeconds(3);
    }

    public void basket() {
        //waitUntilPresence(By.cssSelector(".cartItem__attrValue"));
        WebElement firstResult= findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[1]/div[1]/span[2]"));
        WebElement secondResult= findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[3]/span[1]"));
        String first = firstResult.getText();
        String second= secondResult.getText();
        System.out.println("Ürün Bedeni : "+ first);
        System.out.println("Ürün Fiyatı : "+ second);
        waitBySeconds(2);

    }


}


