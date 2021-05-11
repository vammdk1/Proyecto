package Descartar;

import java.util.Arrays;

//clase encargada de almacenar los datos de los usuarios con sus respectivas contraseñas
public class Usuarios {
	//juntar los usuarios y las cuentas en un fichero
	private String[] users= {"Victor","Admin"};
	private String[] contr= {"ABCDEF","1"};
	private int posicion;

	
	public Usuarios() {
		this.users=users;
		this.contr=contr;
		//this.posicion=posicion;
		
	}
	
	public int getPosicion(String usuario) {
		posicion= Arrays.asList(users).indexOf(usuario);
		return posicion;
	}
	
	public String getUser(int i) {
		return users[i];
	}
	
	public String getContrasena(int i) {
		return contr[i];
	}
	
	public String toString(int i) {
			return this.getUser(i)+" "+this.getContrasena(i);
	}
	
	public static void main(String[] args) {
		
	}
}
