package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class BaseDatos implements Serializable {
	
	private Usuario UX = new Usuario("Vacio", "Vacio", "Vacio", null);
	private  TreeMap<String, Usuario> cUsuarios = new TreeMap<>(); //cusuaros.get(clave); 

	public Usuario getUsuario(String clave) {
		return cUsuarios.get(clave);
	}
	
	public TreeMap<String, Usuario> getCusuarios(){
		return cUsuarios;
	}
	
	public void addUsuario(Usuario u) {
		cUsuarios.put(u.getPin(),u);
	}
	
	public Usuario CompruebaUsuarios(String key) {
		if(cUsuarios.get(key)!=null) {
			return cUsuarios.get(key);
		}else {
			return UX;
		}
		
		
	}
	
	public void setMov(Operaciones mov, Usuario u) {
		u.getCuentaUsuario().setOperacion(mov);
	}
		
	/**
	 * @return regresa todos los movimientos realizados por un usuario
	 */
	public ArrayList<Operaciones> getCuentaMovimientos(Cuenta c) {
		return c.getOperaciones();
	}
	public void guardado () {
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("cUsuarios.users"));
			salida.writeObject(cUsuarios);
			salida.close();
		}catch (Exception e) {
			System.out.println("Error guardando los usuarios");
		}
	}
	public void cargadoDatos() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("cUsuarios.users"));
			cUsuarios=(TreeMap<String, Usuario>) entrada.readObject();	
			entrada.close();
		}catch (Exception e) {
			System.out.println("Error al cargar los usuarios");
			//return null;
		}
	}
	public void ListaUsuarios() {
		System.out.println(cUsuarios);
	}

	public void CargadoManual(){
	//este main se usa para cargar la base de datos sin usar el programa principal
		Usuario u1 = new Usuario("victor", "martinez","123456",null); //crear usuario
		Usuario u2 = new Usuario("paco", "rodriguez", "654321",null);
		Cuenta c1 = new Cuenta(987654321, 200, u1,null, true ); //crear cuenta
		Cuenta c2 = new Cuenta(123789456, 300, u2,null, true);
		RelacionUserCuenta(u1, c1);//relacionar usuario con cuenta
		RelacionUserCuenta(u2, c2);
		this.addUsuario(u1); // guarda usuarios
		this.addUsuario(u2);
		this.ListaUsuarios(); 
		this.guardado();
		
	}
	//proceso para enlazar un usuario a su cuenta
	public static void RelacionUserCuenta(Usuario u, Cuenta c) {
		if(u.equals(c.getUsuario())) {
			u.setCuentau(c);
			u.getCuentaUsuario().setOperaciones(null);
		}
	}
	
	public void PasosMovimientos(Movimiento m,Tperiodica t) {
		if(m!=null) {
			m.setOperacion();	
		}else if (t!=null) {
			t.setOperacion();
		}	
	}
	//proceso para movimientos periodicos
	public static void PasosTPerioducas(Tperiodica m) {
		m.setOperacion();
	}
	
	public void PasosBDatos(Operaciones m1, Operaciones m2,Usuario u1,Usuario u2, BaseDatos bd) {
		bd.setMov(m1,u1); // establece movimientos en la lista de movimientos temporales
		bd.setMov(m2,u2);
		//bd.setCuentaUsuarioMovimientos(u1.getCuentaUsuario());// conecta los movimientos con los las cuentas
		bd.guardado();// guarda los datos
	}
	public void PasosComprobacion(Operaciones m1, Operaciones m2,Usuario u1,Usuario u2, BaseDatos bd) {
		bd.setMov(m1,u1); // establece movimientos en la lista de movimientos temporales
		bd.setMov(m2,u2);
	}
}
