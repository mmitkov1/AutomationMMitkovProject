package MultipleTests;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationAndUrlValidation {

    protected boolean isUrlLoaded(WebDriver webDriver, String PAGE_URL){
        WebDriverWait explicitWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        try{
            explicitWait.until(ExpectedConditions.urlToBe(PAGE_URL));
        }catch(TimeoutException ex) {
            return false;
        }
        return true;
    }

    protected void navigateTo(WebDriver webDriver, String PAGE_URL){
        webDriver.get(PAGE_URL);
    }
}
