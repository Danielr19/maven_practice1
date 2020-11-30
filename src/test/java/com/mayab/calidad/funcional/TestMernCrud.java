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
		System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("andres abimeri");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("andres@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("23");
	    driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
	    driver.findElement(By.xpath("//form/button")).click();
	  }

	  @Test
	  public void testPruebaDelete() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("prueba delete");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("delete@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("18");
	    driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
	    driver.findElement(By.xpath("//form/button")).click();
	    driver.findElement(By.xpath("//i")).click();
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
	    String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]")).getText();
	    driver.findElement(By.xpath("//div[3]/button")).click();
	    assertEquals(text, "Delete User");
	  }
	  
	  @Test
	  public void testRead() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    String expected = "andres@gmail.com";
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
	    List<WebElement> elementos = table.findElements(By.tagName("td"));
	    String found;
	    for(WebElement elemento : elementos) {
	    	if(elemento.getText() == "andres@gmail.com") {
	    		found = elemento.getText();
	    		assertEquals(found, expected);
	    	}
	    }
	    
	    
	    
	  }
	  
	  
	  @Test
	  public void testUpdatePrueba() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("updatePrueba1");
	    pause(5000);
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
	    pause(5000);
	    String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
	    assertEquals(text, "Successfully updated!");
	  }
	  
	  @Test
	  public void testAgregarsuccess() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("andres abimeri");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("andres.abimeri23@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("23");
	    driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
	    driver.findElement(By.xpath("//form/button")).click();
	    pause(5000);
	    String texto = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
	    assertEquals(texto, "Nice one!");
	  }
	  
	  
	  @After
	  public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e){
			  e.printStackTrace();
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

