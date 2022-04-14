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

import CapaDeDatos.CapaDatosPaciente;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class CapaDiseñoPanelBorrar extends JPanel {
	CapaDiseñoSplashTransition splash=new CapaDiseñoSplashTransition();
	Timer t=new Timer();
	JLabel info;
	boolean cambio;
	/**
	 * Create the panel.
	 */
	public CapaDiseñoPanelBorrar(CapaDatosPaciente paciente) {
		setLayout(null);
		setBounds(0, 0, 700, 650);

		ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagene/fondo5.jpeg"));
		ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));
		ImageIcon delete = new ImageIcon(getClass().getResource("/Imagene/delate.png"));
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
			 * Envia al usuario de vuelta al panel principal.
			 */
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(true);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();
						inicio.setCambioIdioma(getCambio());
						if(inicio.getCambioIdioma()==true) {
							inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
							inicio.buscarP.setText("Search Patients");
							inicio.registroP.setText("Register Patient");
						}
						splash.setVisible(false);
						nuevoPanel((JPanel) inicio.getContentPane());
					}
				};

				t.schedule(ti, 2500);

			}
		});

		btnCancelar.setBounds(179, 184, 113, 102);
		btnCancelar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnCancelar.getWidth(), btnCancelar.getHeight(), Image.SCALE_SMOOTH)));
		btnCancelar.setOpaque(false);
		btnCancelar.setBorder(null);
		btnCancelar.setContentAreaFilled(false);
		add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon delete = new ImageIcon(getClass().getResource("/Imagene/delateR.png"));

				btnConfirmar.setIcon(new ImageIcon(delete.getImage().getScaledInstance(btnConfirmar.getWidth(),
						btnConfirmar.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon delete = new ImageIcon(getClass().getResource("/Imagene/delate.png"));

				btnConfirmar.setIcon(new ImageIcon(delete.getImage().getScaledInstance(btnConfirmar.getWidth(),
						btnConfirmar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnConfirmar.setBackground(Color.RED);
		btnConfirmar.addActionListener(new ActionListener() {
			/*
			 * Borra los datos del paciente en la base de datos y  cambia el idioma si es necesario.
			 */
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(true);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						paciente.borrarDatos();	
						CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();
						inicio.setCambioIdioma(getCambio());
						if(inicio.getCambioIdioma()==true) {
							inicio.lblSistemasDeCitas.setText("Covid-19 Dating System");
							inicio.buscarP.setText("Search Patients");
							inicio.registroP.setText("Register Patient");
						}
						splash.setVisible(false);
						nuevoPanel((JPanel) inicio.getContentPane());
					}
				};
				t.schedule(ti, 2500);
			}
		});
		btnConfirmar.setBounds(427, 212, 113, 66);
		btnConfirmar.setIcon(new ImageIcon(delete.getImage().getScaledInstance(btnConfirmar.getWidth(), btnConfirmar.getHeight(), Image.SCALE_SMOOTH)));
		btnConfirmar.setOpaque(false);
		btnConfirmar.setBorder(null);
		btnConfirmar.setContentAreaFilled(false);
		add(btnConfirmar);

		info = new JLabel("\u00BFDesea borrar los datos del usuario?");
		info.setForeground(Color.WHITE);
		info.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 20));
		info.setBounds(157, 87, 415, 33);
		add(info);
		JLabel fondoB = new JLabel("");
		fondoB.setBounds(0, 0, 700, 650);
		fondoB.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(fondoB.getWidth(), fondoB.getHeight(), Image.SCALE_SMOOTH)));
		add(fondoB);

	}
	/*
	 * Sobreescribe un nuevo panel sobre el actual
	 */
	public void nuevoPanel(JPanel panel) {

		removeAll();
		add(panel);
		repaint();
		revalidate();


	}
	/*
	 * Gets y sets del cambio de idioma
	 */
	public void setCambio(boolean cambio) {
		this.cambio=cambio;
	}
	public boolean getCambio() {
		return cambio;
	}
}
