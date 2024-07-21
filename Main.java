/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author isabr
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Agrega elementos a la lista sincronizada
        synchronizedList.add("One");
        synchronizedList.add("Two");
        synchronizedList.add("Three");

        // Itera sobre la lista sincronizada e imprime cada elemento
        synchronized (synchronizedList) {
            for (String s : synchronizedList) {
                System.out.println(s);
            }
        }
    }
}
