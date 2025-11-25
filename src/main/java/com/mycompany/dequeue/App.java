package com.mycompany.dequeue;

import java.util.Iterator;
import java.util.Random;

public class App {

    public static final int ELEMENTS = 100;
    public static final int RANGE = 1000;

    public static void main(String[] args) {

            Random rng = new Random();

            DeQueue<Integer> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για ακεραίους αριθμούς

            for (int i = 0; i < ELEMENTS; i++) {
                deQueue.pushLast(rng.nextInt(RANGE)); // Εισαγωγή τυχαίου αριθμού στο τέλος της ουράς
            }

            Iterator<Integer> iterator = deQueue.iterator(); // Δημιουργία νέου iterator
            while (iterator.hasNext()) { // Οσο υπάρχει επόμενο στοιχείο στην ουρά
                Integer element = iterator.next(); // Το επόμενο στοιχείο της ουράς απο την αρχή της προς το τέλος της
                System.out.print(element + " "); // Εμφάνιση του στοιχείου
            }

            System.out.println();

            for (int j=0; j<ELEMENTS/2;j++) {
                System.out.println(deQueue.popFirst()); // Διαγραφή στοιχείου απο την αρχή της ουράς και εμφάνιση του
            }

            iterator = deQueue.iterator();
            while (iterator.hasNext()) { //
                Integer element = iterator.next();
                System.out.print(element + " ");
            }

            System.out.println();
            System.out.println("First: " + deQueue.first()); // Εμφάνιση του πρώτου στοιχείου της ουράς
            System.out.println("Last: " + deQueue.last()); // Εμφάνιση του τελευταίου στοιχείου της ουράς
            System.out.println("Size: " + deQueue.size()); // Εμφάνιση του αριθμού των στοιχείων της ουράς
            System.out.println("Empty: " + deQueue.isEmpty()); // Εμφάνιση αν η ουρά είναι άδεια
            System.out.println("Clearing array...");
            deQueue.clear(); // Διαγραφή όλων των στοιχείων της ουράς
            System.out.println("Size: " + deQueue.size()); // Εμφάνιση του αριθμού των στοιχείων της ουράς
            System.out.println("Empty: " + deQueue.isEmpty()); // Εμφάνιση αν η ουρά είναι άδεια
    }
}