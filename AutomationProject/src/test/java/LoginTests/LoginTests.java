package LoginTests;

import LoginTests.page.object.Header;
import LoginTests.page.object.HomePage;
import LoginTests.page.object.LoginPage;
import LoginTests.page.object.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {
    private final String PASSWORD = "TGdd7EDby83jdAC";
    private WebDriver webDriver;
    @BeforeSuite
    protected final void setupTestSuite(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest(){
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.webDriver.navigate().to("http://training.skillo-bg.com:4200/users/login");
    }
    @AfterMethod
    private final void tearDownTest(){
        if (this.webDriver != null) {
            this.webDriver.close();
        }
    }

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"blasfsafasfabla", 1234, "User not found"},
                {"p0li0m", 5508, "Successful login!"},
                {"dbsdhsh", 1512, "Ivalid password"}};
    }

    @Test(dataProvider = "getUsers")
    public void LoginUser(String username, int userId, String errorMessage){
        LoginPage login = new LoginPage(this.webDriver);
        ProfilePage profile = new ProfilePage(this.webDriver);
        Header header = new Header(this.webDriver);
        HomePage home = new HomePage(this.webDriver);

        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");

        String singInTitle = login.getSignInElementText();
        Assert.assertEquals(singInTitle,"Sign in", "Login form is not loaded");

        login.populatePassword(this.PASSWORD);
        login.populateUsername(username);
        login.clickSignIn();

        Assert.assertTrue(home.isUrlLoaded(), "Home page is not loaded");

        header.clickProfileLink();

        Assert.assertTrue(profile.isUrlLoaded(userId),"The user profile page is not loaded");
        Assert.assertTrue(profile.isUsernameAsExpected(username), "The username is not as expected");
    }
}
