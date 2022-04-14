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
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JOptionPane;






public class CapaDatosCorreo {

	CapaDatosPaciente paciente;
	private String TO;
	private String SMTP_USERNAME = "citascovidccss@gmail.com";
	private String SMTP_PASSWORD = "193590Jaiguz";
	private String HOST = "smtp.gmail.com";
	private int PORT = 587;
	private String SUBJECT = "Comprobante de Vacunación Covid-19";

	/*
	 * Constructor que recibe todos los datos del paciente para hacer el comprobante
	 */
	public CapaDatosCorreo(CapaDatosPaciente paciente) {
		this.paciente=paciente;
	}

	/*
	 * Metodo que envia el mail al usuario
	 */
	public void sendEmail() throws Exception {

		/*
		 * Crea la conexión a Gmail y crea el inicio de la sesion
		 */
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth.login.disable", "true");  
		props.put("mail.smtp.starttls.enable", "false");  
		props.put("mail.smtp.starttls.required", "true");
		Session session = Session.getDefaultInstance(props);


		String imagePath = "C:\\TrabajosDeJava\\PruebaDeCodigo\\src\\firmaCCSS.jpeg";		//Crea la direccion de la imagen que usaremos como firma
		/*
		 * 	Se crea un cuerpo de texto primero agregamos en msgBodyPart la firma 
		 * mediante un content ID y le asignamos un id y
		 *  con la variable fds leemos el formato de la imagen
		 */
		MimeMultipart multipart = new MimeMultipart();
		BodyPart msgBodyPart = new MimeBodyPart();

		msgBodyPart.setContent(BODY(), "text/html; charset=utf-8");
		multipart.addBodyPart(msgBodyPart);
		msgBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(imagePath);
		msgBodyPart.setDataHandler(new DataHandler(fds));
		msgBodyPart.setHeader("Content-ID", "<img1>");
		msgBodyPart.setDisposition(MimeBodyPart.INLINE);
		multipart.addBodyPart(msgBodyPart);

		/*
		 * Se crea un mensaje de email y se agrega el usuario que lo envia el encabezado y la 
		 * direccion de correo que se paso cuando se estaba registrtando el usuario
		 * además de agregar el cuerpo del mensaje al texto que se enviara
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(SMTP_USERNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(getEmail()));
		msg.setSubject(SUBJECT);
		msg.setContent(multipart);
		
		Transport transport = session.getTransport();
		try
		{
			/*
			 * Se conecta al serivodr de email al usuario y su contraseña y hace el envio de este
			 */
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);    
			transport.sendMessage(msg, msg.getAllRecipients());
			

		}

		catch (Exception ex) {

			

		}

		finally

		{
			/*
			 * Se desconecta del servidor de email que se encuentra
			 */
			transport.close();


		}

	}
	/*
	 * Este metodo solo diseña mediante html y estilos en linea el cuerpo del mensaje
	 * 
	 */
	public String BODY() {
		String BODY = String.join(
				System.getProperty("line.separator"),
				"<body style='background:  linear-gradient(to top,#223,#129); width: 700px;height: 780px;'> ",

				"<header style='background: #34f; height: 40px; text-align: center; padding: 20px; color: #fff;'>",
				"CCSS-Programa Vacunas Covid-19",
				"</header>",
				"<div style='width: 150px; background:linear-gradient(to top,#555,#bbf); margin: 30px auto; padding: 35px; box-shadow:0px 20px 10px 10px;' >",

				"<p>",
				"Nombre: <b>"+paciente.getNombre()+"</b><br><br>",

				"Cedula: <b>"+paciente.getCedula()+"</b><br><br>",

				"Edad: <b>"+paciente.getEdad()+"</b><br><br>",
				"Factor de riesgo: <b>"+paciente.getFactorDeRiesgo()+"</b><br><br>",

				"Positivo de Covid 19: <b>"+paciente.getCovid()+"</b><br><br>",

				"Lugar de Residencia: <b>"+paciente.getLugarResidencia()+"</b><br><br>",

				"Día de la cita: <b>"+paciente.getFecha()+"</b><br><br>",

				"Vacuna: <b>"+paciente.getVacuna()+"</b><br><br>",
				"<b style='color:#0f0'>¡Te esperamos,no faltes!</b>",
				"</p>",

				"</div>",
				"<footer style='margin-top: 100px; '><img style='width:700px; ' src=\"cid:img1\"></footer>",


				"</body>"



				);
		return BODY;
	}
	//Sets y gets de envio de email
	public void setEmail(String to) {
		this.TO=to;
	}
	public String getEmail() {
		return this.TO;
	}


}


