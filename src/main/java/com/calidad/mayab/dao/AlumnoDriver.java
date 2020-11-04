package com.calidad.mayab.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AlumnoDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int id, String nombre, int edad, float calificacion, String email

		AlumnoDAOSQL dao = new AlumnoDAOSQL();
		
		dao.getConnectionORACLE();
		
		Alumno alumno = new Alumno(123, "DanielRod", 25, 7.2f, "emailDanRod");
		Alumno alumno2 = new Alumno(23, "DSAW", 32, 8.6f, "correo2");
		Alumno ab = new Alumno(1,"Pablo", 9, 7.5f, "Correo@Pablo");
		
		//dao.addAlumno(alumno);
		//dao.addAlumno(alumno2);
		
		//dao.updatePromedio(alumno, 200232.0f);
		//dao.removeAlumno(alumno2); //pasar id PK
		//dao.addAlumno(alumnoUpdate);
		//dao.updatePromedio(alumnoUpdate, 9.5f);
		//dao.addAlumno(ab);
		//dao.addAlumno(alumno);
		//dao.addAlumno(alumno2);
		
		//System.out.println(dao.getAlumno(3));
		
		ArrayList<Alumno> all = dao.getAll();
		System.out.println(all);
		 //ResultSet rs = dao.getAllResultSet();
		//System.out.println(rs);
		 //System.out.println(dao.getNumeroAlumnos());
		//dao.removeAlumno(alumnoDelete);
		//Alumno resultado = dao.getAlumno(123);
		 //System.out.println(dao.getAlumno(123));
	}

}
