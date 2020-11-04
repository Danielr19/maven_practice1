package com.mayab.calidad.funcional;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class TestFacebook {
  private WebDriver driver;
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
  public void testFace() throws Exception {
	  //driver.get("https://www.google.com/");
	    //driver.findElement(By.name("q")).clear();
	    //driver.findElement(By.name("q")).sendKeys("facebook");
	  	//driver.findElement(By.id("tsf")).submit();
	    //driver.findElement(By.xpath("//div[@id='rso']/div/div/div/div/a/h3/span")).click();
	    
	  driver.get("https://facebook.com/");  
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danrodarc@outlook.com");
	    driver.findElement(By.id("pass")).clear();
	    driver.findElement(By.id("pass")).sendKeys("ContraMal");
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).click();
	    
	    Thread.sleep(3000);
	    WebElement element = driver.findElement(By.className("_9ay7"));
	    assertEquals("La contraseña que has introducido es incorrecta. ¿Has olvidado la contraseña?", element.getText());
	    //WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[2]/form/div/div[1]/div[2]")); //usar click derecho > full xpath	

    
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
