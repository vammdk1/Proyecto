package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import datos.BaseDatos;
import datos.Cuenta;
import datos.Usuario;


public class InicioSesión extends JFrame{
	
	private boolean cancelar = false;
	private boolean pantallaR = false;
	private Usuario TempUser;
	private Usuario uN;
	private Cuenta cN;
	private static JTextField TApellido;
	
	
	
	public InicioSesión(int y, int x, BaseDatos bd) {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( x, y ); //tamaño
		setLocation( 200,100 ); // coordenadas
	
		// Crear contenedores
		JPanel pTop = new JPanel();
		JPanel pCentral  = new JPanel();
		JPanel pCentral2 = new JPanel();
		JPanel pInferior = new JPanel();
		
		// Crear componentes
		JLabel Titulo = new JLabel( "BBVAM" );
		JLabel Usuario = new JLabel( "Usuario:" );
		JLabel Pin = new JLabel( "Pin:" );
		JTextField TUsuario = new JTextField( 10 ); //espacio de la palabra ?
		JPasswordField TPin = new JPasswordField( 10 );
		JButton Inicio = new JButton("Acceder");		
		JButton Registro = new JButton("Registrarse");
		
		//panel de registro
	
		JLabel Apellido = new JLabel("Apellido");
		TApellido = new JTextField(10);
		
		
		
		// Formato a contenedores
		
		pTop.setBackground ( Color.cyan );
		pInferior.setBackground( Color.lightGray );
		// Formato de componentes
		Titulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		
		pCentral2.setLayout( new GridLayout( 3, 2 ) );
				
		// Añadiendo componentes a contenedores
		add( pTop, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pTop.add( Titulo );
		pCentral2.add( Usuario );
		pCentral2.add( TUsuario );
		pCentral2.add(Apellido);
		Apellido.setVisible(false);
		pCentral2.add(TApellido);
		TApellido.setVisible(false);
		pCentral2.add( Pin );
		pCentral2.add( TPin );
		pCentral.add(pCentral2);
		pInferior.add(Inicio);
		pInferior.add(Registro);
		
		//funciones
		Inicio.addActionListener(new ActionListener() {
			
			/**
			 * abre o no la segunda ventana en función de la autentificación
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pantallaR==false) {
				String validacionC = TPin.getText(); 
				if(validacionC.equalsIgnoreCase("") || TUsuario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña vacíos", "Error Usuario", JOptionPane.ERROR_MESSAGE);
				}else {
					File Comprobador = new File("cUsuarios.users");
					if(Comprobador.exists()) {
						if(bd.CompruebaUsuarios(validacionC).getNom().equals(TUsuario.getText())) {
							TempUser = bd.CompruebaUsuarios(validacionC);
							SesionUsuario v2 = new SesionUsuario(810, y, TempUser,bd,InicioSesión.this);
							setVisible(false);
							VentanaDeCarga vc = new VentanaDeCarga();
							vc.start();
							v2.setVisible(true);
							
							}else {
								JOptionPane.showMessageDialog(null, "Error en usuario o contraseña", "Error de autentificación", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos", "Error de conexión", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					if(TUsuario.getText()!=null && TApellido.getText()!=null && TPin.getText()!=null) {
						//String prueba= bd.CompruebaUsuarios(TPin.getText()).getNom()==null? "":bd.CompruebaUsuarios(TPin.getText()).getNom();
						if(bd.CompruebaUsuarios(TPin.getText()).getNom().equals("vacio")){

							Inicio.setText("Acceder");
							Registro.setText("Registrarse");
							Apellido.setVisible(false);
							TApellido.setVisible(false);
							pantallaR=false;
							cancelar=true;
							uN = new Usuario(TUsuario.getText(), TApellido.getText(),TPin.getText(),null); //crear usuario
							cN = new Cuenta(0, 100, uN,null,true); 
							cN.setNcuenta();
							bd.RelacionUserCuenta(uN, cN);//relacionar usuario con cuenta
							bd.addUsuario(uN); // guarda usuarios
							bd.ListaUsuarios(); 
							bd.guardado();
				
						}else {
							JOptionPane.showMessageDialog(null, "El pin ingresado no se encuentra disponible", "Error de datos", JOptionPane.ERROR_MESSAGE);
							TPin.requestFocus();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Faltan datos para el registro", "Error de datos", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		
		Registro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cancelar==false) {
					Inicio.setText("Aceptar");
					Registro.setText("Cancelar");
					pantallaR=true;
					TUsuario.requestFocus();
					Apellido.setVisible(true);
					TApellido.setVisible(true);
					cancelar=true;
					TUsuario.setText("");
					TApellido.setText("");
					TPin.setText("");
				}else {
					pantallaR=false;
					cancelar=false;
					Registro.setText("Registrarse");
					Inicio.setText("Acceder");
					Apellido.setVisible(false);
					TApellido.setVisible(false);
					TUsuario.setText("");
					TApellido.setText("");
					TPin.setText("");
				}
			}
		});
		
	}
				
}
		

