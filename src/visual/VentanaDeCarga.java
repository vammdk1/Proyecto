package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;


public class VentanaDeCarga extends Thread{
	


	public void run() {
		JFrame v = new JFrame();
		v.setUndecorated(true);
		v.setVisible(true);
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		v.setSize( 810, 300 ); //tamaño
		v.setLocation( 200,100 ); // coordenadas
	
		// Crear contenedores
		JPanel pTop = new JPanel();
		JPanel pCentral  = new JPanel();
		JPanel pInferior = new JPanel();
		
		JLabel Titulo = new JLabel( "BBVAM" );
		JLabel Mensaje = new JLabel("Conectando al servidor");
		
		pTop.setBackground ( Color.cyan );
		pInferior.setBackground( Color.lightGray );
		// Formato de componentes
		Titulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		
		// Añadiendo componentes a contenedores
		v.add( pTop, BorderLayout.NORTH );
		v.add( pCentral, BorderLayout.CENTER);
		pTop.add( Titulo );
		JProgressBar barra = new JProgressBar();
		Mensaje.setFont( new Font( "Arial", Font.BOLD, 24 ));
		pCentral.setLayout( new GridLayout( 3, 1 ) );
		pCentral.add(Mensaje);
		pCentral.add(barra);
		for(int i = barra.getMinimum(); i <= barra.getMaximum(); i++){
            final int percent = i ;
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                	barra.setValue(percent);
                }
            });
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		v.dispose();
	}

}