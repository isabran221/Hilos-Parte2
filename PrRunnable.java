/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prrunnable;

/**
 *
 * @author isabr
 */
public class PrRunnable implements Runnable {
    private String name;

    public PrRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(name + " interrupted.");
            }
        }
        System.out.println(name + " finished.");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrRunnable("Thread 1"));
        Thread t2 = new Thread(new PrRunnable("Thread 2"));
        t1.start();
        t2.start();
    }
}

