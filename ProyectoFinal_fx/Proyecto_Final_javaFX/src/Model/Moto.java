package Model;

import java.io.Serializable;

public class Moto extends Vehiculo implements Serializable {

    public Moto() {
    }

	public Moto(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
	}



}
