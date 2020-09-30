package com.mayab.calidad.doubles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.testing.Assert.assertEquals;

//3er test
import static org.mockito.Matchers.*;
//Crear comportamiento al vuelo
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestDependency {

	private Dependency dependency;
	
	@Before
	public void setupMocks()
	{
		dependency = mock(Dependency.class);
		//dependency = new Dependency(new SubDependency()); //OBJETO REAL
	}
	@Test
	public void test() {
		when(dependency.getClassName()).thenReturn("Mi nombre");
		assertThat(dependency.getClassName(), is("Mi nombre"));
		
		//Marca failure porque la clase no tiene valores
		//assertThat(dependency.getClassName(), is("Dependency"));
		
		/*assertNull(dependency.getClassName());
		assertNull(dependency.getClassNameUpperCase());
		assertNull(dependency.getSubDependencyClassName());*/
	}
	
	@Test
	public void OtroTest()
	{
		when(dependency.getClassName()).thenReturn("Otro nombre");
		assertThat(dependency.getClassName(), is ("Otro nombre"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		when(dependency.getClassName()).thenThrow(IllegalArgumentException.class);
		dependency.getClassName();
	}
	
	//Qué pasa si el método espera un parametro de entrada? podemos usar el addTwo
	@Test
	public void testAddTwo() {
		when(dependency.addTwo(1)).thenReturn(5); //Lo que hace es que solo regresará 5 cuando se le pase 1 como parametro.
		assertEquals(5, dependency.addTwo(1));
		assertEquals(0, dependency.addTwo(27)); //no probar con 0 porque equivale a un NULL por ser entero. Cambiar 0 a 2
	}
	
	//Crear un comportamiento al vuelo
	@Test
	public void testAnswer() {
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				return arg + 20;
			}
		});
	assertEquals(30, dependency.addTwo(10));
	}
	
	@Test
	public void MultiplyTest()
	{
		when(dependency.multiply(anyInt(), anyInt())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				int arg2 = (Integer) invocation.getArguments()[1];
				return arg * arg2;
			}
		});
		assertEquals(50, dependency.multiply(10,5));
	} //create, retreive, update, delete
	
	
		
	

}
