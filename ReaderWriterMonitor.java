/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package readerwritermonitor;

/**
 *
 * @author isabr
 */
public class ReaderWriterMonitor {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }

    public static void main(String[] args) {
        ReaderWriterMonitor monitor = new ReaderWriterMonitor();

        Runnable readTask = () -> {
            try {
                monitor.lockRead();
                System.out.println(Thread.currentThread().getName() + " is reading.");
                Thread.sleep(100);
                monitor.unlockRead();
                System.out.println(Thread.currentThread().getName() + " finished reading.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeTask = () -> {
            try {
                monitor.lockWrite();
                System.out.println(Thread.currentThread().getName() + " is writing.");
                Thread.sleep(100);
                monitor.unlockWrite();
                System.out.println(Thread.currentThread().getName() + " finished writing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(readTask, "Reader 1");
        Thread t2 = new Thread(writeTask, "Writer 1");
        Thread t3 = new Thread(readTask, "Reader 2");
        Thread t4 = new Thread(writeTask, "Writer 2");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
