package com.mycompany.dequeue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicTest {

    @Test
    public void BasicTest() {
        DeQueue<Integer> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για ακέραιους αριθμούς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια

        deQueue.pushFirst(1); // Εισαγωγή του στοιχείου 1 στην αρχή της ουράς
        deQueue.pushLast(2); // Εισαγωγή του στοιχείου 2 στο τέλος της ουράς
        deQueue.pushFirst(3); // Εισαγωγή του στοιχείου 3 στην αρχή της ουράς
        deQueue.pushLast(4); // Εισαγωγή του στοιχείου 4 στο τέλος της ουράς
        assertTrue(deQueue.first() == 3); // Έλεγχος αν το πρώτο στοιχείο της ουράς είναι το 3
        assertTrue(deQueue.last() == 4); // Έλεγχος αν το τελευταίο στοιχείο της ουράς είναι το 4
        assertTrue(deQueue.popFirst() == 3); // Έλεγχος αν το στοιχείο που διαγράφεται απο την αρχή της ουράς είναι το 3
        assertTrue(deQueue.popLast() == 4); // Έλεγχος αν το στοιχείο που διαγράφεται απο το τέλος της ουράς είναι το 4
        assertTrue(deQueue.first() == 1); // Έλεγχος αν το πρώτο στοιχείο της ουράς είναι το 1
        assertTrue(deQueue.last() == 2); // Έλεγχος αν το τελευταίο στοιχείο της ουράς είναι το 2
        deQueue.popFirst(); // Διαγραφή στοιχείου απο την αρχή της ουράς
        deQueue.popLast(); // Διαγραφή στοιχείου απο το τέλος της ουράς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        assertEquals(0, deQueue.size()); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 0
        deQueue.pushFirst(10); // Εισαγωγή του στοιχείου 10 στην αρχή της ουράς
        deQueue.pushLast(20); // Εισαγωγή του στοιχείου 20 στο τέλος της ουράς
        assertEquals(2, deQueue.size()); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 2
        deQueue.clear(); // Διαγραφή όλων των στοιχείων της ουράς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        assertEquals(0, deQueue.size()); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 0
    }
}