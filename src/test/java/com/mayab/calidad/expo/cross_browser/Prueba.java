package com.mayab.calidad.expo.cross_browser;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Prueba {
	
     public static void main(String[] args) throws Exception {
    
    //IMPORTANTE usuario para la foto es Danrodarc que es mi nombre Daniel Rodríguez Arceo
    	 
     final String URL = "https://Danrodarc:a5f454ba-51b1-40b8-8d5d-3d4ac5ba0ede@ondemand.us-west-1.saucelabs.com:443/wd/hub";
     DesiredCapabilities caps = DesiredCapabilities.chrome(); 
 	 caps.setCapability("platform", "Windows 10");
     caps.setCapability("version", "latest");
     caps.setCapability("name", "chrome");
     caps.setCapability("extendedDebugging", "true");
     caps.setCapability("buildNumber", "3.0");
     WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);
     driver.get("https://mern-crud.herokuapp.com");
	 /* //private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\DanRo\\Documents\\Anahuac\\4to Semestre\\Calidad y Pruebas\\DriverChrome\\chromedriver.exe");
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }*/

	  //@Test
	  //public void testAdd() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Nombre");
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("Correo@gmail.com");
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("22");
	    driver.findElement(By.xpath("//div[3]/div[2]/div/div")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
	    driver.findElement(By.xpath("//form/button")).click();
	    
	    Thread.sleep(3000);
	    
	    WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")); //usar click derecho > full xpath	
	    assertEquals("Nice one!", element.getText());
	    //WebElement element2 = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
	    //assertEquals("Successfully added!", element.getText());
	 // }
    
    String expected = "Successfully added!";
    String gotMessage="";
    WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
    gotMessage = result.getText();
    assertThat(expected, is(equalTo(gotMessage)));
    boolean finalResult = false;
    if(gotMessage.equals("Successfully added!")) {
    	finalResult = true;
    }
    else {
    	finalResult = false;
    }
    if(finalResult) {
    	((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
    }
    else {
    	((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
    }
    driver.quit();
    }

}
