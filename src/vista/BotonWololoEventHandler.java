package vista;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import modelo.espacio.Casillero;
import modelo.espacio.Contenible;
import modelo.espacio.Mapa;
import modelo.espacio.Posicion;
import modelo.estructuras.Castillo;
import modelo.estructuras.PlazaCentral;
import modelo.juego.Juego;
import modelo.unidades.Aldeano;

import java.awt.*;

import static java.awt.Color.GREEN;

public class BotonWololoEventHandler extends BotonEventHandler {
    private static final int ANCHO = 20;
    private static final int ALTO = 15;
    private final Stage escenario;
    private final TextField nombre_jugador1;
    private final TextField nombre_jugador2;

    BotonWololoEventHandler(Stage escenario, TextField nombre_jugador1, TextField nombre_jugador2) {
        this.escenario = escenario;
        this.nombre_jugador1 = nombre_jugador1;
        this.nombre_jugador2 = nombre_jugador2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.handle(actionEvent);
        BorderPane raiz = new BorderPane();
        GridPane vistaMapa = new GridPane();
        vistaMapa.setAlignment(Pos.CENTER);

        Juego nuevoJuego = new Juego(ANCHO, ALTO);
        Mapa mapa = nuevoJuego.getMapa();

        for (int x = 0; x < ANCHO; x++) {
            for (int y = 0; y < ALTO; y++) {
                Image imagenSuelo = new Image(getClass().getResourceAsStream("imagenes/suelo.png"));
                ImageView suelo = new ImageView(imagenSuelo);
                Button unBoton = new Button();
                Casillero unCasillero = mapa.getCasillero(new Posicion(x, y));
                Contenible elContenido = unCasillero.getContenido();
                unBoton.setGraphic(suelo);
                if(elContenido instanceof Castillo){
                    unBoton.setOnAction(new BotonCastilloHandler(unBoton, unCasillero));
                }
                if (elContenido instanceof Aldeano) {
                    unBoton.setOnAction(new BotonAldeanoHandler(unBoton, unCasillero));
                }
                unBoton.setPadding(Insets.EMPTY);

                if(elContenido instanceof PlazaCentral) {
                    unBoton.setOnAction(new BotonPlazaCentralHandler(unBoton, unCasillero));
                }

                vistaMapa.add(unBoton, x, y);
                }
            }
            raiz.setCenter(vistaMapa);
            Scene escenaJuego = new Scene(raiz);

            this.escenario.setScene(escenaJuego);
            this.escenario.setMaximized(true);
        }
    }
