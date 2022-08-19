package selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import selenium_project.library.PageBase;

import java.awt.*;
import java.util.List;

public class PopularPage extends PageBase {
    private final String xpathPopular = "//a[contains(text(),'Popular')]";
    private final String xpathCheckPopularActive = "//a[contains(text(),'Popular')]//parent::li";
    private final String popularStatus="active";
    private final String xpathItemTitle="//img[@title='XXX']";
    private final String xpathAddToCart="//span[text()='Add to cart']";
    private final String xpathAddToCartFromPopular="(//a[@title='XXX']//following::div[3]/div[2]/a/span[text()='Add to cart'])[1]";
    private final String xpathMore="(//a[@title='XXX']//following::div[3]/div[2]/a/span[text()='More'])[1]";
    private final String addToCartSuccessMsg="Product successfully added to your shopping cart";
    public PopularPage(WebDriver driver) {
        super(driver);
    }

    public void SelectPopular() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,760)");
      //  click(By.xpath("//a[contains(text(),'Best Sellers')]"));
    if (driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class") == popularStatus) {
            Assert.assertTrue(true);
        } else {
            click(By.xpath(xpathPopular));
            //String ActStatus = driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class");
            Assert.assertEquals(driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class"),popularStatus);
        }
    }

    public void AddToCartFromPopularPage(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathAddToCartFromPopular.replace("XXX",itemTitle)));
        String ActMsg=getText(By.xpath("//div[@id='layer_cart']//h2[1]"));
        Assert.assertEquals(addToCartSuccessMsg,ActMsg,"Failed to Add To Cart");

    }

    public void AddToCartFromMore(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathMore.replace("XXX",itemTitle)));
        Thread.sleep(3000);
        click(By.xpath(xpathAddToCart));
        String ActMsg=getText(By.xpath("//div[@id='layer_cart']//h2[1]"));
        Assert.assertEquals(addToCartSuccessMsg,ActMsg,"Failed to Add To Cart");
    }

    public void AddToCartFromProductPage(String itemTitle) {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
    }
}
