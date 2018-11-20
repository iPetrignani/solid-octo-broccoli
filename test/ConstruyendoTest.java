import Excepciones.AldeanoOcupado;
import Excepciones.CasilleroOcupado;
import Excepciones.ExcedeLimiteDelMapa;
import espacio.Mapa;
import estados.Construyendo;
import estados.GenerandoOro;
import estructuras.Cimiento;
import estructuras.Cuartel;
import juego.Jugador;
import org.junit.Test;
import unidades.Aldeano;

import static org.junit.Assert.assertEquals;

public class ConstruyendoTest {

    @Test
    public void testFinalizarConstruccionDesocupaAldeano () throws CasilleroOcupado, ExcedeLimiteDelMapa, AldeanoOcupado {
        Mapa unMapa = new Mapa (20, 20);
        Jugador unJugador = new Jugador(unMapa, 10, 0, null);
        Cuartel unCuartel = new Cuartel(null);
        Cimiento unCimiento = new Cimiento(unCuartel);
        Construyendo unEstado = new Construyendo(unCimiento);
        Aldeano unAldeano = new Aldeano(unJugador);

        unAldeano.comenzarCimientos(unCimiento);
        unEstado.realizarAccion(unAldeano);

        assertEquals(Construyendo.class, unAldeano.getEstado().getClass());

        unEstado.realizarAccion(unAldeano);
        unEstado.realizarAccion(unAldeano);
        unEstado.realizarAccion(unAldeano);


        assertEquals(GenerandoOro.class, unAldeano.getEstado().getClass());
    }
}
