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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class CapaDiseñoPrincipal extends JFrame {
	
	CapaDiseñoIdioma idioma = new CapaDiseñoIdioma();
	private boolean cambioIdioma;
	JLabel lblSistemasDeCitas;						
	JLabel buscarP;
	JLabel registroP;
	private JPanel Panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapaDiseñoPrincipal frame = new CapaDiseñoPrincipal();	
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor el cual crea el frame principal
	 * Contiene todas las entradas a los diferentes objeto necesarios del programa
	 */
	public CapaDiseñoPrincipal() {
		
		setIconImage(new ImageIcon(getClass().getResource("/Imagene/CCSS.png")).getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 650);
		setTitle("CCSS-Citas Covid-19");		//Establece la información y medidas del Frame
		setLocationRelativeTo(null);
		setResizable(false);
		Panel = new JPanel();
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Panel.setBounds(0, 0, 700, 650);
		setContentPane(Panel);
		Panel.setLayout(null);

		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagene/Fondo.jpg"));

		ImageIcon mostrar = new ImageIcon(getClass().getResource("/Imagene/consulta.png"));	//	Contienen las imagenes a utilizar

		ImageIcon logo = new ImageIcon(getClass().getResource("/Imagene/CCSS.png"));

		ImageIcon fondoB = new ImageIcon(getClass().getResource("/Imagene/Idioma.png"));

		CapaDiseñoRegistrarPaciente registrarPacientes = new CapaDiseñoRegistrarPaciente();

		buscarP = new JLabel("Buscar Paciente");
		buscarP.setForeground(Color.WHITE);
		buscarP.setFont(new Font("Arial", Font.PLAIN, 16));
		buscarP.setBounds(442, 233, 122, 19);
		Panel.add(buscarP);

		registroP = new JLabel("Registrar Paciente");							//JLabels que contienen información
		registroP.setForeground(Color.WHITE);
		registroP.setFont(new Font("Arial", Font.PLAIN, 16));
		registroP.setBounds(108, 233, 143, 19);
		Panel.add(registroP);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setToolTipText("System made by:Jairo,\u00D3scar,Jos\u00E9,Samuel,Guillermo");
		lblNewLabel_1.setBounds(63, 23, 107, 94);
		lblNewLabel_1.setIcon(new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel_1.getWidth(),
				lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH)));

		Panel.add(lblNewLabel_1);

		JButton btnMostrarInformacinDel = new JButton();
		btnMostrarInformacinDel.addMouseListener(new MouseAdapter() {
			/*
			 * El metodo mouseEntered aplica la función de cuando el cursor
			 * este sobre el boton cambie a otro icono el cual se declara en la variable mostrar
			 */
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	ImageIcon mostrar = new ImageIcon(getClass().getResource("/Imagene/search.png"));
            	
            	btnMostrarInformacinDel.setIcon(new ImageIcon(mostrar.getImage().getScaledInstance(btnMostrarInformacinDel.getWidth(),
						btnMostrarInformacinDel.getHeight(), Image.SCALE_SMOOTH)));
            }
            /*
             * El metodo mouseExited aplica la función de cuando el cursor no este sobre el botón el icono pase a su 
             * estado inicial.
             */
            @Override
            public void mouseExited(MouseEvent e) {
            	ImageIcon mostrar = new ImageIcon(getClass().getResource("/Imagene/consulta.png"));
            	
            	btnMostrarInformacinDel.setIcon(new ImageIcon(mostrar.getImage().getScaledInstance(btnMostrarInformacinDel.getWidth(),
            			btnMostrarInformacinDel.getHeight(), Image.SCALE_SMOOTH)));
            }
            
        });
		btnMostrarInformacinDel.setBackground(Color.RED);
		btnMostrarInformacinDel.setBounds(438, 258, 138, 114);				//funciones para establecer el tamaño y diseño de los botones 
		btnMostrarInformacinDel.setOpaque(false);							//en el programa se presentan muchas funciones de este tipo
		btnMostrarInformacinDel.setBorder(null);
		btnMostrarInformacinDel.setContentAreaFilled(false);
		
		btnMostrarInformacinDel
		.setIcon(new ImageIcon(mostrar.getImage().getScaledInstance(btnMostrarInformacinDel.getWidth(),
				btnMostrarInformacinDel.getHeight(), Image.SCALE_SMOOTH)));

		btnMostrarInformacinDel.addActionListener(new ActionListener() {
			/*
			 * Este metodo indica que cuando el usuario haga click
			 * se llamará al Panel buscador y además si el idioma
			 * es inglés que cambie todo el texto del programa.
			 */
			public void actionPerformed(ActionEvent e) {
				
				CapaDiseñoPanelBusc buscador = new CapaDiseñoPanelBusc();
				if (getCambioIdioma() == true) {

					buscador.info.setText("Enter the ID number");
					buscador.cedu.setText("ID: ");

					buscador.setCambio(getCambioIdioma());
				}
				nuevoPanel(buscador);

			}
		});

		Panel.add(btnMostrarInformacinDel);

		lblSistemasDeCitas = new JLabel("Sistema De Citas-CCSS");

		lblSistemasDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemasDeCitas.setForeground(Color.WHITE);
		lblSistemasDeCitas.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 24));
		lblSistemasDeCitas.setBounds(180, 39, 343, 34);
		Panel.add(lblSistemasDeCitas);
		JButton btnIdioma = new JButton("");
		btnIdioma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	ImageIcon idioma = new ImageIcon(getClass().getResource("/Imagene/lang.png"));
            	
            	btnIdioma.setIcon(new ImageIcon(idioma.getImage().getScaledInstance(btnIdioma.getWidth(),
            			btnIdioma.getHeight(), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	ImageIcon idioma = new ImageIcon(getClass().getResource("/Imagene/Idioma.png"));
            	
            	btnIdioma.setIcon(new ImageIcon(idioma.getImage().getScaledInstance(btnIdioma.getWidth(),
            			btnIdioma.getHeight(), Image.SCALE_SMOOTH)));
            }
            
        });
		btnIdioma.setBackground(Color.RED);
		btnIdioma.addActionListener(new ActionListener() {
			/*
			 * Este metodo llama al panel Idioma 
			 */
			public void actionPerformed(ActionEvent e) {
				
				nuevoPanel(idioma);

			}
		});
		btnIdioma.setOpaque(false);
		btnIdioma.setBorder(null);
		btnIdioma.setBounds(624, 11, 49, 44);
		btnIdioma.setContentAreaFilled(false);
		btnIdioma.setIcon(new ImageIcon(
				fondoB.getImage().getScaledInstance(btnIdioma.getWidth(), btnIdioma.getHeight(), Image.SCALE_SMOOTH)));
		Panel.add(btnIdioma);

		JLabel lblCajaCostarrincenseSeguro = new JLabel("Caja Costarricense Seguro Social");
		lblCajaCostarrincenseSeguro.setForeground(Color.WHITE);
		lblCajaCostarrincenseSeguro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCajaCostarrincenseSeguro.setBounds(10, 582, 202, 23);
		Panel.add(lblCajaCostarrincenseSeguro);

		JLabel lblVersion = new JLabel("Version 1.0.0.0");
		lblVersion.setForeground(Color.WHITE);
		lblVersion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVersion.setBounds(10, 557, 93, 23);
		Panel.add(lblVersion);

		JLabel copyRight = new JLabel("\u00A9 2021 CCSS");
		copyRight.setFont(new Font("Arial", Font.PLAIN, 12));
		copyRight.setForeground(Color.WHITE);
		copyRight.setBounds(591, 576, 93, 34);
		Panel.add(copyRight);

		ImageIcon fondoBoton = new ImageIcon(getClass().getResource("/Imagene/register.png"));
		JButton RegistrarPaciente = new JButton();
		
		RegistrarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	ImageIcon fondoBoton = new ImageIcon(getClass().getResource("/Imagene/user.png"));
				
				RegistrarPaciente.setIcon(new ImageIcon(fondoBoton.getImage().getScaledInstance(RegistrarPaciente.getWidth(),
						RegistrarPaciente.getHeight(), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	ImageIcon fondoBoton = new ImageIcon(getClass().getResource("/Imagene/register.png"));
				
				RegistrarPaciente.setIcon(new ImageIcon(fondoBoton.getImage().getScaledInstance(RegistrarPaciente.getWidth(),
						RegistrarPaciente.getHeight(), Image.SCALE_SMOOTH)));
            }
            
			
        });

		RegistrarPaciente.setBackground(Color.RED);
		RegistrarPaciente.setBounds(108, 258, 138, 114);
		RegistrarPaciente.setOpaque(false);
		RegistrarPaciente.setBorder(null);
		RegistrarPaciente.setContentAreaFilled(false);
		RegistrarPaciente.setIcon(new ImageIcon(fondoBoton.getImage().getScaledInstance(RegistrarPaciente.getWidth(),
				RegistrarPaciente.getHeight(), Image.SCALE_SMOOTH)));
		RegistrarPaciente.addActionListener(new ActionListener() {
			/*
			 * El método llama al panel de registro en caso de que el idioma sea inglés cambiará
			 * todos sus títulos 
			 */
			public void actionPerformed(ActionEvent e) {

				if (getCambioIdioma() == true) {

					registrarPacientes.correoLabl.setText("Email:");
					registrarPacientes.siPositivo.setText("Yes");
					registrarPacientes.noPositivo.setText("No");
					registrarPacientes.noFactor.setText("No");
					registrarPacientes.siFactor.setText("Yes");
					registrarPacientes.id.setText("ID:");
					registrarPacientes.edad.setText("Age:");
					registrarPacientes.factorRiesgo.setText("Risk Factor:");
					registrarPacientes.positivoCovid.setText("Covid Positive:");
					registrarPacientes.provincia.setText("Residence Province:");
					registrarPacientes.nombre.setText("Name:");
					registrarPacientes.regis.setText("Register Patient");
					registrarPacientes.setCambio(getCambioIdioma());
				}

				nuevoPanel(registrarPacientes);
			}
		});

		Panel.add(RegistrarPaciente);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 700, 650);
		fondo.setIcon(new ImageIcon(
				imagen.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));

		Panel.add(fondo);
	}
	/*
	 * Remueve el panel que este y llama a un panel nuevo.
	 */
	public void nuevoPanel(JPanel panelNuevo) {
		Panel.removeAll();
		Panel.add(panelNuevo);
		Panel.repaint();
		Panel.revalidate();

	}
	/*
	 * Set y get de las variables que cambian el idioma del programa ya que esta encapsulada la variable
	 */
	public void setCambioIdioma(boolean cambioIdioma) {
		this.cambioIdioma = cambioIdioma;
	}

	public boolean getCambioIdioma() {
		return cambioIdioma;
	}

}
