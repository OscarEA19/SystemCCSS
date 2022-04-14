package CapaDeDiseño;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.AWTEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class CapaDiseñoSplashScreen extends JFrame implements Runnable{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static  void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapaDiseñoSplashScreen frame = new CapaDiseñoSplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Thread t;
	public CapaDiseñoSplashScreen() {
		setType(Type.UTILITY);
		setSize(436,261);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setFocusable(true);
		setFocusableWindowState(true);
		t = new Thread(this);
		t.start();


		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 434, 261);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagene/fondoSplash.png"));
		ImageIcon carga = new ImageIcon(getClass().getResource("/Imagene/carga.gif"));
		ImageIcon cargalogo = new ImageIcon(getClass().getResource("/Imagene/CCSS.png"));
		JLabel logo = new JLabel("");
		logo.setBounds(27, 84, 101, 83);
		logo.setIcon(new ImageIcon(cargalogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(logo);
		JLabel lblNewLabel_1_1 = new JLabel("Version 1.0.0.0");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(333, 234, 101, 27);
		contentPane.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1 = new JLabel("Cargando.....");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(65, 223, 101, 27);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel = new JLabel("Sistema De Citas-CCSS");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		lblNewLabel.setBounds(138, 106, 296, 39);
		contentPane.add(lblNewLabel);
		JLabel imaCarga = new JLabel();
		imaCarga.setBounds(10, 209, 45, 41);
		imaCarga.setIcon(new ImageIcon(carga.getImage().getScaledInstance(imaCarga.getWidth(), imaCarga.getHeight(), Image.SCALE_DEFAULT)));
		contentPane.add(imaCarga);

		JLabel fondoIma = new JLabel();
		fondoIma.setBounds(0, 0, 434, 261);
		fondoIma.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(fondoIma.getWidth(), fondoIma.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondoIma);
	}

	@Override
	public void run() {
		while(t !=null) {
			try {
				Thread.sleep(10000);
				this.dispose();
				new CapaDiseñoPrincipal().setVisible(true);
				t=null;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
}

