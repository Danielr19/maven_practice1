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
		//Alumno ab = new Alumno(1,"Pablo", 9, 7.5f, "Correo@Pablo");
		
		dao.addAlumno(alumno);
		dao.addAlumno(alumno2);
		
		dao.updatePromedio(alumno, 200232.0f);
		dao.removeAlumno(alumno2); 
		
		
		ArrayList<Alumno> all = dao.getAll();
		System.out.println(all);
		 
	}

}
