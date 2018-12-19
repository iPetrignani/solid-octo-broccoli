package modelo.estructuras;

import modelo.espacio.Posicion;
import modelo.excepciones.OroInsuficiente;
import modelo.juego.Jugador;
import modelo.unidades.Aldeano;

import java.util.LinkedList;

public class PlazaCentral extends Estructura {
    private static final int PRECIO_ALDEANO = 25;

    public PlazaCentral(Jugador jugador) {
        vida = 450;
        vidaMaxima = 450;
        velocidadDeReparacion = 25;
        propietario = jugador;
        posiciones = new LinkedList<Posicion>();

    }

    public Aldeano crearAldeano(int oroDisponible) {
        if (oroDisponible < PRECIO_ALDEANO) throw new OroInsuficiente();
        propietario.restarOro(PRECIO_ALDEANO);
        this.propietario.aumentarPoblacion();
        return new Aldeano(this.propietario);
    }

}
