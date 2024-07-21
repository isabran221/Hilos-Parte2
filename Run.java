/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package run;

/**
 *
 * @author isabr
 */
public class run extends Thread {
    public run(String name) {
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
        run t1 = new run("Thread 1");
        run t2 = new run("Thread 2");
        t1.start();
        t2.start();
    }
}
