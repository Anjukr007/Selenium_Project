package selenium_project.tests;

import org.testng.annotations.Test;
import selenium_project.library.TestBase;
import selenium_project.pages.PopularPage;

public class PopularTest extends TestBase {
    @Test
    public void selectPopular() throws InterruptedException {

        String itemTitle="Blouse";
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        popularPage.clickOnItem(itemTitle);
        popularPage.VerifyProductTitle(itemTitle);
    }
    @Test
    public void addToCartFromPopularPage() throws InterruptedException {
        String itemTitle="Blouse";
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        popularPage.AddToCartFromPopularPage(itemTitle);
    }
    @Test
    public void addToCartFromMore() throws InterruptedException {

        String itemTitle="Faded Short Sleeve T-shirts";
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        popularPage.AddToCartFromMore(itemTitle);
    }
    @Test
    public void addToCartFromProductPage() throws InterruptedException {

        String itemTitle="Blouse";
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        popularPage.AddToCartFromProductPage(itemTitle);
    }
}
