package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


//crear un constructor para las cuentas y los datos necesarios
public class Cuenta implements Serializable{
	
	private long numero;
	private double saldo;
	private Usuario User;
	
	public  Cuenta(int numero,int dinero,Usuario usuario) {
		this.numero=numero;
		this.saldo=dinero;
		this.User=usuario;
	}
	
	public Usuario getUsuario() {
		return User;
	}
	
	public long getNcuenta() {
			return numero;
	}

	public void setNcuenta() {//crea una cuenta nueva con 9 numeros random y lo almacena en el array
		StringBuffer convertidor = new StringBuffer();
		for (int x = 0; x<9;x++) {
			int nrandom =(int)Math.floor(Math.random()*(9-0+1)+0);
			convertidor.append(nrandom);
		}
		int Cnumero = Integer.parseInt(convertidor.toString());
		this.numero=Cnumero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double monto) {
		this.saldo=monto;
	}
		
	public String toString() { // regresa el string de las cuentas
		return "Cuenta: "+this.numero+" | saldo: "+this.saldo+" || Propietario: "+ this.User.getNom()+" "+this.User.getApell() ;
	} 

	
	
	
}
