package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import modelo.espacio.Casillero;
import modelo.espacio.Posicion;
import vista.MapaView;

public class BotonCasilleroEventHandler implements EventHandler<ActionEvent> {
    Posicion posicion;

    public BotonCasilleroEventHandler(Casillero unCasillero, Button boton) {
        posicion = unCasillero.getPosicion();
    }



    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.seleccionarCasillero(posicion);

    }
}
