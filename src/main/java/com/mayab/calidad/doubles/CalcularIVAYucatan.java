package com.mayab.calidad.doubles;

public class CalcularIVAYucatan implements CalcularIVA{

	public float getIVA(float porcentaje, float base)
	{
		return base*porcentaje;
	}
}
