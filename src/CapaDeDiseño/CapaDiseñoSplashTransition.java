package CapaDeDiseño;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.AWTEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class CapaDiseñoSplashTransition extends JFrame implements Runnable{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public  static void main(String[] args) {
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
	public CapaDiseñoSplashTransition() {
		setType(Type.UTILITY);
		setSize(426, 200);
		
		setLocationRelativeTo(null);
		
		
		setFocusable(true);
		setUndecorated(true);


		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagene/FondoCovid.jpg"));
		ImageIcon barra = new ImageIcon(getClass().getResource("/Imagene/BarraDeCarga.gif"));
		ImageIcon logoIma = new ImageIcon(getClass().getResource("/Imagene/CCSS.png"));
		JLabel lblNewLabel = new JLabel("Por Favor Espere...");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setBounds(119, 69, 242, 33);
		contentPane.add(lblNewLabel);
		JLabel logo = new JLabel();
		logo.setBounds(10, 11, 64, 55);
		logo.setIcon(new ImageIcon(logoIma.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(logo);
		JLabel barraCarga = new JLabel();
		barraCarga.setBounds(0, 167, 426, 33);
		barraCarga.setIcon(new ImageIcon(barra.getImage().getScaledInstance(barraCarga.getWidth(), barraCarga.getHeight(), Image.SCALE_DEFAULT)));
		contentPane.add(barraCarga);
		JLabel fondoL = new JLabel();
		fondoL.setBounds(0, 0, 426, 166);
		fondoL.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(fondoL.getWidth(), fondoL.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondoL);
	}

	@Override
	public void run() {
		while(t !=null) {
			try {
				
				Thread.sleep(2500);
				
				this.dispose();
				
				t=null;
				

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		

	}
}

