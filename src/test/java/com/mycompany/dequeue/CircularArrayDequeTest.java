package com.mycompany.dequeue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CircularArrayDequeTest {

    @Test
    public void CircularArrayDequeTest() {
        DeQueue<Integer> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για ακέραιους αριθμούς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        int count = 100000; // Μεταβλητή για τον αριθμό επαναλήψεων της for
        for (int i = 0; i < count; i++) {
            deQueue.pushLast(i); // Εισαγωγή στοιχείου στο τέλος της ουράς
            assertTrue(deQueue.size() == i + 1); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς αυξάνεται κατά 1
            assertTrue(deQueue.first() == 0); // Έλεγχος αν το πρώτο στοιχείο της ουράς είναι το 0
        }
        int current = 0;
        while (!deQueue.isEmpty()) { // Οσο η ουρά δεν είναι άδεια
            assertTrue(deQueue.first() == current); // Έλεγχος αν το πρώτο στοιχείο της ουράς είναι ισο με την τιμή της μεταβλητής current
            assertTrue(deQueue.popFirst() == current); // Έλεγχος αν το στοιχείο που διαγράφεται απο την αρχή της ουράς είναι ισο με την τιμή της μεταβλητής current
            current++; // Αύξηση της τιμής της μεταβλητής current κατά 1
        }
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
    }
}