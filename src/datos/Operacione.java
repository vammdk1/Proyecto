package datos;

import java.io.Serializable;
import java.util.ArrayList;

// Se podría usar esta clase como alamcén de operaciones
// esta clase se va a encargar de procesar las operaciones 
// atomizar los movimientos

public abstract class Operacione implements Serializable{
	
	private Cuenta cuenta1;
	private Cuenta cuenta2;
	private double monto;
	private String descripcion;
		
	public Operacione(Cuenta usuario ,Cuenta contacto, double monto, String descripcion) {
		this.cuenta1= usuario;
		this.cuenta2 = contacto;
		this.monto = monto;
		this.descripcion = descripcion;
	}
	public Cuenta getCuenta1() {
		return cuenta1;
	}
	public void setCuenta1() {
		this.cuenta1=cuenta1;
	}

	public Cuenta getCuenta2() {
		return cuenta2;
	}
	public void setCuenta2() {
		this.cuenta2=cuenta2;
	}

	public double getMonto() {
		return monto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion() {
		this.descripcion=descripcion;
	}

	public void setMontos(double fondos) {
		this.monto = monto;
	}

	public void Ingreso() {// ingresos a la cuenta, creo que esta sobra
		cuenta1.setSaldo(this.cuenta1.getSaldo()+this.monto);
		cuenta2.setSaldo(this.cuenta2.getSaldo()-this.monto);	
	}
	public void Egreso() {// cobros a la cuenta	
		cuenta2.setSaldo(this.cuenta2.getSaldo()+this.monto);
		cuenta1.setSaldo(this.cuenta1.getSaldo()-this.monto);
	}
	

	
}
