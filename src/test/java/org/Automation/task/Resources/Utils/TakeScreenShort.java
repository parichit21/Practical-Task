package org.Automation.task.Resources.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class TakeScreenShort {
    public static void takeScreenshot(WebDriver driver, String fileName) {
        String path = System.getProperty("user.dir") + "/screenshots";
        new File(path).mkdirs();

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcFile, new File(path + "/" + fileName + ".png"));
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

}
