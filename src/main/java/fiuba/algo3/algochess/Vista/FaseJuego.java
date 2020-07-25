package fiuba.algo3.algochess.Vista;

import fiuba.algo3.algochess.Controlador.ControladorAtaque;
import fiuba.algo3.algochess.Controlador.ControladorMovimiento;
import fiuba.algo3.algochess.Controlador.CrearUnidad;
import fiuba.algo3.algochess.Modelo.juego.Juego;
import fiuba.algo3.algochess.Vista.Inicio.VentanaLoguear;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.io.File;

public class FaseJuego {



    private  VBox ladoDerecho;
    private  VBox ladoIzquierdo;
    private  VBox infoUnidad;
    private   Label nombreUnidad;
    private   Label vidaUnidad;
    public  Label turnoDe;
    public   Label puntosDisponibles;
    public  Label mensajeDeError;
    public    Stage ventana;
    private  BorderPane interfasJuego;
    public  Juego juego;
    private  boolean comienzoJuego = false;
    private GridPane tablero;
    public TableroInterfaz tableroInterfaz;




    public  void display(Stage ventana, MediaPlayer menuInicio) {
        //Inicializo Jugadores y Juego
        String jugador1 = VentanaLoguear.display("Registro Jugador 1");
        String jugador2 = VentanaLoguear.display("Registro Jugador 2");
        menuInicio.stop();
        juego = new Juego(jugador1,jugador2);
        etapaColocarFichas(juego);
    }

    public  void etapaColocarFichas(Juego juego ) {
        ventana = new Stage();
        ventana.setTitle("AlgoChess");

        tableroInterfaz = new TableroInterfaz(juego);
        tablero = tableroInterfaz.crearTablero();
        ladoDerecho = crearUnidadesVbox(juego);
        ladoIzquierdo = crearMensajesJugador(juego);

        interfasJuego = new BorderPane();

        interfasJuego.setCenter(tablero);
        BorderPane.setMargin(tablero,new Insets(10));
        interfasJuego.setPadding(new Insets(15));
        interfasJuego.setStyle("-fx-background-color: #45647e;");
        cambiarLadoDerechoInterfaz(ladoDerecho);
        cambiarLadoIzquierdoInterfaz(ladoIzquierdo);

        Scene scene = new Scene(interfasJuego,1200,780);

        ventana.setScene(scene);
        ventana.show();
    }

    private  VBox crearInfoUnidad(Juego juego) {
        VBox hBox = new VBox(10);
        hBox.setStyle("-fx-background-color: #7a7e31;");
        nombreUnidad = new Label();
        vidaUnidad = new Label();

        hBox.getChildren().addAll(nombreUnidad,vidaUnidad);
        return hBox;
    }

    private  VBox crearMensajesJugador(Juego juego) {
        VBox vBox = new VBox(10);

        turnoDe = new Label("Turno de " + juego.jugadorActual().getNombreJugador());

        puntosDisponibles = new Label("Puntos disponibles: " + juego.jugadorActual().getPuntosDisponibles());

        mensajeDeError = new Label();
        vBox.getChildren().addAll(turnoDe,puntosDisponibles,mensajeDeError);
        vBox.setPrefWidth(200);
        return vBox;
    }

    private  VBox crearUnidadesVbox(Juego juego) {
        VBox vBox = new VBox(10);

        Label unidadesDisponibles = new Label("Unidades: ");
        unidadesDisponibles.setTextFill(Color.web("#ff0000", 0.8));

        Button crearSoldado = new Button("Crear Soldado");
        crearSoldado.setOnAction( e -> new CrearUnidad("soldado",juego,tableroInterfaz,this, "imagenes/soldado4.png","imagenes/soldado3.png", "src/main/resources/sonidos/SoldadoCreacion.mp3"));

        Button crearJinete = new Button("Crear Jinete");
        crearJinete.setOnAction( e -> new CrearUnidad("jinete",juego,tableroInterfaz, this, "imagenes/jinete3der.png", "imagenes/jinete3ladoizq.png","src/main/resources/sonidos/caballeroCreacion.mp3"));

        Button crearCurandero = new Button("Crear Curandero");
        crearCurandero.setOnAction( e -> new CrearUnidad("curandero",juego,tableroInterfaz, this, "imagenes/curandero2.png", "imagenes/curandero.png","src/main/resources/sonidos/curanderoCreacion.mp3"));

        Button crearCatapulta = new Button("Crear Catapulta");
        crearCatapulta.setOnAction( e -> new CrearUnidad("catapulta",juego,tableroInterfaz, this, "imagenes/catapultaMiraDerecha.png", "imagenes/catapultaMiraIzquierda.png","src/main/resources/sonidos/catapultaCreacion.mp3"));


        
        vBox.getChildren().addAll(unidadesDisponibles,crearSoldado,crearCurandero,crearJinete,crearCatapulta);
        return vBox;
    }

    public  void inicioJuego(){

        mensajeDeError.setText("Comienzo etapa juego");
        puntosDisponibles.setText("");
        infoUnidad = crearInfoUnidad(juego);
        VBox botonesAccionar = acciones();
        VBox conjunto = new VBox(10);
        conjunto.getChildren().addAll(botonesAccionar,infoUnidad);
        cambiarLadoDerechoInterfaz(conjunto);
        comienzoJuego = true;

    }

    private  VBox acciones() {
        VBox vBox = new VBox(10);

        Button botonMover = new Button("Mover");
        botonMover.setOnMousePressed( e -> new ControladorMovimiento(this,tablero));

        Button botonAtaque = new Button("Atacar/Curar");
        botonAtaque.setOnMousePressed( e -> new ControladorAtaque(this,tablero));

        vBox.getChildren().addAll(botonAtaque,botonMover);
        return vBox;
    }

    public  void cambiarLadoDerechoInterfaz(VBox ladoDerecho){
        interfasJuego.setRight(ladoDerecho);
    }

    private  void cambiarLadoIzquierdoInterfaz(VBox ladoIzquierdo) {
        interfasJuego.setLeft(ladoIzquierdo);
    }

    public boolean comenzoElJuego(){
        return comienzoJuego;
    }

    public void cambiarJugadorActual(String nombreJugador){
        turnoDe.setText("Turno de " + nombreJugador);
    }

    public void cambiarVidaUnidad(double vida){
        vidaUnidad.setText("Vida unidad: " + vida );
    }

    public void cambiarMensajeError(String error){

        int len = error.length();
        if(len > 30){
            char[] errorFiltrado = new char[len+2];
            for(int i = 0; i < len; i++){
                if( i == len/2){
                    errorFiltrado[i] = '\n';
                }
                if( i == len/2 + 1){
                    errorFiltrado[i] = '-';
                }
                if( i < len/2) {
                    errorFiltrado[i] = error.charAt(i);
                }
                else{
                    errorFiltrado[i+2] = error.charAt(i);
                }
            }
            error = String.valueOf(errorFiltrado);
        }


        mensajeDeError.setText(error);
    }

    public void cambiarNombreUnidad(String nombreUnidad){
        this.nombreUnidad.setText("Unidad : " + nombreUnidad);
    }

    public void cerrarJuego() {
        ventana.close();
    }
}

