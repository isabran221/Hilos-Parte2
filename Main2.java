/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main2;

/**
 *
 * @author isabr
 */
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // Agrega elementos a la lista
        list.add("One");
        list.add("Two");
        list.add("Three");

        // Itera sobre la lista e imprime cada elemento
        for (String s : list) {
            System.out.println(s);
        }
    }
}
