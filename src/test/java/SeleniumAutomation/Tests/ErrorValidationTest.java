
package SeleniumAutomation.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumAutomation.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {
	
	/*
	@Test
	public void submitOrder() throws IOException,  InterruptedException
	{
	
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("gopaledeepak77@gmail.com","Deepak123@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	*/
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException,  InterruptedException
	{
	
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("gopaledeepak77@gmail.com","Deepak123@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException,  InterruptedException
	{
	
		String productName = "ZARA COAT 3";
		LandingPage landingPage = new LandingPage(driver);
		ProductCatalog productcatalog = landingPage.loginApplication("gopaledeepak77@gmail.com","Deepak123@");
		
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
		//CartPage cartPage = new CartPage(driver);
		CartPage cartPage = productcatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
	
}
