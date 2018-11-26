package unidades;


import Excepciones.*;
import contenibles.Contenible;
import estadosAtaque.ArmaCargada;
import estadosAtaque.ArmaDescargada;
import estadosAtaque.EstadosAtaque;
import juego.Jugador;

public class ArmaDeAsedio extends Accionables {
    EstadosAtaque estado;


    public int getVida() {
        return this.vida;
    }

    public ArmaDeAsedio(Jugador unJugador) {
        vida = 150;
        propietario = unJugador;
        estado = new ArmaDescargada();

    }


    public void realizarAccionCorrespondiente()  {
        this.estado.realizarAccion();
    }

    public void cargarArma() throws ArmaYaCargada, ArmaSeCargaEnSiguienteTurno {
        estado.cargarArma();
        estado = new ArmaCargada();
    }

    public void atacar(Contenible unContenible) throws ContenibleFueraDeRango, ContenibleDelMismoJugador, ExcedeLimiteDelMapa, AsedioNoAtacaUnidad, ArmaNoCargada {
        estado.ataqueListo();
        if (unContenible.calcularDistancia(this.posicion.getPosX(), this.posicion.getPosY()) > 3)
            throw new ContenibleFueraDeRango();
        if (unContenible.sonDelMismoJugador(this.propietario))
            throw new ContenibleDelMismoJugador();
        unContenible.ataqueDeAsedio();
    }
}