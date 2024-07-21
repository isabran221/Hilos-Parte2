/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filosofoscenando;

/**
 *
 * @author isabr
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilosofosCenando {
    private static final int NUM_FILOSOFOS = 5;
    private static final Lock[] tenedores = new ReentrantLock[NUM_FILOSOFOS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new ReentrantLock();
        }

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Filosofo(i).start();
        }
    }

    private static class Filosofo extends Thread {
        private final int id;

        Filosofo(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pensar();
                    tomarTenedores();
                    comer();
                    cederTenedores();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void pensar() throws InterruptedException {
            System.out.println("Filosofo " + id + " está pensando.");
            Thread.sleep((int) (Math.random() * 100));
        }

        private void comer() throws InterruptedException {
            System.out.println("Filosofo " + id + " está comiendo.");
            Thread.sleep((int) (Math.random() * 100));
        }

        private void tomarTenedores() {
            int tenedorIzquierdo = id;
            int tenedorDerecho = (id + 1) % NUM_FILOSOFOS;

            while (true) {
                tenedores[tenedorIzquierdo].lock();
                if (tenedores[tenedorDerecho].tryLock()) {
                    return;
                }
                tenedores[tenedorIzquierdo].unlock();
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void cederTenedores() {
            int tenedorIzquierdo = id;
            int tenedorDerecho = (id + 1) % NUM_FILOSOFOS;

            tenedores[tenedorDerecho].unlock();
            tenedores[tenedorIzquierdo].unlock();
        }
    }
}
