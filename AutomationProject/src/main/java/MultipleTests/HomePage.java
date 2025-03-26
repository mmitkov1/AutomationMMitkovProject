package MultipleTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends NavigationAndUrlValidation {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isUrlLoaded(){
        return isUrlLoaded(webDriver, PAGE_URL);
    }

    public void navigateTo(){
        //this.webDriver.get(PAGE_URL);
        super.navigateTo(webDriver, PAGE_URL);
    }
}
