package application;

import java.io.IOException;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Model.Administrador;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;
import controllers.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Aplicacion extends Application{

	//Solo puede existir una instancia de la clase principal, en este caso tenemos una sola intancia
	//de la clase biblioteca
	private Concesionario concesionario;
	private Stage primaryStage;

	Administrador administrador1= new Administrador("Miguel", "florez", "1010", "1234");
	Administrador administrador2= new Administrador("Miguel", "florez", "2314", "12345");

	Empleado empleado1= new Empleado("Juan", "Vasquez", "0000");
	Empleado empleado2= new Empleado("Santi", "Ovalei", "1111");


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage= primaryStage;
		this.concesionario= new Concesionario();
		primaryStage.setTitle("Concesionario");
		mostrarVentanaPrincipal();

		concesionario.getListaAdministradores().add(administrador1);
		concesionario.getListaAdministradores().add(administrador2);

		concesionario.getListaEmpleados().add(empleado1);
		concesionario.getListaEmpleados().add(empleado2);

	}

	private void mostrarVentanaPrincipal() throws IOException {
		//Se establece la ruta de la ventana que desea ejecutar
		try{
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("../Views/PrincipalView.fxml"));
			AnchorPane anchorPane= (AnchorPane)loader.load();
			PrincipalController principalController = loader.getController();
			principalController.setAplicacion(this);

			Scene scene= new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			PrincipalController controller= loader.getController();
			controller.setStage(primaryStage);
		}catch(IOException e){

			}
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);

	}


//-----------------------Metodos del Administrador---------------------------------------

	public boolean verificarAdmin(String identificacion, String contrasenia){
		return concesionario.verificarAdministrador(identificacion, contrasenia);
	}
	public Administrador obtenerAdministrador(String identificacion, String contrasenia){
		return concesionario.obtenerAdministrador(identificacion, contrasenia);
	}

	public boolean crearAdministrador(String nombre, String apellido, String cedula, String contrasenia) throws AdministradorException {
		Administrador nuevoAdministrador= new Administrador(nombre, apellido, cedula, contrasenia);
		return concesionario.crearAdministrador(nuevoAdministrador);
	}

	public boolean eliminarAdministrador(Administrador adminEliminar) throws AdministradorException{
		return concesionario.eliminarAdministrador(adminEliminar);
	}

	public boolean actualizarAdmin(String nombre, String apellido, String cedula, String contrasenia) throws AdministradorException {
		return concesionario.actualizarAdministrador(nombre, apellido, cedula, contrasenia);
	}



//------------------------Metodos CRUD del cliente---------------------------------------
	public boolean crearCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException {
		Cliente newCliente= new Cliente(nombre, apellido, cedula, fechaNacimiento);
		return concesionario.crearCliente(idEmpleado, newCliente);
	}


	public boolean eliminarCliente(String idEmpleado, Cliente clienteEliminar) throws EmpleadoException, ClienteException{
		return concesionario.eliminarCliente(idEmpleado, clienteEliminar);
	}


	public boolean actualizarCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException{
		return concesionario.actualizarCliente(idEmpleado, nombre, apellido, cedula, fechaNacimiento);
	}


//------------------------Metodos CRUD del empleado----------------------------------------

	public boolean verificarEmpleado(String identificacion){
		return concesionario.verificarEmpleado(identificacion);
	}

	public Empleado obtenerEmpleado(String identificacion) {
		return concesionario.obtenerEmpleado(identificacion);
	}

	public boolean crearEmpleado(String identificacionAdmin,String contraseniaAdmin,String nombre, String apellido, String identificacion) throws EmpleadoException, AdministradorException {
		Empleado empleadoNuevoEmpleado= new Empleado(nombre, apellido, identificacion);
		Administrador administradorEmpleado= concesionario.obtenerAdministrador(identificacionAdmin,contraseniaAdmin);

		return concesionario.crearEmpleado(administradorEmpleado, empleadoNuevoEmpleado);
	}

	public boolean eliminarEmpleado (String identificacionAdmin, Empleado empleadoEliminar) throws AdministradorException, EmpleadoException {
		return concesionario.eliminarEmpleado(identificacionAdmin, empleadoEliminar);
	}

	public boolean actualizarEmpleado(String idAdmin, String nombre, String apellido, String cedula) throws AdministradorException, EmpleadoException{
		return concesionario.actualizarEmpleado(idAdmin, nombre, apellido, cedula);
	}


}
