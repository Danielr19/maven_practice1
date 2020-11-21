package com.mayab.calidad.dao;
//DAO Data Acces Object
//COMENTARIO PARA PRUEBA #1
public class Alumno {
	private int id; //tal vez static
	private String nombre;
	private int edad;
	private float calificacion;
	private String email;
	
	public Alumno(int id, String nombre, int edad, float calificacion, String email)
	{
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.calificacion = calificacion;
		this.email = email;
	}
	public Alumno() {
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ID: "+ this.id + ", nombre: " + this.nombre + ", calificacion: " + this.calificacion + ", email: " + this.email + ", edad: " + this.edad;
	}
}
