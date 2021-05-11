package Descartar;
import datos.*;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Scanner;

import visual.VentanaGrafica;

//Programa principaa
public class Main {
	private static int ejex=400;//dimenciones de la ventana gráfica
	private static int ejey=600;
	private static int centrox=ejex/2;
	private static int centroy=ejey/2;
	public static  Font confLetra = new Font("Arial",Font.PLAIN,20);//tipo de letra para la ventana Gráfica
	private static int posicionUser1=1;
	private static int posicionUser2=0;
	

	
	//clase principal
	public static void main(String[] args) {
		Usuarios user1= new Usuarios();
		Cuentas receptor = new Cuentas();
		Cuentas Emisor = new Cuentas();
		Scanner in = new Scanner(System.in);
		/**while(true) {// loop infinito para la autentificación, si los datos no son correctos nunca se pasará de este punto
			
					System.out.println("Introduce usuario: ");
			String comprobacion= in.nextLine();
			if(user1.getPosicion(comprobacion)!=-1) {
				System.out.println("Introduce contraseña: ");
				Scanner in2 = new Scanner(System.in);
				String comCont= in.nextLine();//comprueba la contraseña
				if(comCont.equals(user1.getContrasena(user1.getPosicion(comprobacion)))) {
					////////////////////////////////////////////comienza el uso del programa///////////////////////////////
					posicionUser=user1.getPosicion(comprobacion);// campo de control general, todos los datos de este programa se relacionan 
					//a travez de su posición en un array, es decir, todos los datos del usuario "Victor" se van a encontrar en la posición 
					// cero de todos los arrays en todas las clases
					break;
	
				}else {
					System.out.println("usuario o contraseña incorrectos");
				}
			}else {
				System.out.println("Usuario no encontrado");
			}
			
		}**/
		//------------autentificación superada, fase2: prueba de herencias------------//
		//Emisor.setFondos(posicionUser1, 1000);
		//receptor.setFondos(posicionUser2, 500);
		/**for(int vueltas=3; vueltas>0;vueltas--) {
			//si es no periódica
			Movimientos op1= new Movimientos("987654321", 20, "prueba", false);
			System.out.println(op1.toString());
			Emisor.setMovimiento(posicionUser1,op1.toString());
			if(receptor.getPosicion(op1.getCuenta2())!=-1) {
				posicionUser2=receptor.getPosicion(op1.getCuenta2());
				op1.Egreso(op1.getMonto(), posicionUser1,posicionUser2);//anotado con un fixme en la clase cuentas
				op1.Ingreso(op1.getMonto(), posicionUser1, posicionUser2);
			}
			
			//si es periódica
			Tperiodicas op2= new Tperiodicas("123456789", 200, "prueba2", LocalDate.now() , 1,12,2022);
			System.out.println(op2.toString());
			if(receptor.getPosicion(op2.getCuenta2())!=-1) {
				posicionUser2=receptor.getPosicion(op2.getCuenta2());
				op2.Egreso(op2.getMonto(), posicionUser1,posicionUser2);//anotado con un fixme en la clase cuentas
				op2.Ingreso(op2.getMonto(), posicionUser1, posicionUser2);
			}
			//Emisor.getMovimientosLocales(posicionUser1);
			System.out.println(receptor.getMovimientosLocales(posicionUser1));
		}
		
		
		VentanaGrafica vent = new VentanaGrafica(ejex, ejey, "BVAM");
		vent.dibujaRect(0, 0, 400, 600, 70);
		vent.leeTexto(centrox-150, centroy+10, 100, 50, "Usuario", confLetra, Color.black);
		vent.dibujaCirculo(centrox, centroy-100 , 70, 2, Color.black);
		vent.dibujaTexto(centrox-150, centroy+30, "Contraseña:", confLetra, Color.black);
		

		vent.espera(2000);
		vent.borra();
		vent.dibujaRect(centrox-150, centroy-100, 300, 200, 10, Color.RED);
		vent.dibujaTexto(centrox-120, centroy, "Sin conexión con el servidor", confLetra, Color.black);
		
		  */
		 
		}

}
