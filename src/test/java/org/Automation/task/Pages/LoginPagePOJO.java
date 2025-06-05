package org.Automation.task.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class LoginPagePOJO {

    private WebDriver driver;
    public   static Properties config;

@FindBy(id = "user-name")
    WebElement username;

@FindBy(id = "password")
    WebElement password;

@FindBy(id = "login-button")
    WebElement submitbtn;


    public LoginPagePOJO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getUserName(String userName){
        username.sendKeys(userName);
    }

    public void getPassword(String pass){
        password.sendKeys(pass);
    }

    public void clickFunction(){
        submitbtn.click();
    }








}
