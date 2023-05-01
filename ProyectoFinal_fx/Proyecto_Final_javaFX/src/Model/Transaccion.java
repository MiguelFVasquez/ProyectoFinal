package Model;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {
	private String fecha;
	private double total;
	private String codigo;
	private List<DetalleTransaccion> listaDetalles= new ArrayList<>();
	/**
	 * @param fecha
	 * @param total
	 * @param codigo
	 */
	public Transaccion(String fecha, double total, String codigo) {
		this.fecha = fecha;
		this.total = total;
		this.codigo = codigo;
	}
	/**
	 *
	 */
	public Transaccion() {
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<DetalleTransaccion> getListaDetalles() {
		return listaDetalles;
	}
	public void setListaDetalles(List<DetalleTransaccion> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}
	@Override
	public String toString() {
		return "Transaccion \nFecha: " + fecha + "\nTotal: " + total + "\nCodigo: " + codigo + "\nlista Detalles:"
				+ listaDetalles;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



}
