package Model;

public class Recepcionista implements Runnable{
    private Monitor monitor;
    public Recepcionista(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    //Duerme a al resepcionista
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitor.recepcion();

        }
    }
}
