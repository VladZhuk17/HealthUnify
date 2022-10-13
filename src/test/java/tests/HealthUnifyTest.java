package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HealthUnifyTest {

    private WebDriver driver;
    private final static By WEIGHT_FIELD = By.xpath("//input[@name='wg']");
    private final static By HEIGHT_CMS_FIELD = By.xpath("//input[@name='ht']");
    private final static By CALCULATE_FIELD = By.xpath("//input[@value='Calculate']");
    private String actualText;

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
    }

    @Test
    public void verifyEmptyField() {
        driver.findElement(CALCULATE_FIELD).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text, "Enter the value for weight", "There is a mistake in the text ");
    }

    @Test
    public void verifyNormalTest() {
        driver.findElement(WEIGHT_FIELD).sendKeys("75");
        driver.findElement(HEIGHT_CMS_FIELD).sendKeys("175");
        driver.findElement(CALCULATE_FIELD).click();
        actualText = "Your category is Normal";
        Assert.assertEquals(actualText, "Your category is Normal", "Conclusion is incorrect");
    }


    @Test
    public void verifyStarvationTest() {
        driver.findElement(WEIGHT_FIELD).sendKeys("30");
        driver.findElement(HEIGHT_CMS_FIELD).sendKeys("180");
        driver.findElement(CALCULATE_FIELD).click();
        actualText = "Your category is Starvation";
        Assert.assertEquals(actualText, "Your category is Starvation", "Conclusion is incorrect");
    }

    @Test
    public void verifyObeseTest() {
        driver.findElement(WEIGHT_FIELD).sendKeys("130");
        driver.findElement(HEIGHT_CMS_FIELD).sendKeys("160");
        driver.findElement(CALCULATE_FIELD).click();
        actualText = "Your category is Obese";
        Assert.assertEquals(actualText, "Your category is Obese", "Conclusion is incorrect");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}