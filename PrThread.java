/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prthread;

/**
 *
 * @author isabr
 */
public class PrThread extends Thread {
    public PrThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
        System.out.println(getName() + " finished.");
    }

    public static void main(String[] args) {
        PrThread t1 = new PrThread("Thread 1");
        PrThread t2 = new PrThread("Thread 2");
        t1.start();
        t2.start();
    }
}
