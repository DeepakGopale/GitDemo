
package SeleniumAutomation.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
		
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException,  InterruptedException
	{
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		//CartPage cartPage = new CartPage(driver);
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submit();
		String confirmMessage = confirmationPage.getConfmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalog productcatalog = landingPage.loginApplication("gopaledeepak77@gmail.com","Deepak123@");
		OrderPage ordersPage = productcatalog.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumAutomation//data//PurchaseOrder.json"); 
			return new Object [] [] {{data.get(0)},{data.get(1)}};
		}
	
	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email", "gopaledeepak77@gmail.com");
	//	map.put("password", "Deepak123@");
	//	map.put("product", "ZARA COAT 3");
	//	
	//	HashMap<Object,Object> map1 = new HashMap<Object,Object>();
	//	map.put("email", "anshika@gmail.com");
	//	map.put("password", "Iamking@000");
	//	map.put("product", "ADIDAS ORIGINAL");
		
	//	return new Object [] [] {{map},{map1}};
	
	
	/*
	@DataProvider
	public Object[][] getData()
	{
		return new Object [] [] {{"gopaledeepak77@gmail.com","Deepak123@","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}
	*/

}
