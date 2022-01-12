package datos;

import java.time.LocalDate;

//clase hija usada para las operaciones periódicas 
public class Tperiodica extends Operaciones{//
	//private int diaf ; // día de la fecha final
	private int mesf ; // mes de la fecha final ,¿ usar un map para conectar las cosas ?
	private int annof;
	private long RepeticionesRestantes;
	public boolean Ingreso;
	private LocalDate finicial ; //fecha inicial
	private LocalDate ultimoM;
	
	

	/**
	 * @param usuario cuenta del usuario
	 * @param contacto cuenta del contacto
	 * @param monto monto de la operacion
	 * @param descripcion descripcion de la operacion
	 * @param ingreso true si es un ingreso
	 * @param mfinal ultimo mes de la transferencia
	 * @param afinal ultimo año de la transferencia
	 */
	public Tperiodica(Cuenta usuario,Cuenta contacto, double monto, String descripcion, boolean ingreso,int mfinal, int afinal,Long repeticionesR,LocalDate ultimoMovimiento) {
		super(usuario,contacto,monto,descripcion);
		this.Ingreso=ingreso;
		this.finicial=LocalDate.now();
		this.mesf=mfinal;
		this.annof=afinal;
		this.RepeticionesRestantes=repeticionesR;
		this.ultimoM=ultimoMovimiento;
	
		
	}
	/**almacena el numero de veces que se va a repetir una operación temporal
	 * @param R numero de repeticions restantes
	 */
	public void setRepeticionesR(long R) {
		this.RepeticionesRestantes=R;
	}
	/**
	 * @return la fecha del ultimo cambio 
	 */
	public LocalDate getUltimoCambio() {
		return this.ultimoM;
	}
	/**
	 * @param ultimo fecha que va a ser guardada como la ultima fecha de cambio
	 */
	public void setUltimoCambio(LocalDate ultimo) {
		this.ultimoM=ultimo;
	}
	/**
	 * @return regresa el numero de veces que se va a repetir una operación temporal
	 */
	public long getRepeticionesR() {
		return this.RepeticionesRestantes;
	}
	public LocalDate getFinicial() {
		return finicial;
	}
	/**
	public int getDFinal() {
		return diaf;
	}
	public void setDfinal() {
		this.diaf=diaf;
	}*/
	public int getMFinal() {
		return mesf;
	}
	public void setMfinal() {
		this.mesf=mesf;
	}
	public int getMesI() {
		return finicial.getMonthValue();
	}
	public int getAFinal() {
		return annof;
	}
	public void setAfinal() {
		this.annof=annof;
	}
	
	public void setOperacion() {
		if (this.Ingreso==true) {
			Ingreso(this);
		}else {
			Egreso(this);
		}
	}
		
	public String toString() {
		if (this.Ingreso==true) {
			return this.getDescripcion()+"==>|Emisor: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: +"+this.getMonto()+"|Fecha de operación: "+this.getFinicial()+"| Fecha fin :"+ LocalDate.of(this.getAFinal(),this.getMFinal(),LocalDate.now().getDayOfMonth());
		}else {
			return this.getDescripcion()+"==>|Receptor: "+this.getCuenta2().getUsuario().getNom()+" "+this.getCuenta2().getUsuario().getApell()+" |Monto: -"+this.getMonto()+"|Fecha de operación: "+this.getFinicial()+"| Fecha fin :"+ LocalDate.of(this.getAFinal(),this.getMFinal(),LocalDate.now().getDayOfMonth());
		}
	}
	
	
	
}
