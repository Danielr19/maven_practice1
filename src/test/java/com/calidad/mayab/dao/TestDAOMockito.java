package com.calidad.mayab.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
//import static org.testing.Assert.assertEquals;
import static org.mockito.ArgumentMatcher.*;

import java.util.HashMap;

//3er test
import static org.mockito.Matchers.*;

import org.mockito.Mockito;
//Crear comportamiento al vuelo
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.*;
public class TestDAOMockito {

	private DAO baseDatos;
	private Alumno alumno;
	
	private DAOFake fake; //Esta linea es la que quisiera no usar 
	@Before
	public void setup()
	{
		baseDatos = mock(DAO.class);
		fake = new DAOFake(); //Esta linea es la que quisiera no usar 
		alumno = new Alumno("Daniel", 20, 8.5f, "00368487@anahuac.mx");
		setupBaseDatos();
		fake.addAlumno(alumno); //Esta linea es la que quisiera no usar 
		
	}
	public void setupBaseDatos() {
		when(baseDatos.getBaseDatos()).thenReturn(new HashMap<Integer, Alumno>());
	}
	
	@Test
	public void testAddAlumno()
	{   
		//Inicio proceso de respuesta pasandole cualquier objeto de tipo Alumno.
		when(baseDatos.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			//Realizo el método interno que se ejucatara cuando se invoque el mock
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Alumno a = (Alumno)invocation.getArguments()[0];
				baseDatos.getBaseDatos().putIfAbsent(a.getId(), a);
				return true;
			}
				
		});
		//verifico que funcione correctamente el método creado
		int size = baseDatos.getBaseDatos().size();
		baseDatos.addAlumno(alumno);
		assertThat(baseDatos.getBaseDatos().size(), is (size+1));
	}
	@Test
	public void testDeleteAlumno()
	{
		when(baseDatos.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			//Realizo el método interno que se ejucatara cuando se invoque el mock
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Alumno a = (Alumno)invocation.getArguments()[0];
				baseDatos.getBaseDatos().putIfAbsent(a.getId(), a);
				return true;
			}
				
		});
		
		when(baseDatos.deleteAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno a = (Alumno)invocation.getArguments()[0];
				baseDatos.getBaseDatos().remove(a.getId());
				return true;
			}
		});
		//CHECAR POR QUÉ NO ESTA FUNCIONANDO EL DELETE O PORQUE SIZE NO ES CORRECTO
		baseDatos.addAlumno(alumno);
		//System.out.println(baseDatos.getBaseDatos().size());
		int size = baseDatos.getBaseDatos().size();
		baseDatos.deleteAlumno(alumno);
		assertThat(baseDatos.getBaseDatos().size(), is(size-1));
		
	}
	@Test
	public void updatePromedioAlumno()
	{
		when(baseDatos.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			//Realizo el método interno que se ejucatara cuando se invoque el mock
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Alumno a = (Alumno)invocation.getArguments()[0];
				baseDatos.getBaseDatos().putIfAbsent(a.getId(), a);
				return true;
			}
				
		});
		
		when(baseDatos.updatePromedioAlumno(Mockito.any(Alumno.class), anyFloat())).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno a = (Alumno)invocation.getArguments()[0];
				float promedioNuevo = (float)invocation.getArguments()[1]; //MAESTRA pregunta del float que le mande en correo
				//baseDatos.getBaseDatos().get(a.getId());
			
				//MAESTRA no se si aquí deba hacer más cosas vi que Esteban en el suyo verifico si existia el alumno y luego actualizó la base de datos con ese id.
				baseDatos.getBaseDatos().get(a.getId()).setCalificacion(promedioNuevo);
				//baseDatos.getBaseDatos().replace(alumno.getId(), promedioNuevo);
				
				return true;
			}
		});  
		baseDatos.addAlumno(alumno);
		float promedioNuevo = 9;
		baseDatos.updatePromedioAlumno(alumno, 9);
		assertThat(alumno.getCalificacion(), is(promedioNuevo));
	}
	
}
