package com.mayab.calidad.doubles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.testing.Assert.assertEquals;

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
		
		assertNull(dependency.getClassName());
		assertNull(dependency.getClassNameUpperCase());
		assertNull(dependency.getSubDependencyClassName());
	}
	
	@Test
	public void OtroTest()
	{
		when(dependency.getClassName()).thenReturn("Otro nombre");
		assertThat(dependency.getClassName(), is ("Otro nombre"));
	}
	

}
