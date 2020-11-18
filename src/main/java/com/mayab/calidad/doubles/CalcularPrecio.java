package com.mayab.calidad.doubles;

public class CalcularPrecio {

	public float precioProducto;
	public float porcentaje;
	public CalcularIVA formulaIVA;
	
	public CalcularPrecio()
	{
		this.precioProducto = 100;
		this.porcentaje = .16f;
		
	}
	public float Calcular(CalcularIVA formula) {
		//formulaIVA = new CalcularIVAYucatan();
		formulaIVA = formula;
		float iva = formulaIVA.getIVA(porcentaje, precioProducto);
		return iva + precioProducto;
	}
}
