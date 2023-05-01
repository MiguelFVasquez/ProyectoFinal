package Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ClienteException;

public class Empleado extends Persona {

	private List<Transaccion> listaTransaccion= new ArrayList<>();
	private List<Cliente> listaClientes= new ArrayList<>();

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


}
