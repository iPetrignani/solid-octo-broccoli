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
import modelo.estructuras.Cimiento;
import modelo.juego.Juego;
import vista.MapaView;

public class BotonCimientoEventHandler extends BotonEventHandler {
    Button boton;
    Cimiento cimiento;
    Posicion posicion;

    public BotonCimientoEventHandler(Casillero unCasillero, Button botonMapa, Juego unJuego) {
        Cimiento cimiento = (Cimiento) unCasillero.getContenido();
        ContextMenu contextMenu = new ContextMenu();

        MenuItem reanudar = new MenuItem("Reanudar Construccion");
        MenuItem detener = new MenuItem("Detener Construccion");
        MenuItem informacion = new MenuItem("Informacion");

        reanudar.setOnAction(new ReanudarConstruccionCimientoHandler(cimiento,unJuego));
        detener.setOnAction(new DetenerConstruccionCimientoHandler(cimiento,unJuego));
        informacion.setOnAction(new MostrarInformacionCimientoHandler(cimiento));


        contextMenu.getItems().addAll(detener,reanudar,informacion);
        botonMapa.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(botonMapa, event.getScreenX(), event.getScreenY());

                String rutaSonido = "/vista/sonidos/cimiento.wav";
                AudioClip sonidoCimiento = new AudioClip(
                        BotonEventHandler.class.getResource(rutaSonido).toExternalForm()
                );

                sonidoCimiento.play();

            }
        });
        cimiento = (Cimiento) unCasillero.getContenido();
        boton = botonMapa;
        posicion =  unCasillero.getPosicion();

    }
    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.seleccionarCasillero(posicion);
    }
}
