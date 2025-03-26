package MultipleTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class PostModal {
    private final WebDriver webDriver;
    @FindBy(className="modal-dialog")
    private WebElement modalElement;

    @FindBy(xpath = "//*[@class='post-modal-img']")
    private WebElement imagePostModal;

    @FindBy(className = "post-user")
    private WebElement postUser;

    @FindBy(className = "post-title")
    private WebElement postTitle;


    public PostModal(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isImageVisible() {
        try {
            WebElement image = imagePostModal;
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPostTitle() {
        //WebElement postTitle = modalElement.findElement(By.className("post-title"));
        return postTitle.getText();
    }

    public String getPostUser() {
        return postUser.getText();
    }
}
