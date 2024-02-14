package SeleniumAutomation.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumAutomation.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinationImp1 extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage= launchApplication();
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username,String password)
	{
		productCatalog = landingPage.loginApplication(username,password);
	}
	
	@When("^I and product (.+) to cart$")
	public void I_and_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submit();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"(^\"]*)\" message is displayed$")
	public void something_message_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
