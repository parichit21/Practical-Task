package org.Automation.task.Pages;

import org.Automation.task.Resources.Utils.TakeScreenShort;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class LogonPage {
    public   static  WebDriver driver;
    public   static Properties config;

    @BeforeMethod
    public void login() throws IOException {
        driver = new ChromeDriver();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/org/Automation/task/Resources/properties/config.properties");
        config = new Properties();
        config.load(fis);
        driver.get(config.getProperty("Base_URL"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("user-name")).sendKeys(config.getProperty("Username"));
        driver.findElement(By.id("password")).sendKeys(config.getProperty("Password"));
        driver.findElement(By.id("login-button")).click();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void AddTocart(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        TakeScreenShort.takeScreenshot(driver, "Cart_entry");
    }

    @AfterMethod
    public void checkout(){
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(config.getProperty("first-name"));
        driver.findElement(By.id("last-name")).sendKeys(config.getProperty("last-name"));
        driver.findElement(By.id("postal-code")).sendKeys(config.getProperty("postal-code"));
        driver.findElement(By.id("continue")).click();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
        driver.quit();
    }


}
