package com.calidad.mayab.dao;

import java.util.HashMap;
public class DAOFake implements DAO {

	private HashMap<Integer, Alumno> baseDatos = new HashMap<Integer, Alumno>();
	
	@Override
	public boolean addAlumno(Alumno a) {
		baseDatos.put(a.getId(),a);
		return true;
	}
	@Override
	public boolean deleteAlumno(Alumno a) {
		baseDatos.remove(a.getId());
		return true;
	}
	@Override
	public boolean updatePromedioAlumno(Alumno a, float nuevoPromedio) {
		this.baseDatos.get(a.getId()).setCalificacion(nuevoPromedio);
		return true;
	}
	@Override
	public int getNumeroAlumnos() {
		return baseDatos.size();
	}
	
	@Override
	public Alumno getAlumno(int id) {
		return baseDatos.get(id);
	}
	
	@Override	
	public HashMap<Integer, Alumno> getBaseDatos(){
		return this.baseDatos;
	}
}
