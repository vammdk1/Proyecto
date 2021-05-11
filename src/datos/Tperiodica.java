package datos;

import java.time.LocalDate;

//clase hija usada para las operaciones periódicas 
public class Tperiodica extends Operacione{//
	private int dia ; // día de la fecha final
	private int mes ; // mes de la fecha final ,¿ usar un map para conectar las cosas ?
	private int anno;
	public boolean Ingreso;
	private LocalDate finicial ; //fecha inicial
	
	

	/**
	 * @param usuario cuenta del usuario
	 * @param contacto cuenta del contacto
	 * @param monto monto de la operacion
	 * @param descripcion descripcion de la operacion
	 * @param ingreso true si es un ingreso
	 * @param dfinal ultimo dia de la transferencia
	 * @param mfinal ultimo mes de la transferencia
	 * @param afinal ultimo año de la transferencia
	 */
	public Tperiodica(Cuenta usuario,Cuenta contacto, double monto, String descripcion, boolean ingreso,int dfinal,int mfinal, int afinal) {
		super(usuario,contacto,monto,descripcion);
		this.finicial=LocalDate.now();
		this.dia=dfinal;
		this.mes=mfinal;
		this.anno=afinal;
		
	}
	public LocalDate getFinicial() {
		return finicial;
	}
	
	public int getDFinal() {
		return dia;
	}
	public void setDfinal() {
		this.dia=dia;
	}
	public int getMFinal() {
		return mes;
	}
	public void setMfinal() {
		this.mes=mes;
	}
	public int getAFinal() {
		return anno;
	}
	public void setAfinal() {
		this.anno=anno;
	}
	
	public void setOperacion() {
		if (this.Ingreso==true) {
			this.Ingreso();
		}else {
			Egreso();
		}
	}
		
	public String toStringIngresoP() {
		if (this.Ingreso==true) {
			return "Ingreso: "+this.getDescripcion()+"==>|Contacto: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: +"+this.getMonto()+"|Fecha de inicio: "+this.getFinicial()+"| Fecha fin :"+ LocalDate.of(this.getAFinal(),this.getMFinal(),this.getDFinal());
		}else {
			return "Egreso: "+this.getDescripcion()+"==>|Contacto: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: -"+this.getMonto()+"|Fecha de inicio: "+this.getFinicial()+"| Fecha fin :"+ LocalDate.of(this.getAFinal(),this.getMFinal(),this.getDFinal());
		}
	}
	
	
	
}
