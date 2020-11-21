//PRUEBAS DE INTEGRACIÓN
package com.mayab.calidad.dbunit.integration;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mayab.calidad.dao.Alumno;
import com.mayab.calidad.dao.AlumnoDAOSQL;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.dbunit.Assertion;

public class TestAlumnoDAOOracleInsert extends DBTestCase {

	public TestAlumnoDAOOracleInsert(String name)
	{
			super(name);
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver"); //.driver.Or...
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@localhost:1522:xe");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "dbunit");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "dbunit");	
	}
		
	@Before
	public void setUp() throws Exception{
		
		super.setUp();
		IDatabaseConnection con = getConnection();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(con, getDataSet());
		}
		finally {
			con.close();	
		}
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSetBuilder().build(new File("src\\resources\\alumno_init.xml"));
	}
	
	
	
	/*@Test
	public void testGetAverageAge() {
		//Session session = HibernateSessionFactory.getSession();
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(alumno);
	}*/
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	
	@Test
	public void testInsertCount() throws SQLException {
		//Excercise
		//Alumno b = new Alumno(2,"Maria",23,9.8f,"CORREO");
				Alumno a = new Alumno();
				a.setId(2);
				a.setNombre("Maria");
				a.setEdad(23);	
				a.setCalificacion(9.8f);
				a.setEmail("CORREO");
				
				
				AlumnoDAOSQL dao = new AlumnoDAOSQL();
				
				IDatabaseConnection con = null;
				int actualRows = 0;
				try {
					con = getConnection();
					//System.out.println(con.getRowCount("alumno"));
					actualRows =  con.getRowCount("alumno");
					
					dao.addAlumno(a);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				assertEquals(actualRows+1, con.getRowCount("alumno"));
				con.close();
	}
	
	@Test
	public void testinsertAlumnoTest() throws Exception{
		
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(9.8f);
		a.setEmail("CORREO");
		a.setEdad(23);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(a);
		IDataSet databaseDataSet = null;
		ITable actualTable = null;
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		//try(InputStream is = getClass().getClassLoader().getResourceAsStream("dbunit/expected-user.xml")) {
		try {
			//Leemos los datos del archivo esperado
			databaseDataSet = getConnection().createDataSet();
			actualTable = databaseDataSet.getTable("alumno");
			expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("alumno");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	/*@Test
	public void testInsert() {
		//Excercise
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(9.8f);
		a.setEmail("CORREO");
		a.setEdad(23);	
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(a);
		
		//Verify
				//Fetch database data after executing your code
				try{
					//IDataSet databaseDataSet = getConnection().createDataSet();
					//ITable actualTable = databaseDataSet.getTable("alumno");
					//Leemos los datos del archivo esperado
					
					ITable actualTable = (ITable)getDataSet();
					
					//InputStream xmlFile =  getClass().getResourceAsStream("/insert_result.xml");
					//IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
					IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
					ITable expectedTable = expectedDataSet.getTable("alumno");
					// Assert actual database table match expected table
					Assertion.assertEquals(expectedTable, actualTable);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
	}*/
	
	
	@Test
	public void testDeleteCount() throws SQLException{
		
		//<ALUMNO ID='1' NOMBRE='Juan' CALIFICACION='8.6' EMAIL='correoJuan@correo' EDAD='32'/>
		Alumno a = new Alumno();
		a.setId(1);
		a.setNombre("Juan");
		a.setCalificacion(8.6f);
		a.setEmail("correoJuan@correo");
		a.setEdad(32);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		
		IDatabaseConnection con = null;
		int actualRows = 0;
		try {
			con = getConnection();
			actualRows = con.getRowCount("alumno");
			dao.removeAlumno(a);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(actualRows-1, con.getRowCount("alumno"));
		con.close();
	}
	
	@Test
	public void testUpdatePromedio() throws DatabaseUnitException, SQLException {
		
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(8.0f);
		a.setEmail("CORREO");
		a.setEdad(23);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(a);
		
		IDataSet databaseDataSet = null;
		ITable actualTable = null;
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		IDatabaseConnection con = null;
		
		try {
			con = getConnection();
			dao.updatePromedio(a, 9.8f);
			databaseDataSet = getConnection().createDataSet();
			actualTable = databaseDataSet.getTable("alumno");
			
			//Leemos los datos del archivo esperado
			expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("alumno");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Assertion.assertEquals(expectedTable, actualTable);
		con.close();
	}
	
	
	@Test
	public void testGetAll() throws DataSetException {
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(9.8f);
		a.setEmail("CORREO");
		a.setEdad(23);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(a);
		ITable actualTable = null;
		ArrayList<Alumno> actualAlumnos = new ArrayList<Alumno>();
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		try {
			actualAlumnos = dao.getAll();
			expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("alumno");
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + "Esta es una excepción");
		}
		
		for(int i = 0; i < expectedTable.getRowCount(); i++)
		{
			assertEquals(expectedTable.getValue(i, "id"), Integer.toString(actualAlumnos.get(i).getId()));
			assertEquals(expectedTable.getValue(i, "nombre"), actualAlumnos.get(i).getNombre());
			assertEquals(expectedTable.getValue(i, "calificacion"), Float.toString(actualAlumnos.get(i).getCalificacion()));
			assertEquals(expectedTable.getValue(i, "email"), actualAlumnos.get(i).getEmail());
			assertEquals(expectedTable.getValue(i, "edad"), Integer.toString(actualAlumnos.get(i).getEdad()));
		}
	}
	
	@Test
	public void testGetNumeroAlumnos() throws SQLException {
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(9.8f);
		a.setEmail("CORREO");
		a.setEdad(23);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		int actualCount = 0;
		IDatabaseConnection con = null;
		try {
			con = getConnection();
			actualCount = dao.getNumeroAlumnos();
			dao.addAlumno(a);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(actualCount+1, con.getRowCount("alumno"));
		con.close();
	}
	
	@Test
	public void testGetAlumno() throws DataSetException {
		
		Alumno a = new Alumno();
		a.setId(2);
		a.setNombre("Maria");
		a.setCalificacion(9.8f);
		a.setEmail("CORREO");
		a.setEdad(23);
		
		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		dao.addAlumno(a);
		Alumno actualAlumno = new Alumno();
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		try {
			actualAlumno = dao.getAlumno(2);
			expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("alumno");
			
			//Verificamos con el alumno creado gracias al metodo getAlumno que sus parametros sean iguales a los esperados segun el xml
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(expectedTable.getValue(1, "id"), Integer.toString(actualAlumno.getId()));
		assertEquals(expectedTable.getValue(1, "nombre"), actualAlumno.getNombre());
		assertEquals(expectedTable.getValue(1, "calificacion"), Float.toString(actualAlumno.getCalificacion()));
		assertEquals(expectedTable.getValue(1, "email"), actualAlumno.getEmail());
		assertEquals(expectedTable.getValue(1, "edad"), Integer.toString(actualAlumno.getEdad()));
	}
}