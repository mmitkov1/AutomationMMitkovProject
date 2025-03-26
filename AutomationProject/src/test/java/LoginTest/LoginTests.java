package LoginTest;

import LoginTest.page.factory.Header;
import LoginTest.page.factory.HomePage;
import LoginTest.page.factory.LoginPage;
import LoginTest.page.factory.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {

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
                {"vidko@test.com","123abc", 8335, "Successful login!"},
                {"vidko.v","123abc", 8335, "Successful login!"},
                {"p0li0m","TGdd7EDby83jdAC", 5508, "Successful login!"},
                {"dbsdhsh","sdhshs", 8333, "Successful login!"}};
    }

    @DataProvider(name="errorMessages")
    public Object[][] errorMessages(){
        return new Object[][]{
                {"blasfsafasfabla","asfsaf", "User not found"},
                {"dbsdhsh","asdasfsaf", "Ivalid password"},
                {"dbsdhsh","", "Password cannot be empty"},

                {"blasfsafasfabla@tezt.xom","asfsaf", "User not found"},
                {"blasfsafasfabla@.xom","asfsaf", "User not found"},
                {"blasfsafasfabla@","asfsaf", "User not found"},
                {"vidko@test.com","asdasfsaf", "Ivalid password"},
                {"viasfasfasfsadko@teafasfasfst.com","", "Password cannot be empty"},

                {"","TGdd7EDby83jdAC", "UsernameOrEmail cannot be empty"},
                {"","", "UsernameOrEmail cannot be empty"},
        };
    }

    @Test(dataProvider = "getUsers")
    public void LoginUser(String username, String password, int userId, String signInMessage){
        LoginPage login = new LoginPage(this.webDriver);
        ProfilePage profile = new ProfilePage(this.webDriver);
        Header header = new Header(this.webDriver);
        HomePage home = new HomePage(this.webDriver);

        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");

        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");

        login.populatePassword(password);
        login.populateUsername(username);
        login.clickSignIn();

        login.onSignInMessage(signInMessage);

        Assert.assertTrue(home.isUrlLoaded(), "Home page is not loaded");
        header.clickProfileLinkWithHandle();
        Assert.assertTrue(profile.isUrlLoaded(userId),"The user profile page is not loaded");
        Assert.assertTrue(profile.isUsernameAsExpected(username), "The username is not as expected");
    }

    @Test(dataProvider = "errorMessages")
    public void LoginErrorMessages(String username, String password, String signInMessageExpected){
        LoginPage login = new LoginPage(this.webDriver);
        ProfilePage profile = new ProfilePage(this.webDriver);
        Header header = new Header(this.webDriver);
        HomePage home = new HomePage(this.webDriver);

        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");

        login.populatePassword(password);
        login.populateUsername(username);
        login.clickSignIn();

        String singInMessageActual = login.getSignInMessage();

        Assert.assertEquals(singInMessageActual, signInMessageExpected, "Sing in error message is not as expected");
    }
}
