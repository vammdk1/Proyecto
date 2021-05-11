package Descartar;
import java.util.ArrayList;
import java.util.Arrays;
// esta clase va a crear los números de cuenta nuevos y almacenar los movimientos que se produzcan en las mismas
import java.util.Random;

public class Cuentas {
	public static ArrayList<Integer> ncuenta = new ArrayList<>(Arrays.asList(123456789,987654321)) ; //almacena los numeros de cuenta(de momento solo se van a alamacenar 10
	private double[] Fondos= new double [10];//FIXME no consigo almacenar los cambios de forma permanente
	private String[] Movimientosglobales = new String [10];//almacena los movimintos de todas las cuentas(la prueba contiene un máximo de 10)
	private String[] Movimientoslocales = new String [10];//almacena los movimientos de una cuenta(la prueba contiene un máximo de 10)
	private String Movimiento;
	private String[] Prueba= {"Carlos -- juan-- 12","pepe--carlos--23"};
	private int posicion;//permite controlar las posiciones de las cuentas
	
	
	/**
	 * @param Usuario Selecciona el usuario del cual se van a realizar acciones
	 */
	public Cuentas () {//va a cargar los datos del usuario
		this.ncuenta=ncuenta;
		this.Fondos=Fondos;
		this.Movimiento=Movimiento;
	}
	
	/**
	 * @param i posición del usuario en el array
	 * @return regresa el numero de cuenta
	 */
	public int getNcuenta(int i) {
			return ncuenta.get(i);
	}

	/**
	 * @param crea un numero de cuenta en la ultima posición
	 */
	public void setNcuenta() {//crea una cuenta nueva con 9 numeros random y lo almacena en el array
		StringBuffer convertidor = new StringBuffer();
		for (int x = 0; x<9;x++) {
			int nrandom =(int)Math.floor(Math.random()*(9-0+1)+0);
			convertidor.append(nrandom);
		}
		int numero = Integer.parseInt(convertidor.toString());
		
		
		ncuenta.add(numero);
		
		
	}
	
	public double getFondos(int i) {
		return Fondos[i];
	}
	
	/**
	 * @param i posición de los fondos en el array
	 * @param Frestantes monto final después de una operación
	 */
	public void setFondos(int i,double Frestantes) {
		this.Fondos[i]=Frestantes;
		Fondos[i]=this.Fondos[i];
		//System.out.println(Fondos[i]);
	}
	/**
	 * @param i posición de la lista de movimientos en el array
	 * @return regresa la lista de movimientos del usuario en la posición i
	 */
	public String getMovimientosLocales(int i) {
			return Movimientosglobales[i];
	}
	/**
	 * @param i numero de la posición en el array
	 * este set sirve para agregar un movimiento a la lista de movimientos de una cuenta
	 */
	public void setMovimiento(int i, String movimiento) {//cuenta origen, cuenta destino, monto
		if(this.Movimientoslocales[i]!=null) {	
			int tam= Movimientoslocales[i].length();
		String[] Moviplus= new String[tam+1];
	
		for(int x=0;x<tam;x++) {
			Moviplus[x]=Movimientoslocales[i];
		}
		Moviplus[tam]=Movimiento;
		Movimiento=Arrays.toString(Moviplus);
		
		this.Movimientosglobales[i]= Movimiento;
		}else {
			//System.out.println("paso1");
			this.Movimientosglobales[i]= movimiento;
		}
	}
	
	public int getPosicion(String receptor) {
		posicion= Arrays.asList(ncuenta).indexOf(receptor);
		return posicion;
	}
	
	public String toString(int i, int x, int fondos) { // regresa el string de la operación
		return "Emisor:"+this.getNcuenta(i)+"--Receptor:"+this.getNcuenta(x)+"--Fondos finales:"+this.getFondos(fondos);
	}
	public static void main(String[] args) {
		Cuentas c1 = new Cuentas();
		c1.setNcuenta();
		System.out.println(ncuenta);
	}

}
