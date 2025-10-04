package org.automationexercise.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName, String customPath) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String baseDir = (customPath != null && !customPath.isEmpty()) ? customPath : "screenshots/";
        String screenshotPath = baseDir + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get(baseDir));
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}