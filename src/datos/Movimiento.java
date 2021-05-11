package datos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Movimiento extends Operacione {
	
	private LocalDate fecha;
	public boolean Ingreso;
	
	/**
	 * @param usuario cuenta del usuario que usa la app
	 * @param contacto cuenta del concato que interactua con el usuario 
	 * @param monto	monto de la operación
	 * @param descripcion	texto que describe la operación
	 * @param Ingreso Si es un ingreso se pone true, si es un egreso es falso
	 */
	public Movimiento(Cuenta usuario,Cuenta contacto, double monto, String descripcion, boolean ingreso){ //coordinar el tipo de movimiento en main 
		super(usuario,contacto,monto,descripcion);
		this.Ingreso=ingreso;
	}
		
	public void setOperacion() {
		if (this.Ingreso==true) {
			this.Ingreso();
		}else {
			Egreso();
		}
	}
	
	public String toString() { // regresa el string de la operación
		if (this.Ingreso==true) {
			return "Ingreso: "+this.getDescripcion()+"==>|Contacto: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: +"+this.getMonto()+" |Fecha: "+LocalDate.now();
		}else {
			return "Egreso: "+this.getDescripcion()+"==>|Receptor: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: -"+this.getMonto()+" |Fecha: "+LocalDate.now();
		}
	}
	
	
	
	
	

	
}
