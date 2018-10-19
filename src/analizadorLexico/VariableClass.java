/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

import java.util.ArrayList;
/**
 *
 * @author naviles
 */
public class VariableClass {
	String Nombre;
	String Valor;
	String Tipo;
	
	public VariableClass(String nombre, String valor, String tipo) {
		super();
		Nombre = nombre;
		Valor = valor;
		Tipo = tipo;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getValor() {
		return Valor;
	}
	public void setValor(String valor) {
		Valor = valor;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	public static VariableClass BuscarVariable(ArrayList<VariableClass> list, String Nombre) {
		
			  for(VariableClass t : list) {
		
			     if(t.getNombre().equalsIgnoreCase(Nombre)) { return t; }
		
			   }
		
			   return null;
		
			}
			
}	