package testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.ProductsPage;

public class LoginTest {
	
	    private WebDriver driver;
	    private LoginPage loginPage;
	    private ProductsPage productsPage;

	    @BeforeMethod
	    public void setUp() {
	        // Set up WebDriver
	    	System.setProperty("webdriver.Chrome.driver",
					"C:\\Users\\sharm\\Downloads\\chromedriver\\chromedriver\\chromedriver.exe");
			
	    	
	        
	        driver = new ChromeDriver();

	        driver.get("https://www.saucedemo.com/v1/");
	        loginPage = new LoginPage(driver);
	        productsPage = new ProductsPage(driver);
	    }

	    @Test
	    public void loginTest() {
	        // Perform login
	        loginPage.enterUsername("standard_user");
	        loginPage.enterPassword("secret_sauce");
	        loginPage.clickLoginButton();

	        // Verify login by checking the product page
	        String productName = productsPage.getProductName();
	        Assert.assertEquals(productName, "Sauce Labs Backpack");
	    }

	    @Test
	    public void loginWithoutPassword() {
	        loginPage.enterUsername("standard_user");
	        loginPage.clickLoginButton();

	        // Verify error state
	        Assert.assertTrue(loginPage.errorstate());
	    }

	    @Test
	    public void verifyTitle() {
	        String actualTitle = loginPage.verifyTitle();
	        String expectedTitle = "Swag Labs";
	        Assert.assertEquals(actualTitle, expectedTitle);
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Close the WebDriver
	        driver.quit();
	    }
	}

