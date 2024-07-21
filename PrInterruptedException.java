/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package printerruptedexception;

/**
 *
 * @author isabr
 */
public class PrInterruptedException {
    // Método estático ex1 que lanza InterruptedException
    public static void ex1() throws InterruptedException {
        Thread h = new PrThread("1");
        h.start();
        Thread.sleep(100);
        h.interrupt();
        System.out.println(h.isInterrupted());
    }

    public static void main(String[] args) {
        try {
            ex1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrThread extends Thread {
    public PrThread(String s) {
        super(s);
    }

    public final void run() {
        boolean sigue = true;
        for (int i = 0; i < 100 && sigue; i++) {
            try {
                System.out.println(getName() + ":" + i);
                sleep(20);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpida");
                sigue = false;
            }
        }
    }
}
