package selenium_project.tests;

import org.testng.annotations.Test;
import selenium_project.library.TestBase;
import selenium_project.pages.PopularPage;

public class PopularTest extends TestBase {
    @Test
    public void selectPopular(){
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
    }
    @Test
    public void addToCartFromPopularPage() throws InterruptedException {
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        String itemTitle="Blouse";
        popularPage.AddToCartFromPopularPage(itemTitle);
    }
    @Test
    public void addToCartFromMore() throws InterruptedException {
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        String itemTitle="Faded Short Sleeve T-shirts";
        popularPage.AddToCartFromMore(itemTitle);
    }
    @Test
    public void addToCartFromProductPage() throws InterruptedException {
        PopularPage popularPage = new PopularPage(driver);
        popularPage.SelectPopular();
        String itemTitle="Blouse";
        popularPage.AddToCartFromProductPage(itemTitle);
    }
}
