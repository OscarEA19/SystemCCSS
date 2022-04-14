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

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import CapaDeDatos.CapaDatosPaciente;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class CapaDiseñoPanelBusc extends JPanel {
	CapaDiseñoSplashTransition splash=new CapaDiseñoSplashTransition();
	Timer t=new Timer();
	JLabel info;
	JLabel cedu;
	private boolean cambio;

	/**
	 * Crea un panel mediante un constructor.
	 */
	public CapaDiseñoPanelBusc() {
		CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();

		setLayout(null);
		setBounds(0, 0, 700, 650);

		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagene/fondo5.jpeg"));

		ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));

		JTextField textField = new JTextField();
		textField.setBounds(207, 275, 203, 20);
		add(textField);
		textField.setColumns(10);
		/*
		 * Si se presiona cancelar el sistema enviará al usuario de vuelta al panel principal
		 */
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			/*
			 * El metodo mouseEntered aplica la función de cuando el cursor
			 * este sobre el boton cambie a otro icono el cual se declara en la variable mostrar
			 */
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/salir.png"));

				btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(),
						btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
			}
			/*
			 * El metodo mouseExited aplica la función de cuando el cursor no este sobre el botón el icono pase a su 
			 * estado inicial.
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));

				btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(),
						btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnCancelar.setBackground(Color.RED);
		btnCancelar.addActionListener(new ActionListener() {
			/*
			 * Si el sistema se cambio a ingles el sistema cambia el idioma
			 */
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(true);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						inicio.setCambioIdioma(getCambio());
						if(inicio.getCambioIdioma()==true) {
							inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
							inicio.buscarP.setText("Search Patients");
							inicio.registroP.setText("Register Patient");
						}
						splash.setVisible(false);
						nuevoPanel((JPanel)inicio.getContentPane());
					}
				};
				t.schedule(ti, 2500);

			}
		});
		btnCancelar.setBounds(456, 306, 87, 70);
		btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(), btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
		btnCancelar.setOpaque(false);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorder(null);

		add(btnCancelar);
		ImageIcon buscar = new ImageIcon(getClass().getResource("/Imagene/lupa.png")); 
		JButton btnbuscar = new JButton("");
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon buscar = new ImageIcon(getClass().getResource("/Imagene/lupaA.png"));

				btnbuscar.setIcon(new ImageIcon(buscar.getImage().getScaledInstance(btnbuscar.getWidth(),
						btnbuscar.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon buscar = new ImageIcon(getClass().getResource("/Imagene/lupa.png"));

				btnbuscar.setIcon(new ImageIcon(buscar.getImage().getScaledInstance(btnbuscar.getWidth(),
						btnbuscar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnbuscar.setBackground(Color.RED);

		btnbuscar.setBounds(456, 269, 53, 26);
		btnbuscar.setIcon(new ImageIcon(buscar.getImage().getScaledInstance(btnbuscar.getWidth(), btnbuscar.getHeight(), Image.SCALE_SMOOTH)));
		btnbuscar.setOpaque(false);
		btnbuscar.setBorder(null);
		btnbuscar.setContentAreaFilled(false);
		add(btnbuscar);
		btnbuscar.addActionListener(new ActionListener() {
			/*
			 *Envia el numero de cedula a la base de datos para buscar la informacion del 
			 *paciente y si el idioma se cambia a ingles cambia todos los textos y envia 
			 *al usuario al panel de informacion
			 */
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Digite el numero de cédula para\nverificarlo en nuestra base de datos.");
				}else {
					splash.setVisible(true);
					TimerTask ti=new TimerTask() {
						@Override
						public void run() {
							CapaDatosPaciente pac=new CapaDatosPaciente();
							pac.setCedula(textField.getText());
							pac.RetornoBaseDatos();
							if(pac.getEdad()==0) {
								inicio.setCambioIdioma(getCambio());
								if(inicio.getCambioIdioma()==true) {
									inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
									inicio.buscarP.setText("Search Patients");
									inicio.registroP.setText("Register Patient");
								}
								splash.setVisible(false);
								nuevoPanel((JPanel)inicio.getContentPane());
							}
							else{


								CapaDiseñoMostrarInfo mostrar = new CapaDiseñoMostrarInfo(pac);
								inicio.setCambioIdioma(getCambio());
								if(inicio.getCambioIdioma()==true ){
									mostrar.cedula.setText("ID:");
									mostrar.fechaCita.setText("Date:");
									mostrar.vacunaCovid.setText("Vaccine:");
									mostrar.lugarResi.setText("Residence Province:");
									mostrar.positivo.setText("Covid-19 Positive:");
									mostrar.fac.setText("Risk Factor:");
									mostrar.edad.setText("Age:");
									mostrar.nombre.setText("Name");
									mostrar.info.setText("Information Of The Patient");
									mostrar.setCambio(inicio.getCambioIdioma());

								}
								splash.setVisible(false);
								nuevoPanel(mostrar);
							}
						}
					};
					t.schedule(ti, 2500);

				}
			}
		});

		cedu = new JLabel("Cedula:");
		cedu.setFont(new Font("Arial", Font.PLAIN, 14));
		cedu.setForeground(Color.WHITE);
		cedu.setBounds(147, 277, 61, 14);
		add(cedu);

		info = new JLabel("Digite El Numero De Cedula ");
		info.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 22));
		info.setForeground(Color.WHITE);
		info.setBounds(182, 217, 338, 28);
		add(info);
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 700, 650);
		fondo.setIcon(new ImageIcon(imagenFondo.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));

		add(fondo);
	}
	/*
	 * Sobreescribe un nuevo panel dentro de otro
	 */
	public void nuevoPanel(JPanel panel) {

		removeAll();
		add(panel);
		repaint();
		revalidate();


	}

	/*
	 * Gets y sets de cambio de idioma
	 */
	public void setCambio(boolean cambio) {
		this.cambio=cambio;
	}
	public boolean getCambio() {
		return cambio;
	}

}

