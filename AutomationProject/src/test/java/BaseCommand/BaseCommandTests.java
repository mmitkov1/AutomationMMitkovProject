package BaseCommand;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class BaseCommandTests {
    @Test
    public void testBrowserCommands() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        String title = webDriver.getTitle();
        System.out.println("Page title is: " + title);

        webDriver.get("http://training.skillo-bg.com:4200/users/login");

        String url = webDriver.getCurrentUrl();
        System.out.println("The page url is: " + url);

        webDriver.close();

        webDriver.quit();
    }

    @Test
    public void closeCurrentWindow(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        Object[] windowHandles = webDriver.getWindowHandles().toArray();

        openUrlInNewWindow(webDriver, "http://training.skillo-bg.com:4200/users/login");

        webDriver.close();

        webDriver.switchTo().window((String) windowHandles[0]);

        System.out.println(webDriver.getCurrentUrl());
    }

    private void openUrlInNewWindow(ChromeDriver webDriver, String url){
        webDriver.switchTo().newWindow(WindowType.WINDOW);
        webDriver.get(url);
    }

    @Test
    public void testBrowserNavigationCommands() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        String title = webDriver.getTitle();
        System.out.println("Page title is: " + title);

        webDriver.navigate().to("http://training.skillo-bg.com:4200/users/login");

        String url = webDriver.getCurrentUrl();
        System.out.println("The page url is: " + url);

        webDriver.navigate().back();

        String urlAfterBack = webDriver.getCurrentUrl();
        System.out.println("The page url is: " + urlAfterBack);

        webDriver.navigate().forward();

        String urlAfterForward = webDriver.getCurrentUrl();
        System.out.println("The page url is: " + urlAfterForward);

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void findElement(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        WebElement login = webDriver.findElement(By.id("nav-link-login"));

        System.out.println(login);

        webDriver.quit();
    }

    @Test
    public void findElements(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        List<WebElement> navigationLinks = webDriver.findElements(By.xpath("//*[@class='nav-link']"));

        for (WebElement item : navigationLinks) {
            System.out.println(item);
        }

        webDriver.quit();
    }

    @Test
    public void getElements(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        List<WebElement> navigationLinks = webDriver.findElements(By.xpath("//*[@class='nav-link']"));

        for (WebElement link : navigationLinks) {
            System.out.println("Text of menu link is: " + link.getText());
        }

        webDriver.quit();
    }

    @Test
    public void clickElement(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        WebElement login = webDriver.findElement(By.id("nav-link-login"));

        System.out.println("Click on " + login.getText());

        login.click();

        System.out.println("Login page is open");

        webDriver.quit();
    }

    @Test
    public void sendKeysElement(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4200/posts/all");

        WebElement login = webDriver.findElement(By.id("nav-link-login"));

        System.out.println("Click on " + login.getText());

        login.click();

        System.out.println("Login page is open");

        WebElement username = webDriver.findElement(By.id("defaultLoginFormUsername"));
        WebElement password = webDriver.findElement(By.id("defaultLoginFormPassword"));

        username.sendKeys("blabla");
        password.sendKeys("123456");

        webDriver.quit();
    }
}
