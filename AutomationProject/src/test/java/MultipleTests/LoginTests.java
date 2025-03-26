package MultipleTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestObject {

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"vidko@test.com","123abc", 8335, "Successful login!"},
                {"vidko.","123abc", 8335, "Successful login!"},
                {"p0li0m","TGdd7EDby83jdAC", 5508, "Successful login!"},
                {"dbsdhsh","sdhshs", 8333, "Successful login!"}
                };
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
        WebDriver webDriver = getDriver();
        LoginPage login = new LoginPage(webDriver);
        ProfilePage profile = new ProfilePage(webDriver);
        Header header = new Header(webDriver);
        HomePage home = new HomePage(webDriver);

        login.navigateTo();

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
        WebDriver webDriver = getDriver();
        LoginPage login = new LoginPage(webDriver);

        login.navigateTo();

        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");

        login.populatePassword(password);
        login.populateUsername(username);
        login.clickSignIn();

        String singInMessageActual = login.getSignInMessage();

        Assert.assertEquals(singInMessageActual, signInMessageExpected, "Sing in error message is not as expected");
    }

    @Test
    public void LoginUserPathVerification() {
        WebDriver webDriver = getDriver();
        LoginPage login = new LoginPage(webDriver);
        Header header = new Header(webDriver);
        HomePage home = new HomePage(webDriver);

        home.navigateTo();
        Assert.assertTrue(home.isUrlLoaded(),"The home page is not loaded");

        header.clickLoginLinkWithHandle();

        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");
    }
}
