package datos;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String nombre;
	private String apellido;
	private String pin;
	private Cuenta cuentaU;
	
	public Usuario (String nombre, String apellido,String pin,Cuenta cuenta) {
		this.nombre=nombre;//convertir el nombre y apellido en un solo campo ?
		this.apellido=apellido;
		this.pin=pin;
		this.cuentaU=cuenta;
	}
	public void setNom() {
		this.nombre=nombre;
	}
	public String getNom() {
		return nombre;
	}
	public void setApell() {
		this.apellido=apellido;
	}
	public String getApell() {
		return apellido;
	}
	public void setPin() {
		this.pin=pin;
	}
	public String getPin() {
		return pin;
	}
	public void setCuentau(Cuenta c) {
		if(this.cuentaU==null) {
			this.cuentaU=c;
		}else {
			this.cuentaU=cuentaU;
		}
	}
	public Cuenta getCuentaUsuario() {
		return cuentaU;
	}
	
	public String toString() {
		return "Usuario: "+this.nombre+" "+ this.apellido+" |Pin: "+this.pin +" |Nº de cuenta :"+this.getCuentaUsuario().getNcuenta();
	}
	

}
