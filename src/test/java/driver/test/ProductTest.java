package driver.test;

import Page.ProductPage;
import driver.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class ProductTest extends BaseTest {
    ProductPage productPage;

    @Before
    public void before() {
        productPage = new ProductPage(getDriver());
    }

    @Test
    public void productTest() {
        productPage.searchProduct();
        productPage.clickMore();
        productPage.productList();
        productPage.addProduct();
        productPage.basket();
    }
}