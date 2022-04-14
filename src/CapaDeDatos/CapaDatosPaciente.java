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
package CapaDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CapaDeDiseño.CapaDiseñoRegistrarPaciente;

public class CapaDatosPaciente  {

	private String nombre;
	private String cedula;
	private int edad;
	private String factorDeRiesgo;
	private String covid;
	private String lugarResidencia;
	private String vacuna;
	private String fecha;
	private Connection con;
	




	/*
	 * Envia la informacion proporcionada del paciente a la base
	 */
	public void RegistroBaseDeDatos() {
		/*
		 * Se conecta a la direccion de la base de datos
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		/*
		 * se conecta al localhost del usuario y envia los datos proporcionados a 
		 * la base de datos
		 */
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost/registrodepacientes","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("INSERT INTO patients VALUES('"+getNombre()+"','"+getCedula()+"','"+getEdad()+"','"+getFactorDeRiesgo()+"','"+getCovid()+"','"+getLugarResidencia()+"','"+getVacuna()+"','"+getFecha()+"')");
			JOptionPane.showMessageDialog(null, "Se realizo el registro exitosamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar el registro");
			e.printStackTrace();
			
		}

	}
	/*
	 * Retirna de la base de datos la informacion correspondiente a la cedula proporcionada
	 */
	public void RetornoBaseDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontro base de datos");
		}		
		try {


			con= DriverManager.getConnection("jdbc:mysql://localhost/registrodepacientes","root","");
			Statement stmt=con.createStatement();
			ResultSet rs= stmt.executeQuery("SELECT * FROM  patients  WHERE  cedula="+getCedula());



			if(rs.next()==true) {

				setNombre(rs.getString("nombre"));
				setCedula(rs.getString("cedula"));
				setEdad(Integer.parseInt(rs.getString("edad")));
				setFactorDeRiesgo(rs.getString("factorDeRiesgo"));
				setCovid(rs.getString("Covid"));
				setLugarResidencia(rs.getString("LugarResidencia"));
				setVacuna(rs.getString("Vacuna"));
				setFecha(rs.getString("fecha"));

			}else {
				JOptionPane.showMessageDialog(null,"Lo sentimos el numero de documento marcado no existe");
			}
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
			
		}

	}
	/*
	 * Borra la informacion de la cedula que se consulto
	 */
	public void borrarDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException E) {
			// TODO Auto-generated catch block
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontró base de datos");
		}		
		try {


			con= DriverManager.getConnection("jdbc:mysql://localhost/registrodepacientes","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE from  patients  WHERE  cedula="+getCedula());
			JOptionPane.showMessageDialog(null,"Se borraron los datos con exito");

		}catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "La cédula no coincide con ninguno de nuestros pacientes.");
		}
	}
	/*
	 * Edita la informacion y la vuelve a enviar a la base de datos
	 */
	public void editarDatos() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException E) {
			// TODO Auto-generated catch block
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontró base de datos");
		}		
		try {


			con= DriverManager.getConnection("jdbc:mysql://localhost/registrodepacientes","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE patients SET `nombre`='"+getNombre()+"',`edad`='"+getEdad()+"',`factorDeRiesgo`='"+getFactorDeRiesgo()+"',`Covid`='"+getCovid()+"',`LugarResidencia`='"+getLugarResidencia()+"',`Vacuna`='"+getVacuna()+"'  WHERE cedula=" +getCedula());
			

		}catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}

	//Sets y gets de las propiedades
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setCedula(String cedula) {
		this.cedula=cedula;
	}


	public void setEdad(int edad) {
		this.edad=edad;
	}
	public void setFactorDeRiesgo(String factorDeRiesgo) {
		this.factorDeRiesgo=factorDeRiesgo;
	}

	public void setCovid(String covid) {
		this.covid=covid;
	}

	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia=lugarResidencia;
	}
	public void setVacuna(String vacuna) {
		this.vacuna=vacuna;
	}
	public void setFecha(String fecha) {
		this.fecha=fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}


	public int getEdad() {
		return edad;
	}

	public String getFactorDeRiesgo() {
		return factorDeRiesgo;
	}

	public String getCovid() {
		return covid;
	}

	public String getLugarResidencia() {
		return lugarResidencia;
	}
	public String getVacuna() {
		return vacuna;
	}
	public String getFecha() {
		return fecha;
	}
}