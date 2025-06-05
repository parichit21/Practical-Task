package org.Automation.task.Test;

import org.Automation.task.Pages.LoginPagePOJO;
import org.Automation.task.Resources.Utils.TakeScreenShort;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    public static WebDriver driver;
    public static Properties config;
    LoginPagePOJO loginPagePOJO ;

    @BeforeMethod
    public void setup() throws IOException {
        driver = new ChromeDriver();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/org/Automation/task/Resources/properties/config.properties");
        config = new Properties();
        config.load(fis);
        driver.get(config.getProperty("Base_URL"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginPagePOJO = new LoginPagePOJO(driver);
    }

    @AfterMethod
    public void setupEnd(){
        driver.quit();
    }


    @Test
    public void verifyLoadSuccess(){
        String title = driver.getTitle();
        if(title != null && !title.isEmpty()) {
            Assert.assertTrue(driver.getTitle().contains("Swag Labs"));
        } else  {
            TakeScreenShort.takeScreenshot(driver, "Title Screen Short");
        }
    }

    @Test
    public void TestuserNameRequired(){
                loginPagePOJO.clickFunction();
        // Locate the error message
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
    String actualerror = errorMsg.getText();
        String expectedError = "Epic sadface: Username is required";
       Assert.assertEquals(actualerror, expectedError);
    }

    @Test
    public void TestPasswordRequired(){
       loginPagePOJO.getUserName("secret_sauce");
        loginPagePOJO.clickFunction();
        // Locate the error message
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
        String actualerror = errorMsg.getText();
        String expectedError = "Epic sadface: Password is required";
        Assert.assertEquals(actualerror, expectedError);
    }
////*[@id="login_button_container"]/div/form/div[3]

@Test
public void TestWithWrongPasswordRequired(){
    loginPagePOJO.getUserName("secret_sauce");
    loginPagePOJO.getPassword("xxxx");
    loginPagePOJO.clickFunction();
    // Locate the error message
    WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
    String actualerror = errorMsg.getText();
    String expectedError = "Epic sadface: Username and password do not match any user in this service";
    Assert.assertEquals(actualerror, expectedError);
}
}
