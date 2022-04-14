/*
 * Autores del Proyecto: Oscar Espinoza,Jairo Guzm�n,Jos� Ruiz,Samuel Velazquez,Guillermo Solano
 * 
 * Fecha de Inicio:22/5/2021
 * 
 * Fecha de Conclusi�n: 7/8/2021
 * 
 * Titulo: Sistema de Citas-CCSS
 * 
 * Objetivo del proyecto: Este proyecto fue creado con el objetivo de mejorar el sistema de
 * asignaci�n de citas para la vacunaci�n del Covid-19, ya que no existe un orden especifico para
 * la asignaci�n de las citas lo que imposibilita la aceleraci�n del proceso
 * 
 * Descripci�n: El sistema cuenta con tres opciones en su panel principal las cuales son:
 * Registrar Paciente,Consulta de Paciente y Cambio de Idioma,l panel Registrar
 * contiene los campos a llenar de la informacion del paciente lo que enviara el reporte,
 * tanto a la base de datos como un correo informativo al paciente sobre su cita de 
 * vacunaci�n, la opcion de consulta, retorna la informaci�n del paciente en el sistema en 
 * este mismo panel se puede eliminar la informaci�n de este paciente si es el caso,
 * la opci�n de idioma hace un cambio de espa�ol a ingles de todas las partes del sistema.
 * 
 */
package CapaDeDatos;

public class CapaDatosVacuna {

	CapaDatosPaciente pac;
	/*
	 * Constructor que recibe los datos necesarios para cambiar el idioma
	 */
	public CapaDatosVacuna(CapaDatosPaciente pac) {
		this.pac=pac;
	}


	/*
	 * Asigna las vacunas dependiendo si tiene factor de riesgo o la edad supera los 50 a�os
	 */
	public void asignacionVacuna() {

		if(this.pac.getEdad()>=50 || this.pac.getFactorDeRiesgo()=="SI") {
			this.pac.setVacuna("Pfizer");
		}else {
			this.pac.setVacuna("Astra Zeneca");
		}


	}



}
