package HeatMap;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Heatmap {
        ChromeOptions option;
        WebDriver driver;


        @BeforeMethod
        public void openBrowser() throws InterruptedException {
            option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");
            option.setHeadless(false);//user interface browser mode on/of(false/true)
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(option);
            driver.manage().window().maximize();
            driver.get("https://app.vwo.com/#/test/ab/13/heatmaps/" +
                    "1?token=eyJhY2NvdW50X2lkIjo2NjY0MDAsImV4cGVyaW1" +
                    "lbnRfaWQiOjEzLCJjcmVhdGVkX29uIjoxNjcxMjA1MDUwLCJ0eXB" +
                    "lIjoiY2FtcGFpZ24iLCJ2ZXJzaW9uIjoxLCJoYXNoIjoiY2IwNz" +
                    "BiYTc5MDM1MDI2N2QxNTM5MTBhZDE1MGU1YTUiLCJzY29wZSI6IiIsI" +
                    "mZybiI6ZmFsc2V9&isHttpsOnly=1");
            driver.manage().window().maximize();}
        @Test(priority =1)
        public void HeatmapClick() throws InterruptedException {
         WebElement element=new WebDriverWait(driver, Duration.ofSeconds(5))//Explicit Wait
           .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='view--campaign ng-scope']//li[2]")));
            Actions action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
            Set<String> handles = driver.getWindowHandles();
            System.out.println("Number of windows opened: "+handles.size());

            Iterator<String> it = handles.iterator();//Iterator
            String parentWindowHanlde = it.next();//parent
            String childWindowHanlde = it.next();//child
            driver.switchTo().window(childWindowHanlde);//switch child1
            String ActualTitle=driver.getTitle();
            System.out.println("Current page title:"+ActualTitle);
            System.out.println("Current page URl:"+driver.getCurrentUrl());
            String ExpectedTitle="Job Ready Automation Tester Blueprint with JAVA By Pramod Dutta";
            Assert.assertEquals(ActualTitle,ExpectedTitle);

            WebElement frm1=driver.findElement(By.id("heatmap-iframe"));//iframe
            driver.switchTo().frame(frm1);//switch to iframe

            WebElement CLICKMAP=new WebDriverWait(driver, Duration.ofSeconds(5))//Explicit Wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='option-tab vwo_player-option']/span")));
            action.moveToElement(CLICKMAP).doubleClick().build().perform();
            System.out.println(" CLICKMAP Text:"+CLICKMAP.getText());

            WebElement Heatmap=new WebDriverWait(driver, Duration.ofSeconds(5))//Explicit Wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='option-tab vwo_player-option']/span")));
            System.out.println(" HEATMAP Text:"+Heatmap.getText());
            action.moveToElement(Heatmap).doubleClick().build().perform();
            Thread.sleep(20000);
            driver.close();}

     @AfterTest
       public void tearDown() {

       driver.quit();
    }}



