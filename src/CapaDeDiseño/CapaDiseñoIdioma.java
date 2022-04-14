/*
 * Autores del Proyecto: Oscar Espinoza,Jairo Guzmán,José Ruiz,Samuel Velazquez,Guillermo Solano
 * 
 * Fecha de Inicio:22/5/2021
 * 
 * Fecha de Conclusión: 7/8/2021
 * 
 * Titulo: Sistema de Citas-CCSS
 * 
 * Objetivo del proyecto: Este proyecto fue creado con el objetivo de mejorar el sistema de
 * asignación de citas para la vacunación del Covid-19, ya que no existe un orden especifico para
 * la asignación de las citas lo que imposibilita la aceleración del proceso
 * 
 * Descripción: El sistema cuenta con tres opciones en su panel principal las cuales son:
 * Registrar Paciente,Consulta de Paciente y Cambio de Idioma,l panel Registrar
 * contiene los campos a llenar de la informacion del paciente lo que enviara el reporte,
 * tanto a la base de datos como un correo informativo al paciente sobre su cita de 
 * vacunación, la opcion de consulta, retorna la información del paciente en el sistema en 
 * este mismo panel se puede eliminar la información de este paciente si es el caso,
 * la opción de idioma hace un cambio de español a ingles de todas las partes del sistema.
 * 
 */
package CapaDeDiseño;

import java.awt.Image;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class CapaDiseñoIdioma extends JPanel  {
	JRadioButton ingles;
	JRadioButton español;
	CapaDiseñoSplashTransition splash=new CapaDiseñoSplashTransition();
	Timer t=new Timer();
	/**
	 * Constructor encargado de crear un nuevo panel.
	 */
	public CapaDiseñoIdioma() {

		setLayout(null);
		setBounds(0, 0, 700, 650);
		ImageIcon ifondo = new ImageIcon(getClass().getResource("/Imagene/fondo5.jpeg"));
		ImageIcon check = new ImageIcon(getClass().getResource("/Imagene/check2.png"));


		español = new JRadioButton("Espa\u00F1ol/Spanish");
		español.setBounds(439, 176, 120, 23);
		add(español);

		ingles = new JRadioButton("Ingl\u00E9s/English");
		ingles.setBounds(168, 176, 109, 23);
		add(ingles);

		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(español);
		grupo1.add(ingles);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			/*
			 * El metodo mouseEntered aplica la función de cuando el cursor
			 * este sobre el boton cambie a otro icono el cual se declara en la variable mostrar
			 */
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon check = new ImageIcon(getClass().getResource("/Imagene/checkA.png"));

				btnAceptar.setIcon(new ImageIcon(check.getImage().getScaledInstance(btnAceptar.getWidth(),
						btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
			}
			/*
			 * El metodo mouseExited aplica la función de cuando el cursor no este sobre el botón el icono pase a su 
			 * estado inicial.
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon check = new ImageIcon(getClass().getResource("/Imagene/check2.png"));

				btnAceptar.setIcon(new ImageIcon(check.getImage().getScaledInstance(btnAceptar.getWidth(),
						btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnAceptar.setBackground(Color.RED);
		btnAceptar.addActionListener(new ActionListener() {
			/*
			 * Cambia el idioma dependiendo la opcion seleccionada
			 */
			public void actionPerformed(ActionEvent e) {
				if(ingles.isSelected()==false && español.isSelected()==false) {
					JOptionPane.showMessageDialog(null,"Escoja un idioma para continuar.\nChoose a language to continue.");
				}else {
					splash.setVisible(true);
					TimerTask ti=new TimerTask() {
						@Override
						public void run() {
							CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();

							if(ingles.isSelected()) {
								inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
								inicio.buscarP.setText("Search Patients");
								inicio.registroP.setText("Register Patient");
								inicio.setCambioIdioma(true);
								splash.setVisible(false);
								JOptionPane.showMessageDialog(null, "¡The lenguage was changed successfully!");
							} 
							if(español.isSelected()) {
								inicio.lblSistemasDeCitas.setText("Sistema de Citas-CCSS");
								inicio.buscarP.setText("Buscar paciente");
								inicio.registroP.setText("Registrar Paciente");
								splash.setVisible(false);
								JOptionPane.showMessageDialog(null, "¡Se cambio exitosamente el idioma!");
							}
							
							
							nuevoPanel((JPanel)inicio.getContentPane());
						}
					};
					t.schedule(ti, 2500);

				}
			}
		});
		btnAceptar.setBounds(286, 266, 153, 124);
		btnAceptar.setIcon(new ImageIcon(check.getImage().getScaledInstance(btnAceptar.getWidth(), btnAceptar.getHeight(), Image.SCALE_SMOOTH)));
		btnAceptar.setOpaque(false);
		btnAceptar.setBorder(null);
		btnAceptar.setContentAreaFilled(false);
		add(btnAceptar);

		JLabel idioma = new JLabel("Idioma");
		idioma.setForeground(Color.WHITE);
		idioma.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 28));
		idioma.setBounds(304, 65, 129, 26);
		add(idioma);
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 700, 650);
		lblFondo.setIcon(new ImageIcon(ifondo.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH)));
		add(lblFondo);





	}
	/*
	 * Sebreescribe un nuevo panel sobre el actual
	 */
	public void nuevoPanel(JPanel panel) {

		removeAll();
		add(panel);
		repaint();
		revalidate();


	}


}
