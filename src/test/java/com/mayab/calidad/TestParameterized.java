package com.mayab.calidad;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;	

@RunWith(Parameterized.class)
public class TestParameterized {

	@Parameters
		public static Collection<Object[]> data(){
			return Arrays.asList(new Object[][]{
				{4,2,2},
	            {6,3,2},
	             {5,5,1},
	             {10,5,2}
			});
		}	
	
	
	private int multiplierOne;
	private int expected;
	private int multiplierTwo;
	
	public TestParameterized(int expected, int multiplierOne, int multiplierTwo) //MultiplyTest
	{
		this.expected = expected;
		this.multiplierOne = multiplierOne;
		this.multiplierTwo = multiplierTwo;
	}
	@Test
	public void test() {
		
	}
	@Test
	public void givenTwoNumbersShouldBeMultiplyResult() {
		int resultado = multiplierOne * multiplierTwo;
		assertThat(expected, is(resultado));
	}

}
