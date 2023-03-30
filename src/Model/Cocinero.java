package Model;

public class Cocinero implements Runnable{
    private Monitor monitor;
    public Cocinero(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {//preparar comida de manera continua
        while(true){
            monitor.cocinar();
        }
    }
}
