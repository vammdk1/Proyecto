package principal;

import java.awt.Font;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import datos.BaseDatos;
import datos.Cuenta;
import datos.Movimiento;
import datos.Operacione;
import datos.Tperiodica;
import datos.Usuario;
import visual.EntornoG;

public class Main {
	
	private static int alturaIntro=400;//dimenciones de la ventana gráfica
	private static int anchuraIntro=300;
	public	Font confLetra = new Font("Arial",Font.PLAIN,20);//tipo de letra para la ventana Gráfica
	
	public static void main(String[] args) {
		EntornoG v= new EntornoG(alturaIntro,anchuraIntro); // descubrir como usar el scaner para acceder al usuario
		v.setVisible(true);
		
		BaseDatos cargadoDatos= new BaseDatos(null, null);
		Usuario u1 = new Usuario("victor", "martinez",123456,null); //crear el usuario con cuentas en lugar de int
		Usuario u2 = new Usuario("paco", "rodriguez", 654321,null);
		Cuenta c1 = new Cuenta(123987654, 200, u1);
		Cuenta c2 = new Cuenta(123789456, 300, u2);
		RelacionUserCuenta(u1, c1);
		
		//a esta altura vendría el cargado de datos del "servidor" (memoria) 
		cargadoDatos.cargadoDatos();//si funciuona
		
		
		Movimiento m1= new Movimiento(c1, c2, 20, "pago alquiler", false); // habría que preparar un método que elija el tipo de operacion antes de cargar los datos (preferiblemente un botón)
		Movimiento m2= new Movimiento(c1, c2, 30, "pago academia", false); // convertir 
		PasosMovimientos(m1);
		PasosMovimientos(m2);
			
		//prueba de base de datos
		BaseDatos datoUser1 = new BaseDatos(u1,c1);
		System.out.println(datoUser1.getCuentaMovimientos());
		//PasosBDatos(datoUser1,m1);
		//PasosBDatos(datoUser1,m2); // por cada movimiento nuevo hay que hacer un nuevo guardado en la base de datos
		
		System.out.println(u1.getCuetnau());
		//System.out.println(u1.getCuetnau().getSaldo());
	
		
		
		}
		
	//proceso para enlazar un usuario a su cuenta
	public static void RelacionUserCuenta(Usuario u, Cuenta c) {
		if(u.equals(c.getUsuario())) {
			u.setCuentau(c);
		}else {
			System.out.println("Esta cuenta no corresponde al usuario selecionado");
		}
		
	}
	
	//proceso para movimientos no periodicos
	public static void PasosMovimientos(Movimiento m) {
		m.setOperacion();
	}
	//proceso para movimientos periodicos
	public static void PasosTPerioducas(Tperiodica m) {
		m.setOperacion();
	}
	
	public static void PasosBDatos(BaseDatos b, Movimiento m) { // como le puedo mandar varios tipos de movimientos sin cambiar la entrada de datos
		//b.setCuenta();
		b.setMov(m);
		b.setCuentaMovimientos();
		System.out.println(b.getCuentaMovimientos()); //esto lee la lista de movimientos del usuario actual en el app
/**
		System.out.println(b.getCuentas());
			for (Movimiento x : b.getMov()) {
				System.out.println(x.toString());
		}*/
		b.guardado();
	}
}
