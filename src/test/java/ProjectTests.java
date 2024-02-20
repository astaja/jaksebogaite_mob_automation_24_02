import Screens.LoginScreen;
import Screens.SignUpScreen;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class ProjectTests {
    private AndroidDriver driver;
    public LoginScreen loginScreen;
    public SignUpScreen signUpScreen;

    @BeforeClass
    public void beforeClassSetup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("appium:automationName","UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage","com.wdiodemoapp");
        desiredCapabilities.setCapability("appium:appActivity","com.wdiodemoapp.SplashActivity");

        desiredCapabilities.setCapability("appium:noReset",true); //app is not starting every time

        URL localUrl = new URL("http://127.0.0.1:4723/");
        driver = new AndroidDriver(localUrl, desiredCapabilities);
        loginScreen = new LoginScreen(driver);
        signUpScreen = new SignUpScreen(driver);
    }

    @BeforeMethod // executed before tests
    public void beforeMethodSetup(){
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
//        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Login\"]")).click();
        driver.activateApp("com.wdiodemoapp");
        driver.executeScript("mobile: activateApp", Map.ofEntries(
                Map.entry("appId", "com.swaglabsmobileapp")
        ));
    }
    @DataProvider(name = "valid-user-data")
    public static Object[][] getUserData(){
        return new String[][]{
                {"email", "password"};
                {"email", "password"}
        };
    }

    @Test(dataProvider = "valid-user-data")
    public void firstTest(String email, String password) throws InterruptedException {
//        Thread.sleep(5000);
        driver.findElement(By.xpath("(//android.widget.TextView[@text=\"Login\"])[1]")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("input-email")).sendKeys("bad email address");
        driver.findElement(new AppiumBy.ByAccessibilityId("input-password")).sendKeys("1234");
        driver.findElement(new AppiumBy.ByAccessibilityId("button-LOGIN")).click();
    }

    @Test
    public void secondTest(){
        String email = "hello hello";
        String password = "0000";
        driver.findElement(new AppiumBy.ByAccessibilityId("input-email")).sendKeys(email);
        driver.findElement(new AppiumBy.ByAccessibilityId("input-password")).sendKeys(password);
        driver.findElement(new AppiumBy.ByAccessibilityId("button-LOGIN")).click();

        String expectedEmail = "Please enter a valid email address";
        String actualEmail = driver.findElement(By.xpath("//android.widget.TextView[@text='" + expectedEmail + "']")).getText();
        Assert.assertEquals(actualEmail, actualEmail);

        String expectedPassword = "Please enter at least 8 characters";
        String actualPassword = driver.findElement(By.xpath("//android.widget.TextView[@text='" + expectedPassword + "']")).getText();
        Assert.assertEquals(actualPassword, actualPassword);
    }
    @Test
    public void thirdTest(){ //implementation of POM
        String email = "hello hello";
        String password = "0000";
        loginScreen.loginUser(email, password);
        signUpScreen.isDisplayed();
    }

    @Test
    public void signUpTest(){ //sign up
        String email = "bonjour";
        String password = "00001";
        String repeatPassword = "0003";
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign up\"]")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("input-email")).sendKeys(email);
        driver.findElement(new AppiumBy.ByAccessibilityId("input-password")).sendKeys(password);
        driver.findElement(new AppiumBy.ByAccessibilityId("input-repeat-password")).sendKeys(repeatPassword);
        driver.findElement(new AppiumBy.ByAccessibilityId("button-SIGN UP")).click();
    }

//    @Test
//    public void validateInputField(){
//    }
//    @Test
//    public void validateDropdown(){
//    }
//    @Test
//    public void swipe(){
//    }
//    @Test
//    public void dragDrop(){
//    }

    @AfterMethod
    public void afterMethodSetup(){
    }

    @AfterClass
    public void afterClassSetup(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.terminateApp()
//        driver.quit();
    }

}
