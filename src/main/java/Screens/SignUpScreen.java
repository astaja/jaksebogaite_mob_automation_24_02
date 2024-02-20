package Screens;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

public class SignUpScreen {
    private AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign up\"]")
        private RemoteWebElement signUp;


    @AndroidFindBy(accessibility = "input-email")
        private RemoteWebElement email;

    @AndroidFindBy(accessibility = "input-password")
        private RemoteWebElement password;

    @AndroidFindBy(accessibility = "input-repeat-password")
        private RemoteWebElement repeatPassword;

    @AndroidFindBy(accessibility = "button-SIGN UP")
        private RemoteWebElement signupButton;


    public SignUpScreen(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }

    public void isDisplayed(){

        signUp.isDisplayed();
        email.isDisplayed();
        password.isDisplayed();
        repeatPassword.isDisplayed();
        signupButton.isDisplayed();
    }



}
