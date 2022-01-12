package datos;

import java.io.Serializable;
import java.util.ArrayList;



//crear un constructor para las cuentas y los datos necesarios
public class Cuenta implements Serializable{
	
	private long numero;
	private double saldo;
	private Usuario User;
	private ArrayList<Operaciones> operacionesCuenta;
	private boolean cNueva;
	
	public  Cuenta(long numero,int dinero,Usuario usuario,ArrayList<Operaciones> Loperaciones, boolean nueva) {
		this.numero=numero;
		this.saldo=dinero;
		this.User=usuario;
		this.operacionesCuenta=Loperaciones;
		this.cNueva=nueva;
	}
	
	public Usuario getUsuario() {
		return User;
	}
	
	/**
	 * va a cargar la lista de movimientos con la lista de memoria o crear un lista nueva
	 * @param OP lista de operaciones
	 * @param nueva si es "true" va a crear un arraylist nuevo para la cuenta
	 */
	public void setOperaciones(ArrayList<Operaciones> OP) {
		if(cNueva==true) {
			this.operacionesCuenta= new ArrayList<>();
		}else {
			this.operacionesCuenta=OP;			
		}
	}
	
	public ArrayList<Operaciones> getOperaciones(){
		return operacionesCuenta;
	}
	
	public void setOperacion(Operaciones OP) {
		//System.out.println("setOperacion"+this.operacionesCuenta+"<--"+OP);
		this.operacionesCuenta.add(OP);
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
