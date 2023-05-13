package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.ClienteException;
import Exceptions.VehiculoException;

public class Empleado extends Persona {

	private List<Transaccion> listaTransaccion= new ArrayList<>();
	private List<Cliente> listaClientes= new ArrayList<>();
	private List<Vehiculo> listaVehiculos= new ArrayList<>();
	public Empleado() {
	}

	public Empleado(String nombre, String apellido, String identificacion) {
		super(nombre, apellido, identificacion);
	}

	public List<Transaccion> getListaTransaccion() {
		return listaTransaccion;
	}

	public void setListaTransaccion(List<Transaccion> listaTransaccion) {
		this.listaTransaccion = listaTransaccion;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


	public boolean verificarIdentificacion(String identificacion){
		return this.getIdentificacion().equals(identificacion);
	}


	//CRUD CLIENTE-------------------------------------------------------
	/**
	 *
	 * @param identificacion
	 * @return
	 */
	private boolean verificarCliente(String identificacion){
		boolean verificado= false;
		for (Cliente clienteAux : listaClientes) {
			if(clienteAux.verificarIdentificacion(identificacion)){
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
	private Cliente obtenerCliente(String identificacion) {
		Cliente clienteEncontrado= null;
		for (Cliente clienteAux : listaClientes) {
			if(clienteAux.verificarIdentificacion(identificacion))
				clienteEncontrado= clienteAux;

		}
		return clienteEncontrado;
	}

	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param fechaNacimiento
	 * @return
	 * @throws ClienteException
	 */
	public boolean crearCliente(Cliente newCliente)throws ClienteException{
		boolean creado= true;
		boolean clienteEncontrado= verificarCliente(newCliente.getIdentificacion());
		if (clienteEncontrado) {
			creado= false;
			throw new ClienteException("El cliente ya se encuentra registrado");
		}
		listaClientes.add(newCliente);

		return creado;
	}

	/**
	 *
	 * @param identificacion
	 * @return
	 * @throws ClienteException
	 */
	public boolean eliminarCliente(Cliente clienteEliminar) throws ClienteException {
		boolean eliminado= false;
		Cliente clienteEncontrado= obtenerCliente(clienteEliminar.getIdentificacion());
		if (clienteEncontrado==null) {
			throw new ClienteException("El cliente que desea eliminar no se encuentra registrado");
		}else {
			eliminado= true;
			listaClientes.remove(clienteEncontrado);
		}
		return eliminado;
	}
	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param fechaNacimiento
	 * @return true, si se pudo actualizar correctamente el cliente
	 * @return false, si no se puede actualiza el cliente, eso se daria porque el cliente no existe
	 * @throws ClienteException
	 */
	public boolean actualizarCliente(String nombre, String apellido, String identificacion, String fechaNacimiento) throws ClienteException {
		boolean actualizado = false;
		Cliente clienteAux= obtenerCliente(identificacion);
		if (clienteAux==null) {
			throw new ClienteException("El cliente no ha sido encontrado");
		}
		actualizado=true;
		clienteAux.setNombre(nombre);
		clienteAux.setApellido(apellido);
		clienteAux.setIdentificacion(identificacion);

		return actualizado;
	}
//-----------------------------------------------------------CRUD VEHICULOS----------------------------------------------------------------------------------
	//Verifica si un vehiculo es igual a otro dependiendo de si es la misma marca y modelo
	//Se "Clona" la lista de vehiculos para almacenar el resultado del stream en esta lista
	//Si la lista NO está vacia es que se encontraron elementos con la misma marca y modelo, por lo que retornaria true

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @return true si la lista "vehiculosCoincidente" NO está vacia
	 * @return false si la lista está vacia, lo que significa que ningun elemento de la lista tiene el mismo nombre ni mismo modelo
	 */
	private boolean verificarVehiculo(String marca, String modelo){
	    boolean encontrado = false;
	    List<Vehiculo> vehiculosCoincidentes = this.listaVehiculos.stream()
	            .filter(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo))
	            .collect(Collectors.toList());

	    if(!vehiculosCoincidentes.isEmpty()) {
	    	encontrado = true;
	    }
	    return encontrado;
	}

	/**
	 * Se obtiene recorre la lista de vehiculos con un flujo de stream, esto retorna null si no encuentra ningun vehiculo que cumpla con las condiciones dadas
	 * O si no retorna la funcion el primer objeto que encuentre con esas condiciones
	 * @param marca
	 * @param modelo
	 * @return
	 */
	private Vehiculo obtenerVehiculo(String marca, String modelo) {
		return (Vehiculo) listaVehiculos.stream().filter(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo));

	}

	/**
	 * Se recibe una intancia de moto y se verifica que no exista ya en la lista de vehiculos
	 * @param moto
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearMoto(Vehiculo moto) throws VehiculoException{
		boolean creado= false;
		if (verificarVehiculo(moto.getMarca(), moto.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuentra registrado");
		}else {
			creado= true;
			listaVehiculos.add(moto);
		}

		return creado;
	}
	/**
	 *
	 * @param sedan
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearSedan(Vehiculo sedan) throws VehiculoException{
		boolean creado= false;
		if (verificarVehiculo(sedan.getMarca(), sedan.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuentra registrado");
		}else {
			creado= true;
			listaVehiculos.add(sedan);
		}

		return creado;
	}

	/**
	 *
	 * @param deportivo
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearDeportivo(Vehiculo deportivo) throws VehiculoException{
		boolean creado= false;
		if (verificarVehiculo(deportivo.getMarca(), deportivo.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(deportivo);
		}
		return creado;
	}

	/**
	 *
	 * @param camioneta
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearCamioneta(Vehiculo camioneta) throws VehiculoException{
		boolean creado= false;
		if (verificarVehiculo(camioneta.getMarca(), camioneta.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(camioneta);
		}
		return creado;
	}

	/**
	 *
	 * @param pickUp
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearPickUp(Vehiculo pickUp) throws VehiculoException {
		boolean creado= false;
		if (verificarVehiculo(pickUp.getMarca(), pickUp.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(pickUp);
		}
		return creado;

	}
	/**
	 *
	 * @param van
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearVan(Vehiculo van) throws VehiculoException {
		boolean creado= false;
		if (verificarVehiculo(van.getMarca(), van.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(van);
		}
		return creado;
	}
	/**
	 *
	 * @param bus
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearBus(Vehiculo bus) throws VehiculoException {
		boolean creado= false;
		if (verificarVehiculo(bus.getMarca(), bus.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(bus);
		}
		return creado;
	}
	/**
	 *
	 * @param camion
	 * @return
	 * @throws VehiculoException
	 */
	public boolean crearCamion(Vehiculo camion) throws VehiculoException {
		boolean creado= false;
		if (verificarVehiculo(camion.getMarca(), camion.getModelo())) {
			throw new VehiculoException("El vehiculo ya se encuetra registrado");
		}
		else {
			creado= true;
			listaVehiculos.add(camion);
		}
		return creado;
	}

	/**
	 *
	 * @param vehiculoEliminar
	 * @return
	 * @throws VehiculoException
	 */
	public boolean eliminarVehiculo(Vehiculo vehiculoEliminar) throws VehiculoException {
		boolean eliminado = false;
		Vehiculo vehiculoEncontrado= obtenerVehiculo(vehiculoEliminar.getMarca(), vehiculoEliminar.getModelo());
		if (vehiculoEncontrado==null) {
			throw new VehiculoException("El vehiculo que desea eliminar no se encuentra registrado");
		}else {
			eliminado= true;
			listaVehiculos.remove(vehiculoEncontrado);
		}

		return eliminado;
	}

}
