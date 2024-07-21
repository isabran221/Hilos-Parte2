/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filosofoscomensales;

/**
 *
 * @author isabr
 */
import java.util.Random;

class MesaCircular {

    private int cantidadFilosofos;
    private boolean[] comiendo;

    public MesaCircular(int n) {
        cantidadFilosofos = n;
        comiendo = new boolean[cantidadFilosofos];

        for (int i = 0; i < cantidadFilosofos; i++) {
            comiendo[i] = false;
        }
    }

    public synchronized void intentarComer(int id) throws InterruptedException {
        int izquierda = (id + cantidadFilosofos - 1) % cantidadFilosofos;
        int derecha = (id + 1) % cantidadFilosofos;
        while (comiendo[izquierda] || comiendo[derecha]) {
            wait();
        }

        comiendo[id] = true;
    }

    public synchronized void terminarDeComer(int id) {
        comiendo[id] = false;
        notifyAll();
    }
}

class FilosofoPensante extends Thread {

    private int id;
    private MesaCircular mesa;
    private Random generador;

    public FilosofoPensante(int id, MesaCircular mesa) {
        this.id = id;
        this.mesa = mesa;
        generador = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                mesa.intentarComer(id);
                comer();
                mesa.terminarDeComer(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep(generador.nextInt(1000));
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep(generador.nextInt(1000));
    }
}

public class FilosofosComensales {
    public static void main(String[] args) {
        int numFilosofos = 5;
        MesaCircular mesa = new MesaCircular(numFilosofos);

        for (int i = 0; i < numFilosofos; i++) {
            new FilosofoPensante(i, mesa).start();
        }
    }
}
