/**
 *
 */
package Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;

/**
 * @author Juan Miguel
 *
 */
public class Concesionario {
	//Atributos
	private String nombre;
	private List<Vehiculo> listaVehiculos= new ArrayList<>();
	private List<Administrador> listaAdministradores= new ArrayList<>();
	private List<Empleado> listaEmpleados= new ArrayList<>();
	private List<Transaccion> listaTransacciones= new ArrayList<>();
	private List<Cliente> listaClientes= new ArrayList<Cliente>();

	public Concesionario (){

	}

	public Concesionario(String nombre) {
		this.nombre = nombre;
		Administrador administrador1= new Administrador("Miguel", "florez", "1010", "1234");
		Administrador administrador2= new Administrador("Miguel", "florez", "2314", "12345");
		listaAdministradores.add(administrador1);




	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}


	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}


	public List<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}


	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	@Override
	public String toString() {
		return "Concesionario " + nombre +  "\nLista de Vehiculos: \n" + listaVehiculos +
				"\nLista de Administradores: \n" + listaAdministradores;
	}

//----------------------------CRUD DEL ADMINISTRADOR-------------------------------------------------------------------------

	/**
	 * Metodo que sirve para verificar si un administrador ya existe, un administrador a otro si tienen la misma cedula
	 * @param identificacion
	 * @return
	 */
	public boolean verificarAdministrador(String identificacion, String contrasenia){
		boolean verificado= false;
		for (Administrador administradorAux : listaAdministradores) {
			if(administradorAux.verificarIDContrasenia(identificacion, contrasenia)){
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}

	private boolean verificarAdministrador(String identificacion){
		boolean verificado= false;
		for (Administrador administradorAux : listaAdministradores) {
			if(administradorAux.verificarIdentificacion(identificacion)){
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}

	/**
	 * Metodo que obtiene un administrador en base a su cedula y sus credenciales(contrasenia)
	 * @param identificacion
	 * @return
	 */
	public Administrador obtenerAdministrador(String identificacion, String contrasenia){
		Administrador administradorEncontrado= null;
		for (Administrador administradorAux : listaAdministradores) {
			if (administradorAux.verificarIdentificacion(identificacion))
				administradorEncontrado= administradorAux;
		}
		return administradorEncontrado;
	}

	public Administrador obtenerAdministrador(String identificacion){
		Administrador administradorEncontrado= null;
		for (Administrador administradorAux : listaAdministradores) {
			if (administradorAux.verificarIdentificacion(identificacion))
				administradorEncontrado= administradorAux;
		}
		return administradorEncontrado;
	}

	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param credenciales
	 * @return
	 * @throws AdministradorException
	 */
	public boolean crearAdministrador(Administrador newAdministrador) throws AdministradorException{
		boolean creado= false;
		boolean administradorEncontrado= verificarAdministrador(newAdministrador.getIdentificacion());
		if(administradorEncontrado){
			creado=false;
			throw new  AdministradorException("El empleado ya se encuentra registrado");
		}else{
			creado=true;
			listaAdministradores.add(newAdministrador);
		}

		return creado;
	}

	/**
	 *
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 */
	public boolean eliminarAdministrador(Administrador administradorEliminar) throws AdministradorException {
		boolean eliminado= false;
		Administrador adminEncontrado= obtenerAdministrador(administradorEliminar.getIdentificacion());
		if (adminEncontrado==null) {
			throw new AdministradorException("El administrador que desea eliminar no se encuentra en el sistema");
		}else {
			eliminado=true;
			listaAdministradores.remove(adminEncontrado);

		}
		return eliminado;
	}
	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param credenciales
	 * @return
	 * @throws AdministradorException
	 */
	public boolean actualizarAdministrador(String nombre, String apellido, String identificacion, String credenciales) throws AdministradorException{
		boolean actualizado= false;
		Administrador administradorAux= obtenerAdministrador(identificacion);
		if (administradorAux== null) {
			throw new AdministradorException("El administrador no se ha encontrado");
		}
		actualizado= true;
		administradorAux.setNombre(nombre);
		administradorAux.setApellido(apellido);
		administradorAux.setIdentificacion(identificacion);
		administradorAux.setCredenciales(credenciales);

		return actualizado;
	}


//----------------------------------------------------CRUD EMPLEADO---------------------------------------------------------------------
	/**
	 * Metodo necesario para saber si el empleado existe en la lista general de los empleados
	 * @param identificacion
	 * @return
	 */
	public boolean verificarEmpleado(String identificacion){
		boolean verificado= false;
		for (Empleado empleadoAux : listaEmpleados) {
			if (empleadoAux.verificarIdentificacion(identificacion)) {
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}
	/**
	 *
	 * @param identificacion
	 * @return
	 */
	public Empleado obtenerEmpleado(String identificacion){
		Empleado empleado= null;
		for (Empleado empleadoAux : listaEmpleados) {
			if (empleadoAux.verificarIdentificacion(identificacion)) {
				empleado= empleadoAux;
			}
		}
		return empleado;
	}


	/**
	 * En este metodo inicialmente obtenemos un administrador, que es el que esta registrando al empleado, se verifica que este exista, si existe, este administrador va a registrar
	 * al empleado y lo añade a su lista de empleados, esto ya se realiza en los metodos de administrador
	 * Si la funcion administradorAux.crearEmpleado() retorna true, es que se ha podido crear satisfactoriamente el empleado
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws EmpleadoException
	 * @throws AdministradorException
	 */
	public boolean crearEmpleado(Administrador administradorEmpleado, Empleado newEmpleado) throws EmpleadoException,AdministradorException {
		boolean creado= false;
		Administrador administradorAux= administradorEmpleado;
		if (administradorAux==null) {
			throw new AdministradorException("El administrador encargado no se encuentra en la base de datos");
		}

		if(administradorAux.crearEmpleado(newEmpleado)){
			creado=true;
			this.listaEmpleados.add(newEmpleado);
		}else {//Preguntar si esta excepcion es necesaria teniendo en cuenta que ya se esta manejando en el metodo de crearEmpleado()
			throw new EmpleadoException("El empleado no ha podido ser registrado");
		}
		//return  administradorAux.crearEmpleado(nombre, apellido, identificacion)
		return creado;
	}
	/**
	 *
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 * @throws EmpleadoException
	 */
	public boolean  eliminarEmpleado(String identificacionAdmin,Empleado empleadoEliminar) throws AdministradorException, EmpleadoException {
		boolean eliminado= false;
		Administrador administradorAux= obtenerAdministrador(identificacionAdmin);
		if (administradorAux==null) {
			throw new AdministradorException("El administrador no se encuentra en la base de datos");
		}
		if (administradorAux.eliminarEmpleado(empleadoEliminar)) {
			eliminado=true;
			this.listaEmpleados.remove(empleadoEliminar);
		}
		//return administradorAux.eliminarEmpleado(identificacion);
		return eliminado;
	}

	/**
	 *
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 * @throws EmpleadoException
	 */

	public boolean actualizarEmpleado(String identificacionAdmin,String nombre, String apellido, String identificacion) throws AdministradorException, EmpleadoException{
		boolean actualizado= false;
		Administrador administradorAux= obtenerAdministrador(identificacionAdmin);
		if (administradorAux==null) {
			throw new AdministradorException("El administrador no se encuentra en la base de datos");
		}

		if (administradorAux.actualizarEmpleado(nombre, apellido, identificacion)) {
			actualizado=true;
		}

		//return administradorAux.actualizarEmpleado(nombre, apellido, identificacion)
		return actualizado;
	}

//-------------------------------------------------------CRUD CLIENTE--------------------------------------------------------------------------------------------

	/**
	 *
	 * @param identificacionEmpleado
	 * @param newCliente
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean crearCliente(String identificacionEmpleado, Cliente newCliente) throws EmpleadoException, ClienteException{
		boolean creado= false;
		Empleado empleadoCliente= obtenerEmpleado(identificacionEmpleado);
		if (empleadoCliente==null) {
			throw new EmpleadoException("El empleado no se encuentra registrado");
		}

		if (empleadoCliente.crearCliente(newCliente)) {
			creado=true;
			this.listaClientes.add(newCliente);
		}
		return creado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param clienteEliminar
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean eliminarCliente(String identificacionEmpleado, Cliente clienteEliminar) throws EmpleadoException, ClienteException{
		boolean eliminado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.eliminarCliente(clienteEliminar)) {
			eliminado= true;
			this.listaClientes.remove(clienteEliminar);
		}

		return eliminado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param fechaNacimiento
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean actualizarCliente(String identificacionEmpleado,String nombre, String apellido, String identificacion, String fechaNacimiento) throws EmpleadoException, ClienteException{
		boolean actualizado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}
		if (empleadoAux.actualizarCliente(nombre, apellido, identificacion, fechaNacimiento)) {
			actualizado=true;
		}


		return actualizado;

	}


}
