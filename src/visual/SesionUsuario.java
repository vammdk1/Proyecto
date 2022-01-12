package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.jdi.Value;

import datos.BaseDatos;
import datos.Cuenta;
import datos.Movimiento;
import datos.Operaciones;
import datos.Tperiodica;
import datos.Usuario;


public class SesionUsuario extends JFrame {
	private static JList<String> lMovimientos;
	private long nc2;
	private Usuario c2;
	private double monto;
	private String descripcion;
	private int fDia = LocalDate.now().getDayOfMonth();
	private int fMes;
	private int fAnno;
	private boolean p = false;
	private Usuario user;
	//TODO hacer un hilo
	/**
	 * @param x Anchura	
	 * @param y	Altura	
	 * @param u Usuario
	 * @param b Base De datos
	 * @param v Ventana a abrir
	 */
	public SesionUsuario(int x, int y,Usuario u, BaseDatos bd, JFrame v) {
		user=u;
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		//crear un evento de cierre para visualizar la ventana anterior (va a requerir que le mande la ventana ) buscar en el repositorio
		setSize( x, y ); //tamaño
		setLocation( 200,100 ); // coordenadas
			
		// Crear contenedores
		JPanel pLateral = new JPanel();
		JPanel pBotones = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pCentral1 = new JPanel();
		JPanel pCentral2 = new JPanel();
		JPanel pCentral3 = new JPanel();
						
		//Panel general
		JLabel Titulo = new JLabel( "Bienvenido "+u.getNom());
		JLabel TNCuenta = new JLabel( "Nº de cuenta:" );	
		JLabel Ncuenta = new JLabel(Long.toString(u.getCuentaUsuario().getNcuenta()));
		JLabel TNsaldo = new JLabel("Saldo total:");
		JLabel Nsaldo = new JLabel(String.format(" %.2f", u.getCuentaUsuario().getSaldo()));
		JButton bTransferencias = new JButton("Transferencia normal");
		JButton bTPeriodicas = new JButton("Transferencia Periódica");
		JButton bSalir = new JButton("Salir");
				
		// Formato a contenedores
		pLateral.setBackground ( Color.cyan );
		// Formato de componentes
		Titulo.setFont( new Font("Arial",Font.BOLD,18));
								
		// Añadiendo componentes a contenedores
		add( pLateral, BorderLayout.WEST );
		add( pCentral, BorderLayout.CENTER );
		pLateral.setLayout(new GridLayout(2,1));
		pLateral.add(Titulo);
		pBotones.setLayout(new GridLayout(3,1));
		pBotones.add(bTransferencias);
		pBotones.add(bTPeriodicas);
		pBotones.add(bSalir);
		pLateral.add(pBotones);
		pCentral.setLayout(new GridLayout(3,1));
		pCentral.add(pCentral1);
		pCentral1.setLayout(new GridLayout(3,2));
		pCentral1.add(TNCuenta);
		pCentral1.add(Ncuenta);
		pCentral1.add(TNsaldo);
		pCentral1.add(Nsaldo);
		
		//botones para ambas operaciones
		JPanel pAC = new JPanel();
		JButton BAceptar = new JButton("Aceptar");
		JButton BCancelar = new JButton("Cancelar");
		JButton BBorrar = new JButton("Borrar");
		pAC.add(BAceptar);
		pAC.add(BBorrar);
		pAC.add(BCancelar);
		pAC.setVisible(false);
		
		pCentral2.setLayout(new GridLayout(3,1));
		
		//Transferencias normales
		JPanel PTransferencias = new JPanel();
		JLabel TNReceptor = new JLabel("Nº de cuenta del receptor");
		JTextField NC = new JTextField(10);
		JLabel TRMonto = new JLabel("Monto");
		JTextField RMonto = new JTextField(10);
		JLabel TDescrip = new JLabel("Descripción");
		JTextField Descrip = new JTextField(10);
		PTransferencias.add(TNReceptor);
		PTransferencias.add(NC);
		PTransferencias.add(TRMonto);
		PTransferencias.add(RMonto);
		PTransferencias.add(TDescrip);
		PTransferencias.add(Descrip);
		
		//Transferencias periódicas
		JPanel PTPeriodicas = new JPanel();
		JLabel TDia = new JLabel("Fecha final para la transferencia :");
		JTextField Dia = new JTextField(2);
		Dia.setEditable(false);
		Dia.setText(Integer.toString(fDia));
		JLabel TMes = new JLabel("/");
		JTextField Mes = new JTextField(2);
		JLabel TAnno = new JLabel("/");
		JTextField Anno = new JTextField(4);
		PTPeriodicas.add(TDia);
		PTPeriodicas.add(Dia);
		PTPeriodicas.add(TMes);
		PTPeriodicas.add(Mes);
		PTPeriodicas.add(TAnno);
		PTPeriodicas.add(Anno);
		
		
		PTransferencias.setVisible(false);
		PTPeriodicas.setVisible(false);
		pCentral2.add(PTransferencias);
		pCentral2.add(PTPeriodicas);
		pCentral2.add(pAC);
		pCentral.add(pCentral2);
		
		
		//lista de movimientos
		lMovimientos = new JList<String>();
		getListaMovimientos(bd,u);
		lMovimientos.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane lScrollM= new JScrollPane(lMovimientos);//Para usar el scrol panel hay que agregar la lista al interior de la misma cuando la declaramos
		pCentral.add(lScrollM);
		
		//comprobador de periodicas
		ComprobadorPeiodicas(u,bd);

		//si se cierra la ventana, la anterior se activa
		addWindowListener( new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				v.setVisible( true );
			}
		});
		
		bSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			v.setVisible(true);
			}
		});
		
		bTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 PTransferencias.setVisible(true);
			 pAC.setVisible(true);
			 PTPeriodicas.setVisible(false);
			 lScrollM.setEnabled(false);
			 p=false;
			}
		});
		
		bTPeriodicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PTransferencias.setVisible(true);
				 pAC.setVisible(true);
				 PTPeriodicas.setVisible(true);
				 p=true;
				}
			});
		
		
		BAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!NC.getText().equals("")&&!RMonto.getText().equals("")&&!Descrip.getText().equals("")) {
				nc2=Long.parseLong(NC.getText());
				monto=Double.parseDouble(RMonto.getText());
				descripcion=Descrip.getText();
				if(monto<=0) {
					JOptionPane.showMessageDialog(null, "No se pueden hacer transferencias negativas", "Monto incorrecto", JOptionPane.ERROR_MESSAGE);
				}else if(u.getCuentaUsuario().getSaldo()<monto) {
					JOptionPane.showMessageDialog(null, "No posee la solvencia para esta operación", "Monto incorrecto", JOptionPane.ERROR_MESSAGE);
				}else {
					for(Map.Entry<String,Usuario> entrada: bd.getCusuarios().entrySet()) {
						Usuario valor = entrada.getValue();
						if(((Usuario)valor).getCuentaUsuario().getNcuenta()==nc2) {
							c2 = valor;
						}
					}
					if(c2==null) {
						JOptionPane.showMessageDialog(null, "El numero de cuenta no corresponde a ningun usuario", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
					}else {
						if(p==false) {
							Movimiento m1 = new Movimiento(u.getCuentaUsuario(),c2.getCuentaUsuario(), monto, descripcion, false);
							Movimiento m2 = new Movimiento(c2.getCuentaUsuario(), u.getCuentaUsuario(), monto, descripcion, true);
							bd.PasosMovimientos(m1,null);
							bd.PasosBDatos(m1,m2,u,c2, bd);
							getListaMovimientos(bd,u);
							
							//----------Fin de la operación -----------------
							Nsaldo.setText(String.format(" %.2f", u.getCuentaUsuario().getSaldo()));
							NC.setText("");
							RMonto.setText("");
							Descrip.setText("");
							Mes.setText("");
							Anno.setText("");
							pAC.setVisible(false);
							PTransferencias.setVisible(false);
							PTPeriodicas.setVisible(false);
							p=false;
							
						}else {
							if(!Mes.getText().equals("")&&!Anno.getText().equals("")) {
								fMes=Integer.parseInt(Mes.getText());
								fAnno=Integer.parseInt(Anno.getText());
								LocalDate fechaActual = LocalDate.now();
								if(fAnno<fechaActual.getYear()){
									JOptionPane.showMessageDialog(null, "No se pueden hacer transferencias a años anteriores", "Año incorrecto", JOptionPane.ERROR_MESSAGE);
									Anno.requestFocus();
								}else{
									if((fMes<fechaActual.getMonthValue() && fAnno==fechaActual.getYear() )|| fMes>12) {
										JOptionPane.showMessageDialog(null, "No se pueden hacer transferencias a meses anteriores o inexistentes", "Mes incorrecto", JOptionPane.ERROR_MESSAGE);
										Mes.requestFocus();
									}else {
											LocalDate fechafin = LocalDate.of(fAnno,fMes,LocalDate.now().getDayOfMonth());
											long repeticiones = ChronoUnit.MONTHS.between(fechaActual, fechafin);
											Tperiodica t1 = new Tperiodica(u.getCuentaUsuario(),c2.getCuentaUsuario(), monto, descripcion, false,fMes,fAnno,repeticiones,fechaActual);
											Movimiento t2 = new Movimiento(c2.getCuentaUsuario(),u.getCuentaUsuario(), monto, descripcion, true);
											bd.PasosMovimientos(t2,t1);
											bd.PasosBDatos(t1,t2,u,c2, bd);
											getListaMovimientos(bd,u);
											
											//----------Fin de la operación -----------------
											Nsaldo.setText(String.format(" %.2f", u.getCuentaUsuario().getSaldo()));
											NC.setText("");
											RMonto.setText("");
											Descrip.setText("");
											Mes.setText("");
											Anno.setText("");
											pAC.setVisible(false);
											PTransferencias.setVisible(false);
											PTPeriodicas.setVisible(false);
											p=false;
											
										}
									}	
								}else {
									JOptionPane.showMessageDialog(null, "Faltan datos para hacer la operación", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
								}
							}
						}				
					}
				}else {
					JOptionPane.showMessageDialog(null, "Faltan datos para hacer la operación", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		BCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NC.requestFocus();
				NC.setText("");
				RMonto.setText("");
				Descrip.setText("");
				Mes.setText("");
				Anno.setText("");
				PTransferencias.setVisible(false);
				PTPeriodicas.setVisible(false);
				pAC.setVisible(false);
			}
		});
		
		// Botón de borrado de transferencias
		BBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NC.requestFocus();
				NC.setText("");
				RMonto.setText("");
				Descrip.setText("");
				Mes.setText("");
				Anno.setText("");
			}
		});
		
		
		
		
		
		}
	/**
	 * esto nos regresa la lista de movimientos en la ventana
	 * @param b base de datos a leer
	 * @param u usuario con datos a leer
	 */
	public void getListaMovimientos(BaseDatos b, Usuario u) {
		DefaultListModel lm = new DefaultListModel();
		if(b.getCuentaMovimientos(u.getCuentaUsuario()).size()==0) {
			lm.addElement("No hay movimientos");
		}else {
			for(int i=0; i<b.getCuentaMovimientos(u.getCuentaUsuario()).size(); i++) {
				lm.addElement(b.getCuentaMovimientos(u.getCuentaUsuario()).get(i));	
			}	
		}
		lMovimientos.setModel(lm);
	}

	
	/**programa que procesa las operaciones periodicas
	 * @param u usuario que se procesa
	 */
	public void ComprobadorPeiodicas(Usuario u,BaseDatos bd){
		LocalDate fechaActual = LocalDate.now();
		for(int i=0;i< u.getCuentaUsuario().getOperaciones().size();i++) {
			Operaciones op = u.getCuentaUsuario().getOperaciones().get(i);
			if(op.getClass()==Tperiodica.class){
				Tperiodica opP = (Tperiodica)op;
				if(opP.getRepeticionesR()>0) {
					if((opP.getFinicial().getDayOfMonth()==fechaActual.getDayOfMonth()&&
							opP.getUltimoCambio().getMonth()!=fechaActual.getMonth()&&
							opP.getUltimoCambio().getYear()==fechaActual.getYear())||
							(opP.getUltimoCambio().getYear()!=fechaActual.getYear()&&opP.getFinicial().getDayOfMonth()==fechaActual.getDayOfMonth())) {
						opP.setRepeticionesR((opP.getRepeticionesR()-1));
						opP.setUltimoCambio(fechaActual);
						if(opP.getMonto()<=u.getCuentaUsuario().getSaldo()) {
							Movimiento t1 = new Movimiento(u.getCuentaUsuario(),opP.getCuenta2(),opP.getMonto(),opP.getDescripcion(),false);
							Movimiento t2 = new Movimiento(opP.getCuenta2(),u.getCuentaUsuario(),opP.getMonto(),opP.getDescripcion(), true);
							bd.PasosMovimientos(t1,null);
							Usuario u2 = opP.getCuenta2().getUsuario();
							bd.PasosBDatos(t1,t2,u,u2, bd);
							getListaMovimientos(bd,u);
						}else {
							JOptionPane.showMessageDialog(null, "No posee la solvencia para continuar con la operación: "+ opP.toString(), "Monto incorrecto", JOptionPane.ERROR_MESSAGE);
							opP.setRepeticionesR(0);
							bd.guardado();
						}
					}
				}
			}
		}
	}
	

}
