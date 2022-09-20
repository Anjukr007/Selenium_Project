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
    private final String xpathAddToCartItemName="//div[@id='layer_cart']//span[text()='XXX']";
    private final String addToCartSuccessMsg="Product successfully added to your shopping cart";
    public PopularPage(WebDriver driver) {
        super(driver);
    }

    public void SelectPopular() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,760)");
        click(By.xpath("//a[contains(text(),'Best Sellers')]"));
    if (isPopularVisible(xpathCheckPopularActive)) {
            Assert.assertTrue(true);
        } else {
            click(By.xpath(xpathPopular));
            String actPopularStatus =driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class");
            Assert.assertEquals(actPopularStatus,popularStatus);
        }
    }

    public void AddToCartFromPopularPage(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathAddToCartFromPopular.replace("XXX",itemTitle)));
        Thread.sleep(3000);
        String ActMsg=getText(By.xpath("//div[@id='layer_cart']//h2[1]"));
        Assert.assertEquals(addToCartSuccessMsg,ActMsg,"Failed to Add To Cart");
        String ActItemTitle=getText(By.xpath(xpathAddToCartItemName.replace("XXX",itemTitle)));
        Assert.assertEquals(ActItemTitle,itemTitle,"Not Selected item");

    }

    public void AddToCartFromMore(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathMore.replace("XXX",itemTitle)));
        Thread.sleep(3000);
        click(By.xpath(xpathAddToCart));
        Thread.sleep(3000);
        String ActMsg=getText(By.xpath("//div[@id='layer_cart']//h2[1]"));
        Assert.assertEquals(addToCartSuccessMsg,ActMsg,"Failed to Add To Cart");
        String ActItemTitle=getText(By.xpath(xpathAddToCartItemName.replace("XXX",itemTitle)));
        Assert.assertEquals(ActItemTitle,itemTitle,"Not Selected item");
    }

    public void AddToCartFromProductPage(String itemTitle) throws InterruptedException {
       // mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
       // Thread.sleep(3000);
        driver.switchTo().frame(1);
        click(By.xpath("//p[@id='add_to_cart']//span"));

    }

    public boolean isPopularVisible(String xpathCheckPopularActive ){
       return (driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class") == popularStatus);
          }

}
