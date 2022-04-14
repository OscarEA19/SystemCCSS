package CapaDeDiseño;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import CapaDeDatos.CapaDatosCorreo;
import CapaDeDatos.CapaDatosFecha;
import CapaDeDatos.CapaDatosPaciente;
import CapaDeDatos.CapaDatosVacuna;

public class CapaDiseñoEditarInfo extends JPanel {

	CapaDiseñoSplashTransition splash=new CapaDiseñoSplashTransition();
	Timer t=new Timer();
	JLabel correoLabl;
	JRadioButton noPositivo;
	JRadioButton siPositivo;
	JRadioButton noFactor;
	JRadioButton siFactor;
	JLabel id;
	JLabel edad;
	JLabel factorRiesgo;
	JLabel positivoCovid;
	JLabel provincia;
	JLabel nombre;
	JLabel regis;
	private boolean cambio;

	/**
	 * Create the panel.
	 */
	public CapaDiseñoEditarInfo(CapaDatosPaciente pac) {
		setBounds(0, 0, 700, 651);								//Crea diseño del panel
		setLayout(null);





		JTextField correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(248, 291, 276, 20);					//crea diseño de las distintas cajas de texto
		add(correo);

		correoLabl = new JLabel("Correo:");
		correoLabl.setForeground(Color.WHITE);
		correoLabl.setFont(new Font("Arial", Font.PLAIN, 14));		//crea JLabels
		correoLabl.setBounds(189, 293, 60, 14);
		add(correoLabl);



		JComboBox residencia = new JComboBox();
		residencia.setBounds(368, 470, 89, 22);
		residencia.setModel(new DefaultComboBoxModel(new String[] {"San Jos\u00E9", "Heredia", "Alajuela", "Cartago", "Guanacaste", "Puntarenas", "Limon"}));

		add(residencia);

		noPositivo = new JRadioButton("No");
		noPositivo.setBounds(395, 409, 40, 23);

		add(noPositivo);

		siPositivo = new JRadioButton("Si");
		siPositivo.setBounds(324, 409, 54, 23);
		add(siPositivo);

		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(noPositivo);
		grupo1.add(siPositivo);

		noFactor = new JRadioButton("No");
		noFactor.setBounds(395, 346, 40, 23);
		add(noFactor);



		siFactor = new JRadioButton("Si");
		siFactor.setBounds(324, 346, 54, 23);
		add(siFactor);

		ButtonGroup grupo2 = new ButtonGroup();
		grupo2.add(noFactor);
		grupo2.add(siFactor);

		JTextField annos = new JTextField();
		annos.setBounds(248, 225, 40, 20);
		annos.setColumns(10);
		annos.setText(""+pac.getEdad()+"");
		add(annos);


		JLabel cedu = new JLabel();
		cedu.setForeground(new Color(255, 204, 153));
		cedu.setBackground(Color.WHITE);
		cedu.setBounds(248, 168, 167, 20);
		cedu.setText(pac.getCedula());
		add(cedu);

		JTextField name = new JTextField();
		name.setBounds(248, 101, 276, 20);
		name.setText(pac.getNombre());
		add(name);
		name.setColumns(10);
		ImageIcon enter = new ImageIcon(getClass().getResource("/Imagene/confirm.png"));			//Imagenes de los logos que utilizara el programa
		ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {

			/*
			 * El metodo mouseEntered aplica la función de cuando el cursor
			 * este sobre el botón cambie a otro icono el cual se declara en la variable mostrar
			 */
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon enter = new ImageIcon(getClass().getResource("/Imagene/confirmacion.png"));

				btnAceptar.setIcon(new ImageIcon(enter.getImage().getScaledInstance(btnAceptar.getWidth(),
						btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
			}
			/*
			 * El metodo mouseExited aplica la función de cuando el cursor no este sobre el botón el icono pase a su 
			 * estado inicial.
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon enter = new ImageIcon(getClass().getResource("/Imagene/confirm.png"));

				btnAceptar.setIcon(new ImageIcon(enter.getImage().getScaledInstance(btnAceptar.getWidth(),
						btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
			}


		});
		btnAceptar.setBackground(Color.RED);
		btnAceptar.setBounds(395, 527, 79, 50);
		btnAceptar.setIcon(new ImageIcon(enter.getImage().getScaledInstance(btnAceptar.getWidth(), btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
		btnAceptar.setOpaque(false);
		btnAceptar.setBorder(null);
		btnAceptar.setContentAreaFilled(false);
		btnAceptar.addActionListener(new ActionListener() {
			/*
			 * Al hacer click el programa envia la información proporcionada a los diferentes objetos para 
			 * la asignación de las fechas, la vacuna y envia el correo al usuario
			 */

			public void actionPerformed(ActionEvent e) {
				if(correo.getText().length()==0||name.getText().length()==0||cedu.getText().length()==0||annos.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Debe llenar el formulario para continuar");
				}else {
					splash.setVisible(true);

					if(noFactor.isSelected()) {
						pac.setFactorDeRiesgo("NO");
					}													//dependiendo del botón presionado el programa asignará una cadena de texto "SI"o "NO"
					if(siFactor.isSelected()) {
						pac.setFactorDeRiesgo("SI");
					}
					if(siPositivo.isSelected()) {
						pac.setCovid("SI");
					}
					if(noPositivo.isSelected()) {
						pac.setCovid("NO");
					}
					/*
					 * Si la base de datos no se logra conectar o pasa algun error de envio o el correo no se puede enviar el sistema no realizara el registro
					 */




					TimerTask ti=new TimerTask() {
						@Override
						public void run() {



							try {


								CapaDatosVacuna vacuna=new CapaDatosVacuna(pac);
								CapaDatosFecha  fecha=new CapaDatosFecha(pac);								
								CapaDatosCorreo correosend=new CapaDatosCorreo(pac);
								pac.setNombre(name.getText());
								pac.setCedula(cedu.getText());						//Envia al objeto paciente los datod proporcionados por el usuario
								pac.setEdad(Integer.parseInt(annos.getText()));
								pac.setLugarResidencia((String) residencia.getSelectedItem());
								vacuna.asignacionVacuna(); // asigna la vacuna que se le aplicará al paciente
								fecha.fechaVacuna();   //asigna la fecha de la vacuna del paciente
								pac.editarDatos(); // Envia los datos a la base de datos respectiva
								correosend.setEmail(correo.getText()); //Setea el correo al cual se enviara el correo.

								correosend.sendEmail();//Le envia al paciente un correo con el dia de su cita y la dosis q se le aplicará además de sus datos relevantes
								splash.setVisible(false);
								JOptionPane.showMessageDialog(null,"Se editaron los datos con exito");

							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Error en edición");
								splash.setVisible(false);

							}

							CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();
							inicio.setCambioIdioma(getCambio());
							if(inicio.getCambioIdioma()==true) {
								inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");	//Hace el cambio de idioma si es necesario
								inicio.buscarP.setText("Search Patients");
								inicio.registroP.setText("Register Patient");
							}



							nuevoPanel((JPanel)inicio.getContentPane());

						}



					};
					t.schedule(ti, 2500);





				}

			}
		});
		add(btnAceptar);




		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/salir.png"));

				btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(),
						btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));

				btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(),
						btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnCancelar.setBackground(Color.RED);

		btnCancelar.setBounds(189, 515, 99, 78);
		btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(), btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
		btnCancelar.setOpaque(false);
		btnCancelar.setBorder(null);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.addActionListener(new ActionListener() {
			/*
			 * Si se presiona cancelar el sistema enviará al usuario de vuelta al panel principal
			 */
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(true);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						CapaDiseñoMostrarInfo mostrar = new CapaDiseñoMostrarInfo(pac);
						mostrar.setCambio(getCambio());
						if(mostrar.getCambio()==true ){
							mostrar.cedula.setText("ID:");
							mostrar.fechaCita.setText("Date:");
							mostrar.vacunaCovid.setText("Vaccine:");
							mostrar.lugarResi.setText("Residence Province:");
							mostrar.positivo.setText("Covid-19 Positive:");
							mostrar.fac.setText("Risk Factor:");
							mostrar.edad.setText("Age:");
							mostrar.nombre.setText("Name");
							mostrar.info.setText("Information Of The Patient");
							

						}
						splash.setVisible(false);
						nuevoPanel(mostrar);
//						CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();
//
//						inicio.setCambioIdioma(getCambio());
//						if(inicio.getCambioIdioma()==true) {
//							inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
//							inicio.buscarP.setText("Search Patients");
//							inicio.registroP.setText("Register Patient");
//						}
//						splash.setVisible(false);
						
					}
				};
				t.schedule(ti, 2500);

			}
		});
		add(btnCancelar);





		id = new JLabel("Cedula:");
		id.setBounds(189, 170, 60, 14);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("Arial", Font.PLAIN, 14));
		add(id);

		edad = new JLabel("Edad:");
		edad.setBounds(189, 227, 47, 14);
		edad.setForeground(Color.WHITE);
		edad.setFont(new Font("Arial", Font.PLAIN, 14));
		add(edad);

		factorRiesgo = new JLabel("Factor de riesgo:");
		factorRiesgo.setBounds(189, 349, 113, 14);
		factorRiesgo.setForeground(Color.WHITE);
		factorRiesgo.setFont(new Font("Arial", Font.PLAIN, 14));
		add(factorRiesgo);

		positivoCovid = new JLabel("Positivo Covid-19");
		positivoCovid.setBounds(189, 412, 113, 14);
		positivoCovid.setForeground(Color.WHITE);
		positivoCovid.setFont(new Font("Arial", Font.PLAIN, 14));
		add(positivoCovid);

		provincia = new JLabel("Provincia de residencia:");
		provincia.setBounds(189, 473, 158, 14);
		provincia.setForeground(Color.WHITE);
		provincia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(provincia);


		nombre = new JLabel("Nombre:");
		nombre.setBounds(189, 103, 60, 14);
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setForeground(Color.WHITE);
		add(nombre);

		regis = new JLabel("Editar Datos de Paciente");
		regis.setBounds(233, 39, 336, 32);
		regis.setForeground(Color.WHITE);
		regis.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 26));
		add(regis);

		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagene/fondo5.jpeg"));
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 700, 650);
		fondo.setIcon(new ImageIcon(imagenFondo.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		add(fondo);



	}
	/*
	 * Remueve el panel anterior y coloca uno nuevo dependiendo la instruccion
	 */
	public void nuevoPanel(JPanel nuevo) {
		removeAll();
		add(nuevo);
		repaint();
		revalidate();

	}

	//Set y get del metodo cambio que es a variable que tiene que hacer el cambio en cada panel
	public void setCambio(boolean cambio) {
		this.cambio=cambio;
	}
	public boolean getCambio() {
		return cambio;
	}

}
