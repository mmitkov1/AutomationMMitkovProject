package MultipleTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostTests extends LoginTests {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\upload\\testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{"vidko.v", "123abc", "vidko.v", postPicture, caption}};
    }

    @Test(dataProvider = "getUsers")
    public void testCreatePost(String user, String password, String username, File file, String caption) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickProfileLinkWithHandle();

        ProfilePage profilePage = new ProfilePage(driver);
        int countPosts = profilePage.getPostCount();

        header.clickNewPostLinkWithHandle();

        PostPage postPage = new PostPage(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "The POST URL is not correct!");
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");
        postPage.populatePostCaption(caption);
        postPage.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(8335), "The Profile URL is not correct!");
        Assert.assertEquals(profilePage.getPostCount(), countPosts+1, "The number of Posts is incorrect!");
        profilePage.clickPost(countPosts);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}