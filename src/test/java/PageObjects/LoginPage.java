package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    @FindBy(css="input#auth-block__form-group__email")
    public WebElement tbEmail;

    @FindBy(css = "input[type=\"password\"]")
    public WebElement tbPass;

    @FindBy(css="div.auth-block__btn-group button[type=submit]")
    public WebElement btnLogin;

    @FindBy(css="label#auth-block__form-group__email-error")
    public WebElement lbEmailRequireMessage;

    @FindBy(css="label#password-error")
    public WebElement lbPassRequireMessage;

    @FindBy(css="div.my-alert b")
    public WebElement lbNoAccountMessage;

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getErrorMessage_Email()
    {
        return lbEmailRequireMessage.isDisplayed()? lbEmailRequireMessage.getText() : "" ;
    }

    public String getErrorMessage_Pass()
    {
        return lbPassRequireMessage.isDisplayed()? lbPassRequireMessage.getText() : "" ;
    }




    /*
        Có lỗi xảy ra:
    */
    public String getErrorMessage_NoAccount()
    {
        WebDriverWait waiter = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        waiter.until(ExpectedConditions.visibilityOf(lbNoAccountMessage));
        System.out.println(lbNoAccountMessage.getText());
        return lbNoAccountMessage.getText();
    }

    /*
      Có lỗi xảy ra:
      - Mật khẩu không đúng, vui lòng kiểm tra lại
    */
    public String getErrorMessage_PassIsWrong()
    {
        WebDriverWait waiter = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        waiter.until(ExpectedConditions.visibilityOf(lbNoAccountMessage));
        System.out.println(lbNoAccountMessage.getText());
        return lbNoAccountMessage.getText();
    }
    /*
         waiter log homepage
     */
    public Boolean isLoadedHomePage()
    {
        WebDriverWait waiter = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        return waiter.until(ExpectedConditions.titleIs("Fado - Mua Hàng Quốc Tế Cao Cấp Từ Mỹ, Nhật, Đức, Anh"));
    }

    public String getCurrentURL_Title()
    {
        return driver.getTitle();
    }



}
