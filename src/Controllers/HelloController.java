package Controllers;

import Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;


public class HelloController implements Observer {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnIniciar;

    @FXML
    private Label lblMesero;

    @FXML
    private Circle mesero;


    @FXML
    void Finalizar(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void IniciarAnimacion(ActionEvent event) {
        mesero.setFill(Color.GREEN);
        btnIniciar.setDisable(true);
        //sse agrega al observador los cambios los hace en restaurante
        Monitor monitor = new Monitor();
        monitor.addObserver(this::update);

        Mesero mesero=new Mesero(anchor, monitor);
        Thread hiloMesero = new Thread(mesero);
        hiloMesero.start();


        Recepcionista recepcionista=new Recepcionista(monitor);
        Thread hiloRecepcionista = new Thread(recepcionista);
        hiloRecepcionista.start();

        Cocinero cocinero = new Cocinero(monitor);
        Thread hCocinero = new Thread(cocinero);
        hCocinero.start();

        HilosCreados hilosCreados = new HilosCreados(anchor, monitor, this);
        Thread hCreadorClientes = new Thread(hilosCreados);
        hCreadorClientes.start();


    }

    @Override
    public void update(Observable o, Object arg) {
        synchronized (this) {
            if (((String)arg).contains("ocupadoMesero")){
                String[] cadena = ((String) arg).split(" ");
                int numMesa = Integer.parseInt(cadena[1]);
                Platform.runLater(()-> lblMesero.setText("Atendiendo: "+(numMesa+1)));
                mesero.setFill(Color.RED);
            }else
            if (((String)arg).contains("libreMesero")){
                Platform.runLater(()-> lblMesero.setText(""));
                mesero.setFill(Color.GREEN);
            }
        }
    }
}
