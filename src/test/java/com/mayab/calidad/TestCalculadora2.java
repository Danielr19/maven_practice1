package com.mayab.calidad;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculadora2 {

	static Calculadora miCalculadora;
	
	@BeforeClass //Before antes de cada Test y before class antes de todos.
	public static void setup()
	{
		//Refactorizar código ahorramos una línea en cada void solo creandolo una vez en el Setup();
		System.out.println("Before Test--->");
		miCalculadora = new Calculadora();
	}
	@Test
	public void SumaPositivosTest() {
		float sumando1 = 4;
		float sumando2 = 4;
		float expectedResult = 8;
		float resultado = -1;
										//fail("Not yet implemented");
		
		System.out.println("Ultimo resultado = " +miCalculadora.getUltimaResultado());
		//end setup
		resultado = miCalculadora.suma(sumando1, sumando2);
		//end excercise
		
		//probar Asserts
		assertEquals(expectedResult,resultado,0); // ---> verify
		//no hay teardown porque no tenemos nada que limpiar, no hay BD ni archivos
	}
	
	@Test
	public void RestaPositivosTest()
	{
		float num1 = 4;
		float num2 = 2;
		float expectedResult = 2;
		float resultado = -1;
		
		
		//end setup
		System.out.println("Ultimo resultado = " +miCalculadora.getUltimaResultado());
		resultado = miCalculadora.resta(num1, num2);
		
		assertEquals(expectedResult,resultado,0); //-->verify
		assertThat(resultado, is(expectedResult));
	}
	
	@Test
	public void MultiplicacionPositivosTest()
	{
		float num1 = 3;
		float num2 = 4;
		float expectedResult = 12;
		float resultado = -1;
		
		System.out.println("Ultimo resultado = " + miCalculadora.getUltimaResultado());
		resultado = miCalculadora.multiplicacion(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
	
	@Test
	public void DivisionPositivosTest()
	{
		float numerador = 6;
		float denominador = 3;
		float expectedResult = 2;
		float resultado = -1;
		
		System.out.println("Ultimo resultado division " + miCalculadora.getUltimaResultado());
		resultado = miCalculadora.division(numerador, denominador);
		assertThat(resultado, is(expectedResult));
	}
	@Test
	public void DivisionNegativosTest()
	{
		float numerador = 6;
		float denominadorNeg = -2;
		float expectedResult = -3;
		float resultado = -1;
				
		System.out.println("Ultimo resultado division negativa: " + miCalculadora.getUltimaResultado());
		resultado = miCalculadora.division(numerador, denominadorNeg);
		assertThat(resultado, is(expectedResult));
	}
	@Test
	public void DivisionCeroTest()
	{
		float num = 5;
		float denominador = 0;
		float expectedResult = Float.POSITIVE_INFINITY;
		float resultado = -1;
		
		System.out.println("División entre 0:");
		resultado = miCalculadora.division(num, denominador);
		assertThat(resultado, is(expectedResult));
	}
	public void CeroEntreCero()
	{
		float num1 = 0;
		float num2 = 0;
		float expectedResult = Float.NaN;
		float resultado = -1;
		
		System.out.println("Cero entre Cero:" );
		resultado = miCalculadora.division(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
	@After
	public void Despues()
	{
		System.out.println("Despues de la prueba");
	}
	
	@AfterClass
	public static void Fin()
	{
		System.out.println("Ya termino de ejecutarse la prueba");
	}
}
