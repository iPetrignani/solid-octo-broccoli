package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.espacio.Contenible;
import modelo.espacio.Posicion;
import modelo.estructuras.Estructura;
import modelo.excepciones.AldeanoOcupado;
import modelo.excepciones.EdificioConVidaMaxima;
import modelo.juego.Juego;
import modelo.unidades.Aldeano;
import vista.JuegoVista;
import vista.MapaView;

public class RepararHandler extends AccionSobreCasilla implements EventHandler<ActionEvent> {
    private Aldeano aldeano;
    Juego juego;

    RepararHandler(Aldeano unAldeano, Juego unJuego) {
        aldeano = unAldeano;
        juego = unJuego;
    }

    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.setAccionSobreCasilla(this);
    }

    public void realizarAccion(MapaView mapaView, Posicion posicion) {
        Contenible unContenible = mapaView.getMapa().getContenido(posicion.getPosX(), posicion.getPosY());
        try {
            juego.getJugadorActual().repararEstructura(aldeano, (Estructura) unContenible);
        } catch (AldeanoOcupado e) {
            alertar("¡Aldeano ocupado!");
        } catch (EdificioConVidaMaxima e) {
            alertar("¡Vida máxima alcanzada!");
        }

        JuegoVista juegoVista = JuegoVista.getInstancia();
        juegoVista.actualizar(mapaView.getJuego());
    }
}
