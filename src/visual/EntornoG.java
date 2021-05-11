package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class EntornoG extends JFrame{
	
	public EntornoG(int y, int x) {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( y, x ); //tamaño
		setLocation( 2000,100 ); // coordenadas
	
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
		
		// Formato a contenedores
		
		pTop.setBackground ( Color.cyan );
		pInferior.setBackground( Color.lightGray );
		// Formato de componentes
		Titulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		
		pCentral2.setLayout( new GridLayout( 2, 2 ) );
				
		// Añadiendo componentes a contenedores
		add( pTop, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pTop.add( Titulo );
		pCentral2.add( Usuario );
		pCentral2.add( TUsuario );
		pCentral2.add( Pin );
		pCentral2.add( TPin );
		pCentral.add(pCentral2);
		pInferior.add(Inicio);
		
		
	}

}
