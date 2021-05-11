package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class BaseDatos implements Serializable {
	private Usuario user;
	private Cuenta cuenta;
	private Movimiento mov;
	
	private ArrayList<Movimiento> movTemporales = new ArrayList<>(); //cargar esta lista con todos los datos anteriores al principio de la sesión y descargarla en memoria al terminar
	//private ArrayList<Cuenta> lcuentas = new ArrayList<>(); las cuentas ahora se guardan con el usuario, creo que no hace falta guardarlas por separado
	private  TreeMap<String, Usuario> cUsuarios = new TreeMap<>(); //cusuaros.get(clave); 
	private  TreeMap<Long, ArrayList<Movimiento>> cMov= new TreeMap<>(); //si les quito el estatic, me dan problemas

	//cambiar la lista de movimiento para que almacene operaciones --> y que a su vez cambiar tos los usos de movimientos a operaciones
	
	/**
	 * @param Nuser datos del usuario a almacenar
	 * @param Dcuenta datos de la cuenta a almacenar
	 */
	public BaseDatos(Usuario Nuser,Cuenta Dcuenta/** ,Movimiento operacion*/){
		this.user=Nuser;
		this.cuenta=Dcuenta;
		//this.mov=operacion;
	}
	/**
	public void setCuenta() {
		this.lcuentas.add(this.cuenta);
	}
	public ArrayList<Cuenta> getCuentas(){
		return lcuentas;
	}*/
	/**
	 * @param mov movimiento que se va a guardar
	 */
	public void setMov(Movimiento mov) {
		this.movTemporales.add(mov);
	}
	/**
	 * @return regresa lista de movimientos temporales del usuario
	 */
	public ArrayList<Movimiento> getMov(){
		return movTemporales;
	}
	
	/**
	 * conecta los movimientos realizados por un usuario con su cuenta
	 */
	public void setCuentaMovimientos() {
		cMov.put(this.cuenta.getNcuenta(), movTemporales);
	}
	/**
	 * @return regresa todos los movimientos realizados por un usuario
	 */
	public ArrayList<Movimiento> getCuentaMovimientos() {
		return cMov.get(this.cuenta.getNcuenta());
	}
	public static void guardado () {
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("cUsuarios.users"));
			salida.writeObject(cUsuarios);
			salida.close();
		}catch (Exception e) {
			System.out.println("Error guardando los usuarios");
		}
		try{
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("cMov.cuentas"));
			salida.writeObject(cMov);
			salida.close();
		}catch (Exception e) {
			System.out.println("Error guardando las cuentas");
		}
	}
	public static void cargadoDatos() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("cUsuarios.users"));
			cUsuarios=(TreeMap<String, Usuario>) entrada.readObject();	
			entrada.close();
		}catch (Exception e) {
			System.out.println("Error al cargar los usuarios");
			//return null;
		}
		try {
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("cMov.cuentas"));
			cMov=(TreeMap<Long, ArrayList<Movimiento>>) entrada.readObject();	
			entrada.close();
		}catch (Exception e) {
			System.out.println("Error al cargar las cuentas");
			//return null;
		}
	}

	
}
