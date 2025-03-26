package MultipleTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;

public class PostPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/create";
    private final WebDriver webDriver;
    @FindBy(css = "img.image-preview")
    private WebElement postImage;
    @FindBy(css = "input.input-lg")
    private WebElement postImageTextElement;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;
    @FindBy(name = "caption")
    private WebElement captionElement;
    @FindBy(id = "create-post")
    private WebElement createPostButton;

    public PostPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isImageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(postImage)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getImageName() {
        return postImageTextElement.getAttribute("placeholder");
    }

    public void uploadPicture(File file) {
        uploadField.sendKeys(file.getAbsolutePath());
    }

    public void populatePostCaption(String caption) {
        captionElement.sendKeys(caption);
    }

    public void clickCreatePost() {
        createPostButton.click();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PostPage.PAGE_URL));
    }
}
