package com.mayab.calidad.StepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
public class TestLoginSteps {

	private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

	
	@Given("Browser is open")
	public void browser_is_open() {
		System.setProperty("System.setProperty(\"webdriver.chrome.driver\", \"C:\\\\Users\\\\DanRo\\\\Documents\\\\Anahuac\\\\4to Semestre\\\\Calidad y Pruebas\\\\DriverChrome\\\\chromedriver.exe\");")
		throw new io.cucumber.java.PendingException();
	}
	
	@And("user is in login page")
	public void user_is_in_login_page() {
		
		throw new io.cucumber.java.PendingException();
	}
	@When("user enters username and password")
	public void user_enters_username_and_password() {
		throw new io.cucumber.java.PendingException();
	}
	
	assertEquals("Logout",driver.findElement(By.id("logout")).getText());

	
}
