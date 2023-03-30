package Model;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cliente implements Runnable{
    private AnchorPane anchor;
    private Monitor monitor;
    private static String[] positions;
    public Cliente(AnchorPane anchor, Monitor monitor){
        this.anchor = anchor;
        this.monitor = monitor;
        positions = new String[20];
        positions[0] = "235 60";
        positions[1] = "336 60";
        positions[2] = "438 60";
        positions[3] = "546 60";
        positions[4] = "644 60";
        positions[5] = "750 60";
        positions[6] = "833 60";

        positions[7] = "929 114";
        positions[8] = "929 224";
        positions[9] = "929 307";
        positions[10] = "836 398";

        positions[11] = "740 398";
        positions[12] = "644 389";
        positions[13] = "550 389";
        positions[14] = "445 389";
        positions[15] = "336 389";
        positions[16] = "235 389";
        positions[17] = "166 315";

        positions[18] = "166 224";
        positions[19] = "166 127";
    }
    @Override
    public void run() {
        Circle cliente = new Circle(15, Color.BURLYWOOD);
        Platform.runLater(() -> {
            cliente.setLayoutX(519);
            cliente.setLayoutY(1133);
            anchor.getChildren().add(cliente);
        });
        //Movimiento de los cleintes
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()-> cliente.setLayoutY(cliente.getLayoutY()-50));
        }

        //Posicionar a los clientes en lase mesas con forme el id
        int numMesa = monitor.entrar(Thread.currentThread().getName());
        String[] layout = positions[numMesa].split(" ");
        Platform.runLater(()-> {
            cliente.setLayoutX(Integer.parseInt(layout[0]));
            cliente.setLayoutY(Integer.parseInt(layout[1])+50);
        });
        System.out.println("N" + numMesa);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ordenar
        Platform.runLater(()-> cliente.setFill(Color.MAGENTA));
        monitor.ordenar();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Comer
        Platform.runLater(()-> cliente.setFill(Color.MAROON));
        monitor.comer();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();}

        //Salir
        Platform.runLater(() -> cliente.setFill(Color.TRANSPARENT));
        monitor.salir(numMesa);
    }
}
