package com.mayab.calidad.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
public class AlumnoDAOSQL implements AlumnoDAO{

	/*public Connection getConexionSQL(){
		
		String url = "jdbc"
	}*/
	public Connection getConnectionORACLE(){  

        
		/**	
		create table user905( 
		id number(10) not null,
		name varchar2(4000),
		password varchar2(4000),
		email varchar2(4000),
		country varchar2(4000)
		);
		*/
        Connection con=null;  
        try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            //antes era hr y 123
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","dbunit","dbunit");  
            
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }
	
	/*CREATE TABLE ."ALUMNOS" 
	   (	
		"ID" NUMBER(*, 0), 
		"NOMBRE" VARCHAR2(20 BYTE), 
		"CALIFICACION" FLOAT(126), 
		"EMAIL" VARCHAR2(20 BYTE), 
		"EDAD" NUMBER(*,0)
	   );*/

	public void addAlumno(Alumno alumno) {
		try{
			Connection con = getConnectionORACLE();
		PreparedStatement ps = con.prepareStatement("insert into alumno(id, nombre, calificacion, email, edad) values (?,?,?,?,?)");
		//PreparedStatement ps = con.prepareStatement("insert into tablamaestra(id, first_name, last_name) values (?,?,?)");

		ps.setInt(1, alumno.getId());
		ps.setString(2,alumno.getNombre());
		ps.setFloat(3, alumno.getCalificacion());
		ps.setString(4,alumno.getEmail());
		ps.setInt(5, alumno.getEdad());
		
		int status = ps.executeUpdate(); //1 si hizo algo 2 si no hizo nada.
		con.close();
		
	}catch(Exception ex) {
		ex.printStackTrace();}
	}
	
	public void removeAlumno(Alumno alumno) {
		try {
			Connection con = getConnectionORACLE();
			PreparedStatement ps = con.prepareStatement("DELETE FROM alumno WHERE id = ?");
			
			//ps.setString(1, Integer.toString(alumno.getId()));
			ps.setString(1, String.valueOf(alumno.getId()));
			int status = ps.executeUpdate();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();}
	}
		
	public void updatePromedio(Alumno alumno, float nuevoPromedio){
		try {
			Connection con = getConnectionORACLE();
			PreparedStatement ps = con.prepareStatement("UPDATE alumno SET calificacion = ? WHERE id = ?");
			
			//PreparedStatement ps2 = con.prepareStatement("COMMIT;");
			 //Statement ps = con.createStatement();
			//ps.execute("UPDATE alumno SET calificacion = 10 WHERE id = 123");

			//ps.setString(1, Float.toString(nuevoPromedio));
			//ps.setString(2, Integer.toString(alumno.getId()));
			ps.setFloat(1,(nuevoPromedio));
			ps.setString(2,String.valueOf(alumno.getId()));
			
			int status = ps.executeUpdate();
			//int status2 = ps2.executeUpdate();
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*public void getAll() {
		try {
			Connection con = getConnectionOracle();
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM alumno");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	 @Override
	 public ArrayList<Alumno> getAll() {
	 	ArrayList<Alumno> allAlumnos = new ArrayList<Alumno>();
	 	int numFilas = 0;
        
        try {
        	Connection con = getConnectionORACLE();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumno");
            rs.last();
            numFilas = rs.getRow();
            rs.beforeFirst();
            
            for(int i = 0; i < numFilas; i++) {
            	allAlumnos.add(new Alumno());
            }
            
            for(int i = 0; i < numFilas; i ++) {
            	if(rs.next())
            	{
            	allAlumnos.get(i).setId(rs.getInt("ID"));
            	allAlumnos.get(i).setNombre(rs.getString("NOMBRE"));
            	allAlumnos.get(i).setCalificacion(rs.getFloat("CALIFICACION"));
    			allAlumnos.get(i).setEmail(rs.getString("EMAIL"));
    			allAlumnos.get(i).setEdad(rs.getInt("EDAD"));
            	}
        	}
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allAlumnos;

	   }
	
	public int getNumeroAlumnos() {
		
		int count = 0;
		try {
			Connection con = getConnectionORACLE();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM alumno");
			
			while(rs.next()){
			count = rs.getInt("count");
			}

			con.close();
			
			//return ps.getFetchSize();
			//return ps.getResultSet();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	public Alumno getAlumno(int id) {
		//Alumno a = new Alumno(2,"nombre", 19, 8.5f, "email");
		//return  a;
		Alumno a = new Alumno();
		
		try {
			Connection con = getConnectionORACLE();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM alumno WHERE id = ?");
			//ps.setInt(1, id);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM alumno WHERE id = " + id);
			while(rs.next()) {
				a.setId((int)rs.getInt("ID"));
				a.setNombre(rs.getString("NOMBRE"));
				a.setCalificacion((Float)rs.getFloat("CALIFICACION"));
				a.setEmail(rs.getString("EMAIL"));
				a.setEdad((int)rs.getInt("EDAD"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	public HashMap<Integer, Alumno> getBaseDatos()
	{
		HashMap<Integer, Alumno> hm = new HashMap<Integer, Alumno>();
		return hm;
	}

	
}
