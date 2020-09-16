package com.mayab.calidad;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPassword {

	static User user;
	
	@BeforeClass
	public static void setup()
	{
		System.out.println("Inicio de pruebas de la clase User");
		user = new User();
	}
	
	@Test
	public void EmptyString()
	{
		String expectedResult = "";
		assertThat(expectedResult, is(user.getPassword()));
	}
	@Test
	public void AlmenosUnSimb()
	{
		user.setPassword("Contraseña?");
		boolean expectedResult = true;
		assertThat(expectedResult, is(user.ContieneSimb()));
		
	}
	

}
