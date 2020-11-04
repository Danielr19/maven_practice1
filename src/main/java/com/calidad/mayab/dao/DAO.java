package com.calidad.mayab.dao;

import java.util.HashMap;

//HashMap
public interface DAO {

	boolean addAlumno(Alumno a);
	boolean deleteAlumno(Alumno a);
	boolean updatePromedioAlumno(Alumno a, float nuevoPromedio); //Se podría poner boolean como a los anteriores
	
	int getNumeroAlumnos();
	Alumno getAlumno(int id);
	
	HashMap<Integer, Alumno> getBaseDatos();
	
}
