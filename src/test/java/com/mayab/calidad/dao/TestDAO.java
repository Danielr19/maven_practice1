package com.mayab.calidad.dao;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
//import static org.testing.Assert.assertEquals;

//3er test
import static org.mockito.Matchers.*;
//Crear comportamiento al vuelo
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.calidad.dao.Alumno;
import com.mayab.calidad.dao.DAOFake;


public class TestDAO {

	Alumno alumno;
	private DAOFake fake;
	//Alumno a1 = new Alumno();
	
	@Before
	public void beforeTest()
	{
		fake = new DAOFake();
		//alumno = new Alumno("Daniel", 20, 8.5f, "danrodarc@outlook.com");
		alumno = new Alumno(1, "Daniel", 20, 8.5f, "00368487@anahuac.mx");
		//fake.addAlumno(alumno);
	}
	@Test
	public void AddTest()
	{
		int origSize = fake.getBaseDatos().size();
		fake.addAlumno(alumno);
		
		assertThat(fake.getBaseDatos().size(), is(origSize + 1));
		//int size = fake.baseDatos.size();
		
		//fake.addAlumno(alumno);
		//assertThat(true, is(new Object()));
	}
	@Test
	public void deleteTest()
	{
		fake.addAlumno(alumno);
		int origSize = fake.getBaseDatos().size();
		
		fake.deleteAlumno(alumno);
		assertThat(fake.getBaseDatos().size(), is(origSize-1));
	}
	@Test
	public void updatePromedioTest()
	{
		fake.addAlumno(alumno);
		float califEsperada = 9.0f;
		//float origGrade = fake.getBaseDatos().get(alumno.getId()).getCalificacion();
		
		fake.updatePromedioAlumno(alumno, 9);
		assertThat(fake.getBaseDatos().get(alumno.getId()).getCalificacion(), is(califEsperada));
		
	}
}
