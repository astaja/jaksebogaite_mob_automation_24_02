package Screens;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {
    private AndroidDriver driver;

    // Define elements
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Login\"])[1]")
        private RemoteWebElement login;

    @AndroidFindBy(accessibility = "input-password")
        private RemoteWebElement password;

    @AndroidFindBy(accessibility = "input-password")
        private RemoteWebElement email;
    @AndroidFindBy(accessibility = "button-LOGIN")
        private RemoteWebElement loginButton;

    public LoginScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }
//    public void loginUser(String email, String password){
//        email.sendKeys(email);
//        password.sendKeys(password);
//        loginButton.click();

    public void loginUser(String userEmail, String userPassword) {
        email.sendKeys(new String[]{userEmail});
        password.sendKeys(new String[]{userPassword});
        loginButton.click();
    }
}

