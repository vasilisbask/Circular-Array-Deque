package com.mycompany.dequeue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResizeTest {

    private static final int SIZE = 100000; // O αριθμός επαναλήψεων της for

    @Test
    public void ResizeTest() {
        DeQueue<Integer> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για ακέραιους αριθμούς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        assertEquals(0, deQueue.size()); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 0

        for (int i = 0; i < SIZE; i++) {
            if (i%2==0) {
                deQueue.pushFirst(i); // Εισαγωγή νέου στοιχείου στην αρχή της ουράς
            } else {
                deQueue.pushLast(i); // Εισαγωγή νέου στοιχείου στο τέλος της ουράς
            }
            assertTrue(deQueue.size() == i + 1); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς αυξάνεται κατά 1
            assertTrue(!deQueue.isEmpty()); // Έλεγχος αν η ουρά δεν είναι άδεια
        }

        assertTrue(deQueue.size() == SIZE); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι ίσος με τον αριθμό επαναλήψεων της for

        for (int j=0;j<SIZE/2;j++) {
            if (j%2==0) {
                deQueue.popFirst(); // Διαγραφή στοιχείου απο την αρχή της ουράς
            } else {
                deQueue.popLast(); // Διαγραφή στοιχείου απο το τέλος της ουράς
            }
        }

        assertTrue(!deQueue.isEmpty()); // Έλεγχος αν η ουρά δεν είναι άδεια
        assertTrue(deQueue.size() == SIZE/2); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι ίσος με τον αριθμό επαναλήψεων της for δια 2
        deQueue.clear(); // Διαγραφή όλων των στοιχείων της ουράς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        assertTrue(deQueue.size() == 0); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 0
    }
}