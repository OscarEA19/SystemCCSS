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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class CapaDiseñoMostrarInfo extends JPanel {

	CapaDiseñoSplashTransition splash=new CapaDiseñoSplashTransition();
	Timer t=new Timer();
	JLabel info;
	JLabel nombre;
	JLabel cedula;						//Propiedades de Labels,se covierten propiedades para hacer posivble el cambio de idioma
	JLabel edad;
	JLabel fac;
	JLabel positivo;
	JLabel lugarResi;
	JLabel vacunaCovid;
	JLabel fechaCita;
	private boolean cambio;
	CapaDiseñoPrincipal inicio = new CapaDiseñoPrincipal();
	/**
	 * Costructor encargado de crear el panel
	 */

	public CapaDiseñoMostrarInfo(CapaDatosPaciente pac) {

		setBounds(0, 0, 700, 650);
		setLayout(null);

		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagene/fondo5.jpeg"));

		ImageIcon fondoBU= new ImageIcon(getClass().getResource("/Imagene/BorrarBoton.png"));
		ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));

		JLabel boton = new JLabel("");
		boton.setBounds(629, 33, 45, 43);
		boton.setIcon(new ImageIcon(fondoBU.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH)));
		boton.addMouseListener((MouseListener) new MouseAdapter(){
			/*
			 * El metodo mouseEntered aplica la función de cuando el cursor
			 * este sobre el boton cambie a otro icono el cual se declara en la variable mostrar
			 */
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon fondoBU = new ImageIcon(getClass().getResource("/Imagene/BorrarBotonR.png"));

				boton.setIcon(new ImageIcon(fondoBU.getImage().getScaledInstance(boton.getWidth(),
						boton.getHeight(), Image.SCALE_SMOOTH)));
			}
			/*
			 * El metodo mouseExited aplica la función de cuando el cursor no este sobre el botón el icono pase a su 
			 * estado inicial.
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon fondoBU = new ImageIcon(getClass().getResource("/Imagene/BorrarBoton.png"));

				boton.setIcon(new ImageIcon(fondoBU.getImage().getScaledInstance(boton.getWidth(),
						boton.getHeight(), Image.SCALE_SMOOTH)));
			}

			/*
			 * Entra al panel de borrar usuario y cambia el idioma si es necesario
			 */
			public void mouseClicked(MouseEvent evt) {
				splash.setVisible(true);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						CapaDiseñoPanelBorrar borrar = new CapaDiseñoPanelBorrar(pac);
						inicio.setCambioIdioma(getCambio());
						if(inicio.getCambioIdioma()==true ){
							borrar.info.setText("¿Do you want delete this patient?");
							borrar.setCambio(inicio.getCambioIdioma());
						}
						splash.setVisible(false);
						nuevoPanel(borrar);
					}
				};
				t.schedule(ti, 6000);
			}
		});
		add(boton);
		ImageIcon edit = new ImageIcon(getClass().getResource("/Imagene/editar.png"));
		JButton btnEditar = new JButton("New button");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon edit = new ImageIcon(getClass().getResource("/Imagene/editarA.png"));

				btnEditar.setIcon(new ImageIcon(edit.getImage().getScaledInstance(btnEditar.getWidth(),
						btnEditar.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon edit = new ImageIcon(getClass().getResource("/Imagene/editar.png"));

				btnEditar.setIcon(new ImageIcon(edit.getImage().getScaledInstance(btnEditar.getWidth(),
						btnEditar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnEditar.setBackground(Color.RED);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(true);
				CapaDiseñoEditarInfo editarC=new CapaDiseñoEditarInfo(pac);
				TimerTask ti=new TimerTask() {
					@Override
					public void run() {
						inicio.setCambioIdioma(getCambio());
						if(inicio.getCambioIdioma()==true) {

							editarC.correoLabl.setText("Email:");
							editarC.siPositivo.setText("Yes");
							editarC.noPositivo.setText("No");
							editarC.noFactor.setText("No");
							editarC.siFactor.setText("Yes");
							editarC.id.setText("ID:");
							editarC.edad.setText("Age:");
							editarC.factorRiesgo.setText("Risk Factor:");
							editarC.positivoCovid.setText("Covid Positive:");
							editarC.provincia.setText("Residence Province:");
							editarC.nombre.setText("Name:");
							editarC.regis.setText("Edit Information");
							editarC.setCambio(inicio.getCambioIdioma());
						}
						splash.setVisible(false);
						nuevoPanel(editarC);
					}
				};
				t.schedule(ti, 2500);

			}
		});
		btnEditar.setBounds(10, 37, 80, 50);
		btnEditar.setIcon(new ImageIcon(edit.getImage().getScaledInstance(btnEditar.getWidth(), btnEditar.getHeight(), Image.SCALE_SMOOTH)));
		btnEditar.setOpaque(false);
		btnEditar.setBorder(null);
		btnEditar.setContentAreaFilled(false);
		add(btnEditar);



		JLabel setVacuna = new JLabel();
		setVacuna.setForeground(new Color(255, 204, 153));
		setVacuna.setBackground(Color.WHITE);
		setVacuna.setBounds(258, 471, 161, 14);
		setVacuna.setText(pac.getVacuna());
		add(setVacuna);



		JLabel setDiaCita = new JLabel();
		setDiaCita.setForeground(new Color(255, 204, 153));
		setDiaCita.setBackground(Color.WHITE);
		setDiaCita.setBounds(258, 428, 161, 14);
		setDiaCita.setText(pac.getFecha());
		add(setDiaCita);

		JLabel setLugarReside = new JLabel();
		setLugarReside.setForeground(new Color(255, 204, 153));
		setLugarReside.setBackground(Color.WHITE);
		setLugarReside.setBounds(281, 382, 261, 14);
		setLugarReside.setText(pac.getLugarResidencia());
		add(setLugarReside);


		JLabel setPositivoCovid = new JLabel();
		setPositivoCovid.setForeground(new Color(255, 204, 153));
		setPositivoCovid.setBackground(Color.WHITE);
		setPositivoCovid.setBounds(270, 339, 261, 14);
		setPositivoCovid.setText(pac.getCovid());
		add(setPositivoCovid);

		JLabel setFactorRiesgo = new JLabel();
		setFactorRiesgo.setForeground(new Color(255, 204, 153));
		setFactorRiesgo.setBackground(Color.WHITE);
		setFactorRiesgo.setBounds(270, 293, 261, 14);
		setFactorRiesgo.setText(pac.getFactorDeRiesgo());
		add(setFactorRiesgo);

		JLabel setEdad = new JLabel();
		setEdad.setForeground(new Color(255, 204, 153));
		setEdad.setBackground(Color.WHITE);
		setEdad.setBounds(235, 248, 261, 14);
		setEdad.setText(String.valueOf(pac.getEdad()));
		add(setEdad);

		JLabel setCedula = new JLabel();
		setCedula.setForeground(new Color(255, 204, 153));
		setCedula.setBackground(Color.WHITE);
		setCedula.setBounds(227, 202, 261, 14);
		setCedula.setText(pac.getCedula());
		add(setCedula);

		JLabel setNombre = new JLabel();
		setNombre.setForeground(new Color(255, 204, 153));
		setNombre.setBackground(Color.WHITE);
		setNombre.setBounds(227, 160, 261, 14);
		setNombre.setText(pac.getNombre());
		add(setNombre);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/salir.png"));

				btnRegresar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnRegresar.getWidth(),
						btnRegresar.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon back = new ImageIcon(getClass().getResource("/Imagene/backend.png"));

				btnRegresar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnRegresar.getWidth(),
						btnRegresar.getHeight(), Image.SCALE_SMOOTH)));
			}

		});
		btnRegresar.setBackground(Color.RED);
		btnRegresar.addActionListener(new ActionListener() {
			/*
			 * Envia al usuario de nuevo al inicio si es necesario
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
						nuevoPanel((JPanel) inicio.getContentPane());
					}
				};

				t.schedule(ti, 2500);
			}
		});
		btnRegresar.setBounds(399, 501, 89, 60);
		btnRegresar.setIcon(new ImageIcon(back.getImage().getScaledInstance(btnRegresar.getWidth(), btnRegresar.getHeight(), Image.SCALE_SMOOTH)));
		btnRegresar.setOpaque(false);
		btnRegresar.setBorder(null);
		btnRegresar.setContentAreaFilled(false);
		add(btnRegresar);

		fechaCita = new JLabel("Dia de su cita:");
		fechaCita.setForeground(Color.WHITE);
		fechaCita.setFont(new Font("Arial", Font.PLAIN, 14));
		fechaCita.setBounds(142, 423, 142, 22);
		add(fechaCita);

		vacunaCovid = new JLabel("Vacuna:");
		vacunaCovid.setForeground(Color.WHITE);
		vacunaCovid.setFont(new Font("Arial", Font.PLAIN, 14));
		vacunaCovid.setBounds(142, 466, 142, 22);
		add(vacunaCovid);

		lugarResi = new JLabel("Lugar de residencia:");
		lugarResi.setForeground(Color.WHITE);
		lugarResi.setFont(new Font("Arial", Font.PLAIN, 14));
		lugarResi.setBounds(142, 377, 142, 22);
		add(lugarResi);

		positivo = new JLabel("Positivo Covid-19:");
		positivo.setForeground(Color.WHITE);
		positivo.setFont(new Font("Arial", Font.PLAIN, 14));
		positivo.setBounds(142, 338, 119, 14);
		add(positivo);

		fac = new JLabel("Factor de riesgo:");
		fac.setForeground(Color.WHITE);
		fac.setFont(new Font("Arial", Font.PLAIN, 14));
		fac.setBounds(142, 292, 130, 14);
		add(fac);

		edad = new JLabel("Edad:");
		edad.setForeground(Color.WHITE);
		edad.setFont(new Font("Arial", Font.PLAIN, 14));
		edad.setBounds(142, 248, 45, 14);
		add(edad);

		cedula = new JLabel("Cedula:");
		cedula.setForeground(Color.WHITE);
		cedula.setFont(new Font("Arial", Font.PLAIN, 14));
		cedula.setBounds(142, 201, 61, 14);
		add(cedula);

		nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setForeground(Color.WHITE);
		nombre.setBounds(142, 159, 61, 14);
		add(nombre);

		info = new JLabel("Informaci\u00F3n Del Paciente");
		info.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 22));
		info.setForeground(Color.WHITE);
		info.setBounds(183, 81, 313, 43);
		add(info);
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 700, 650);
		fondo.setIcon(new ImageIcon(
				imagenFondo.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));

		add(fondo);


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
	 * 	Gets y Sets de idioma
	 */
	public void setCambio(boolean cambio) {
		this.cambio=cambio;
	}
	public boolean getCambio() {
		return cambio;
	}
}
