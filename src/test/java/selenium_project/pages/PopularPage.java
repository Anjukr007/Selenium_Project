package selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import selenium_project.library.PageBase;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class PopularPage extends PageBase {
    private final String xpathPopular = "//a[contains(text(),'Popular')]";
    private final String xpathBestSeller="//a[contains(text(),'Best Sellers')]";
    private final String xpathCheckPopularActive = "//a[contains(text(),'Popular')]//parent::li";
    private final String popularStatus="active";
    private final String xpathItemTitleProductPage="//div[@class='pb-center-column col-xs-12 col-sm-4']/h1";
    private final String xpathItemTitle="//img[@title='XXX']";
    private final String xpathAddToCart="//span[text()='Add to cart']";
    private final String xpathAddToCartFromPopular="(//a[@title='XXX']//following::div[3]/div[2]/a/span[text()='Add to cart'])[1]";
    private final String xpathMore="(//a[@title='XXX']//following::div[3]/div[2]/a/span[text()='More'])[1]";
    private final String xpathAddToCartItemName="//div[@id='layer_cart']//span[text()='XXX']";
    private final String addToCartSuccessMsg="Product successfully added to your shopping cart";
    private final String xpathAddToCartMsg="//div[@id='layer_cart']//h2[1]";
    private final String xpathAddToCartFromProduct="//p[@id='add_to_cart']//span";
    public PopularPage(WebDriver driver) {
        super(driver);
    }

    public void SelectPopular() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,760)");
        click(By.xpath(xpathBestSeller));
    if (isPopularVisible(xpathCheckPopularActive)) {
           String actPopularStatus =driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class");
           assertValues(actPopularStatus,popularStatus);
        } else {
            click(By.xpath(xpathPopular));
            String actPopularStatus =driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class");
            assertValues(actPopularStatus,popularStatus);

        }
    }

    public void AddToCartFromPopularPage(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathAddToCartFromPopular.replace("XXX",itemTitle)));
        waitForElement(Duration.ofSeconds(10),By.xpath(xpathAddToCartMsg));
        String ActMsg=getText(By.xpath(xpathAddToCartMsg));
        assertValues(ActMsg,addToCartSuccessMsg);
        String ActItemTitle=getText(By.xpath(xpathAddToCartItemName.replace("XXX",itemTitle)));
        assertValues(ActItemTitle,itemTitle);
    }

    public void AddToCartFromMore(String itemTitle) throws InterruptedException {
        mouseHover(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        click(By.xpath(xpathMore.replace("XXX",itemTitle)));
        waitForElement(Duration.ofSeconds(10),By.xpath(xpathAddToCart));
        click(By.xpath(xpathAddToCart));
        waitForElement(Duration.ofSeconds(10),By.xpath(xpathAddToCartMsg));
        String ActMsg=getText(By.xpath(xpathAddToCartMsg));
        assertValues(ActMsg,addToCartSuccessMsg);
        String ActItemTitle=getText(By.xpath(xpathAddToCartItemName.replace("XXX",itemTitle)));
        assertValues(ActItemTitle,itemTitle);
    }

    public void AddToCartFromProductPage(String itemTitle) throws InterruptedException {
        click(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
        driver.switchTo().frame(1);
        click(By.xpath(xpathAddToCartFromProduct));
        String ActItemTitle=getText(By.xpath(xpathAddToCartItemName.replace("XXX",itemTitle)));
        assertValues(ActItemTitle,itemTitle);

    }

    public boolean isPopularVisible(String xpathCheckPopularActive ){
       return (driver.findElement(By.xpath(xpathCheckPopularActive)).getAttribute("class") == popularStatus);
          }

    public void clickOnItem(String itemTitle) throws InterruptedException {
        click(By.xpath(xpathItemTitle.replace("XXX",itemTitle)));
    }
    public void assertValues(String actValue, String expValue){
        Assert.assertEquals(actValue,expValue);
    }

    public void VerifyProductTitle(String itemTitle) {
        driver.switchTo().frame(1);
        String actTitle=getText(By.xpath(xpathItemTitleProductPage));
        assertValues(actTitle,itemTitle);
    }
}
