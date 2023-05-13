package controllers;

import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Model.Administrador;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;

public class Singleton {

	Concesionario concesionario= null;

	private static class SingletonHolder{
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static Singleton eINSTANCE= new Singleton();
	}


	public static Singleton getInstance(){
		return SingletonHolder.eINSTANCE;
	}

	public Singleton(){
		inicializarDatos();
	}


	private void inicializarDatos() {
		concesionario = new Concesionario("UQ");
		Administrador administrador1= new Administrador("Miguel", "florez", "1010", "1234");
		Administrador administrador2= new Administrador("Miguel", "florez", "2314", "12345");

		Empleado empleado1= new Empleado("Juan", "Vasquez", "0000");
		Empleado empleado2= new Empleado("Santi", "Ovalei", "1111");

		concesionario.getListaAdministradores().add(administrador1);
		concesionario.getListaAdministradores().add(administrador2);

		concesionario.getListaEmpleados().add(empleado1);
		concesionario.getListaEmpleados().add(empleado2);


	}


	public Concesionario getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
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
