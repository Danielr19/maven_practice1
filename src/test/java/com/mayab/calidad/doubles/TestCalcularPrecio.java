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

public class TestCalcularPrecio {

	//Probar la dependencia que es iva yucatan
	//DEPENDENCIA = uso de una clase externa
	private CalcularIVAYucatan ivaYucatan;
	private CalcularPrecio calculadora;
	
	@Before
	public void setup() {
		ivaYucatan = mock(CalcularIVAYucatan.class);
		calculadora = new CalcularPrecio();
	}
	//Lo que queremos es calcular el metodo calcular precio
	
	@Test
	public void CalcularTest() {
		
		//when(ivaYucatan.getIVA(anyFloat(), anyFloat())).thenReturn(116.0f); //DUMMY
		
		when(ivaYucatan.getIVA(anyFloat(), anyFloat())).thenAnswer(new Answer<Float>() {
			public Float answer(InvocationOnMock invocation) throws Throwable{
				float porcentaje = (float) invocation.getArguments()[0];
				float base = (float) invocation.getArguments()[1];
				return porcentaje * base;
			}
		});
		float total = calculadora.Calcular(ivaYucatan);
		assertThat(total, is(116.0f));
	}

}
