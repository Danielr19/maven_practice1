package com.mayab.calidad.funcional;

import static org.junit.Assert.*;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

import org.junit.Test;

public class TestGoogle {

	//USAR INCOGNITO
	private WebDriver driver; //forma de comunicarnos desde JAVA con el navegador (traductor)
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\DanRo\\Documents\\Anahuac\\4to Semestre\\Calidad y Pruebas\\DriverChrome\\chromedriver.exe");
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testUntitledTestCase() throws Exception {
	    driver.get("https://www.google.com/search?q=covid&rlz=1C1CHBF_esMX913MX913&oq=covid&aqs=chrome.0.35i39l2j0i131i433j0i433l2j0i131i433j0i433j0.696j0j15&sourceid=chrome&ie=UTF-8");
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("covid");
	    driver.findElement(By.id("tsf")).submit();
	    driver.findElement(By.xpath("//div[@id='kp-wp-tab-overview']/div[5]/div[2]/div/div/div/div/div/div/div/a/h3/span")).click(); //usar click derecho > full xpath	
	    assertEquals("Preguntas y respuestas sobre la enfermedad por coronavirus (COVID-19)", driver.getTitle());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
