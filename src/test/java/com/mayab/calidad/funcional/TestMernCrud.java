//Agregar Systen.setProperty en clase
package com.mayab.calidad.funcional;

import org.junit.Test;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class TestMernCrud {
	
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
  public void testAdd() throws Exception {
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
  }
  
  @Test
  public void testDelete() throws Exception {
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
    
    WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/p")); //usar click derecho > full xpath	
    assertEquals("That email is already taken.", element.getText());
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

