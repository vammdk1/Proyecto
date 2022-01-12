package principal;

import java.awt.Font;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import datos.BaseDatos;
import datos.Cuenta;
import datos.Movimiento;
import datos.Operaciones;
import datos.Tperiodica;
import datos.Usuario;
import visual.InicioSesión;
import visual.SesionUsuario;

public class Main implements Serializable{
	
	private static int alturaIntro=300;//dimenciones de la ventana gráfica
	private static int anchuraIntro=400;
	public	Font confLetra = new Font("Arial",Font.PLAIN,20);//tipo de letra para la ventana Gráfica
	private static BaseDatos guardadoBaseDatos = new BaseDatos();
	
	public static void main(String[] args) {
//1-Cargado de datos antiguos en la aplicación-------------------
		//TODO leer esto
		//guardadoBaseDatos.CargadoManual(); //esta linea se ejecuta para crear y/o sobreescribir 2 usuarios creados
		
		guardadoBaseDatos.cargadoDatos();
		guardadoBaseDatos.ListaUsuarios();
		
//2-Aparición de la primera ventana y autentificación-----------	
		InicioSesión v= new InicioSesión(alturaIntro,anchuraIntro,guardadoBaseDatos); //autentificación confirmada
		v.setVisible(true);
		}
}
