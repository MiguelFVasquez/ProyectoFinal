package Model;
/**
 * Vehiculos es la clase padre de todo lo relacionado con los vehiculos
 */
public class Vehiculo {
    //Atributos
    private String marca;
    private String modelo;
    private String cambios;
    private double velMaxima;
    private String cilindraje;
    private TipoTransmicion transmicion;
    private TipoCombustible combustible;
    private TipoEstado estado;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
    		TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.cambios = cambios;
        this.velMaxima = velMaxima;
        this.cilindraje = cilindraje;
        this.transmicion= transmicion;
        this.combustible= combustible;
        this.estado= estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

    public double getVelMaxima() {
        return velMaxima;
    }

    public void setVelMaxima(double velMaxima) {
        this.velMaxima = velMaxima;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }



    public TipoTransmicion getTransmicion() {
		return transmicion;
	}

	public void setTransmicion(TipoTransmicion transmicion) {
		this.transmicion = transmicion;
	}

	public TipoCombustible getCombustible() {
		return combustible;
	}

	public void setCombustible(TipoCombustible combustible) {
		this.combustible = combustible;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}

	@Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nCambios: " + cambios + "\nVelocidad maxima: " + velMaxima
                + "\nCilindraje: " + cilindraje+ "\nTipo de combustible: "+ combustible+ "\nTipo de transmicion: "+ transmicion
                + "\nEstado: "+ estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
        Vehiculo other = (Vehiculo) obj;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        return true;
    }





}
