package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.media.AudioClip;
import modelo.espacio.Casillero;
import modelo.espacio.Posicion;
import modelo.juego.Juego;
import modelo.unidades.Espadachin;
import vista.MapaView;

public class BotonEspadachinEventHandler extends BotonEventHandler {
    Espadachin espadachin;
    Button boton;
    Posicion posicion;


    public BotonEspadachinEventHandler(Casillero unCasillero, Button unBoton, Juego unJuego) {
        espadachin = (Espadachin) unCasillero.getContenido();
        boton = unBoton;
        posicion = unCasillero.getPosicion();

        ContextMenu contextMenu = new ContextMenu();
        MenuItem mover = new MenuItem("Mover");
        MenuItem atacar = new MenuItem("Atacar");
        MenuItem informacion = new MenuItem("Informacion");

        informacion.setOnAction(new MostrarInformacionUnidadHandler(this.espadachin));

        mover.setOnAction(new MoverHandler(this.espadachin, unJuego));
        atacar.setOnAction(new AtacarHandler(this.espadachin, unJuego));


        contextMenu.getItems().addAll(mover, atacar, informacion);
        boton.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(boton, event.getScreenX(), event.getScreenY());

                String rutaSonido = "/vista/sonidos/aldeano.mp3";
                AudioClip sonidoEspadachin = new AudioClip(
                        BotonEventHandler.class.getResource(rutaSonido).toExternalForm()
                );

                sonidoEspadachin.play();
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.seleccionarCasillero(posicion);
    }
}
