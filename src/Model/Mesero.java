package Model;

import javafx.scene.layout.AnchorPane;

public class Mesero implements Runnable{
    private Monitor monitor;
    private AnchorPane anchorPane;
    public Mesero(AnchorPane anchorPane, Monitor monitor){
        this.monitor = monitor;
        this.anchorPane=anchorPane;
    }
    @Override
    public void run() {

        while (true){
            monitor.servirOrden();
        }
    }
}
