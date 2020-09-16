package com.mayab.calidad;

public class User {

	private String password;
	
	public User()
	{
		this.password = "";
	}
	public User(String pass)
	{
		this.password = pass;
	}
	public void setPassword(String pass)
	{
		this.password = pass;
	}
	public String getPassword()
	{
		return this.password;
	}
	public boolean ContieneSimb()
	{
		String comprob = "@#$%=:?";
		boolean resultado = false;
		
		for(char c: this.password.toCharArray())
		{
			for(char simb: comprob.toCharArray())
			{
				if(c == simb)
				{
					resultado = true;
				}
			}
		}
		return resultado;
	}
}
