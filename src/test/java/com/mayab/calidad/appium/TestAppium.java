package com.mayab.calidad.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

import org.openqa.selenium.remote.DesiredCapabilities;

public class TestAppium {

  private AndroidDriver driver;

  @Before
  public void setUp() throws MalformedURLException {
	  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	  desiredCapabilities.setCapability("platformName", "android");
	  desiredCapabilities.setCapability("appPackage", "com.mayab.calidad.calculator");
	  desiredCapabilities.setCapability("appActivity", "com.mayab.calidad.calculator.MainActivity");
	  desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

	  URL remoteUrl = new URL("http://localhost:4723/wd/hub");
	  driver = new AndroidDriver(remoteUrl, desiredCapabilities);
  }
  
  private String getOperation(String operation) {
	  switch (operation) {
	  case "+":
		  return "Add";
	  case "-":
		  return "Sub";
	  case "*":
		  return "Mul";
	  case "/":
		  return "Div";
	  case "sqrt":
		  return "Sqrt";
	  }
	  return "";
  }
  
  private String getButtonName(char character) {
	  if (character == '.')
		  return "dec";
	  else if (character == '-')
		  return "negative";
	  return character + "";
  }
  
  private void write(float randomOne, String operation, float randomTwo) {
	  String first = randomOne + "";
	  String second = randomTwo + "";
	  for(int i = 0; i < first.length(); i++) {
		  MobileElement e = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_" + getButtonName(first.charAt(i)));
		  e.click();
	  }
	  MobileElement e = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_" + getOperation(operation));
	  e.click();
	  for(int i = 0; i < second.length(); i++) {
		  MobileElement e1 = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_" + getButtonName(second.charAt(i)));
		  e1.click();
	  }
	  MobileElement e1 = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_calc");
	  e1.click();
  }
  
  private void write(float randomOne, String operation) {
	  String first = randomOne + "";
	  for(int i = 0; i < first.length(); i++) {
		  MobileElement e = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_" + getButtonName(first.charAt(i)));
		  e.click();
	  }
	  MobileElement e = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_" + getOperation(operation));
	  e.click();
	  MobileElement e1 = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/btn_calc");
	  e1.click();
  }
  
  private String getDisplayText() {
	  MobileElement display = (MobileElement) driver.findElementById("com.mayab.calidad.calculator:id/edText1");
	  return display.getText() + "";
  }

  @Test
  public void add_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float randomTwo = rand.nextFloat();
	Float result = randomOne + randomTwo;
	//String esperado
	String stringResult = result + "";
	//Selección de operación
	String operation = "+";
	//Escritura
	write(randomOne, operation, randomTwo);
	//Comparación
	assertThat(getDisplayText(), is(stringResult));
  }
  
  @Test
  public void substract_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float randomTwo = rand.nextFloat();
	Float result = randomOne - randomTwo;
	//String esperado
	String stringResult = result + "";
	//Selección de operación
	String operation = "-";
	//Escritura
	write(randomOne, operation, randomTwo);
	//Comparación
	assertThat(getDisplayText(), is(stringResult));
  }

  @Test
  public void multiply_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float randomTwo = rand.nextFloat();
	Float result = randomOne * randomTwo;
	//String esperado
	String stringResult = result + "";
	//Selección de operación
	String operation = "*";
	//Escritura
	write(randomOne, operation, randomTwo);
	//Comparación
	assertThat(getDisplayText(), is(stringResult));
  }

  
  @Test
  public void division_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float randomTwo = rand.nextFloat();
	Float result = randomOne / randomTwo;
	//String esperado
	String stringResult = result + "";
	//Selección de operación
	String operation = "/";
	
	write(randomOne, operation, randomTwo);
	assertThat(getDisplayText(), is(stringResult));
  }
  
  @Test
  public void squareroot_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float result = (float) java.lang.Math.sqrt(randomOne);
	//String esperado
	String stringResult = new DecimalFormat("#.###").format(result);
	//Selección de operación
	String operation = "sqrt";
	
	write(randomOne, operation);
	assertThat(getDisplayText(), is(stringResult));
  }
  
  @Test
  public void divide_By_Zero_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = rand.nextFloat();
	Float randomTwo = 0.0f;
	//Conversión a String
	String stringResult = "Error";
	//Selección de operación
	String operation = "/";
	
	write(randomOne, operation, randomTwo);
	assertThat(getDisplayText(), is(stringResult));
  }
  
  @Test
  public void negative_Squareroot_Test() {
	//Genera Random
	Random rand = new Random();
	//Números aleatorios flotantes
	Float randomOne = -rand.nextFloat();
	//String esperado
	String stringResult = "Error";
	//Selección de operación
	String operation = "sqrt";
	
	write(randomOne, operation);
	assertThat(getDisplayText(), is(stringResult));
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
}