package org.example;


import PageObjects.LoginPage;
import com.sun.corba.se.impl.interceptors.CDREncapsCodec;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    WebDriver driver;
    LoginPage loginPage;

    /**
     * 1. Login với blank value -> SHow message lỗi
     * 2. Các message lỗi sẽ hide đi sau khi nhận giá trị hợp lệ
     * 3. THông báo lỗi khi login với mật khẩu sai
     * 4. Thông báo lỗi khi login với tài khoản không tồn tại
     * 5. SHow homepage khi login với email/pass đúng.g
     */

    @Before
    public void StartWebDriver()
    {
        driver = new ChromeDriver();
        driver.get("https://fado.vn/dang-nhap?redirect=https%3A%2F%2Ffado.vn%2F");

        loginPage = new LoginPage(driver);
    }

    /*
     * 1. Login với blank value -> SHow message lỗi
     *
     */
    @Test
    public void TestLogin_NoInput_All()
    {
        // loginPage.tbEmail.sendKeys(" ");
        //loginPage.tbPass.sendKeys(" ");
        loginPage.btnLogin.click();
        Assert.assertEquals("Vui lòng nhập dữ liệu", loginPage.getErrorMessage_Email());
    }

    @Test
    public void TestLogin_NoInput_Email()
    {
        loginPage.tbPass.sendKeys("1234567");
        loginPage.btnLogin.click();
        Assert.assertEquals("Vui lòng nhập dữ liệu", loginPage.getErrorMessage_Email());
    }

    @Test
    public void TestLogin_NoInput_Pass()
    {
        loginPage.tbPass.sendKeys("testautomation0422@gmail.com");
        loginPage.btnLogin.click();
        Assert.assertEquals("Vui lòng nhập dữ liệu", loginPage.getErrorMessage_Pass());
    }

    /*
     * 2. Các message lỗi sẽ hide đi sau khi nhận giá trị hợp lệ
     */
    @Test
    public void TestLogin_HideEmailError()
    {
        loginPage.tbPass.sendKeys("1234567");
        loginPage.btnLogin.click();

        boolean isDisplayed = true;
        String errEmail = loginPage.getErrorMessage_Email();
        if (errEmail.length() > 0)
        {
            loginPage.tbEmail.sendKeys("testautomation@gmail.com");
            loginPage.tbPass.clear();
            loginPage.btnLogin.click();
            isDisplayed = loginPage.lbEmailRequireMessage.isDisplayed();
        }
        System.out.println(isDisplayed);
        Assert.assertEquals(false, isDisplayed);
    }

    @Test
    public void TestLogin_HidePassError()
    {
        loginPage.tbEmail.sendKeys("testautomation@gmail.com");
        loginPage.btnLogin.click();

        boolean isDisplayed = true;
        String errPass = loginPage.getErrorMessage_Pass();
        if (errPass.length() > 0)
        {
            loginPage.tbEmail.clear();
            loginPage.tbPass.sendKeys("1234567");
            loginPage.btnLogin.click();
            isDisplayed = loginPage.lbPassRequireMessage.isDisplayed();
        }
        System.out.println(isDisplayed);
        Assert.assertEquals(false, isDisplayed);
    }

    /*
     * 3. THông báo lỗi khi login với mật khẩu sai
     * Có lỗi xảy ra:
     */
    @Test
    public void TestLogin_PassIsWrong()
    {
        loginPage.tbEmail.sendKeys("testautomation0422@gmail.com");
        loginPage.tbPass.sendKeys("1234567");
        loginPage.btnLogin.click();
        Assert.assertEquals("Có lỗi xảy ra:", loginPage.getErrorMessage_PassIsWrong());
    }

    /*
     * 4. Thông báo lỗi khi login với tài khoản không tồn tại
     * Có lỗi xảy ra:
     */
    @Test
    public void TestLogin_NoAccount()
    {
        loginPage.tbEmail.sendKeys("test@gmail.com");
        loginPage.tbPass.sendKeys("1234567");
        loginPage.btnLogin.click();
        Assert.assertEquals("Có lỗi xảy ra:", loginPage.getErrorMessage_NoAccount());
    }

    /*
     * 5. Show homepage khi login với email/pass đúng
     */
    @Test
    public void TestLogin_Success()
    {
        loginPage.tbEmail.sendKeys("testautomation0422@gmail.com");
        loginPage.tbPass.sendKeys("987654321a");
        loginPage.btnLogin.click();

        Boolean isLogin = loginPage.isLoadedHomePage();
        System.out.println(loginPage.getCurrentURL_Title());
        Assert.assertEquals(true, isLogin);
    }
}
