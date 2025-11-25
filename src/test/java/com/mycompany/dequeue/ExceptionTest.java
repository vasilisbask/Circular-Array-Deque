package com.mycompany.dequeue;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExceptionTest {

    @Test
    public void ExceptionTest() {
        DeQueue<Integer> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για ακέραιους αριθμούς
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        assertEquals(0, deQueue.size()); // Έλεγχος αν ο αριθμός των στοιχείων της ουράς είναι 0
        try {
            deQueue.popFirst(); // Διαγραφή στοιχείου απο την αρχή της ουράς αν και δεν υπάρχει κάποιο στοιχείο στην ουρά για να μας πετάξει exception
        } catch (NoSuchElementException e) {
            assertEquals("The deque is empty. So you can not pop from the start of the deque.", e.getMessage()); // Έλεγχος αν το μήνυμα του exception είναι αυτό
        }
        try {
            deQueue.popLast(); // Διαγραφή στοιχείου απο το τέλος της ουράς αν και δεν υπάρχει κάποιο
            // στοιχείο στην ουρά για να μας πετάξει exception
        } catch (NoSuchElementException e) {
            assertEquals("The deque is empty. So you can not pop from the end of the deque.", e.getMessage()); // Έλεγχος αν το μήνυμα του exception είναι αυτό
        }
        try {
            deQueue.first(); // Το πρώτο στοιχείο της ουράς αν και δεν υπάρχει κάποιο στοιχείο στην ουρά για να μας πετάξει exception
        } catch (NoSuchElementException e) {
            assertEquals("The deque is empty. So you can not get the first element of the deque.", e.getMessage()); // Έλεγχος αν το μήνυμα του exception είναι αυτό
        }
        try {
            deQueue.last(); // Το τελευταίο στοιχείο της ουράς αν και δεν υπάρχει κάποιο στοιχείο στην ουρά για να μας πετάξει exception
        } catch (NoSuchElementException e) {
            assertEquals("The deque is empty. So you can not get the last element of the deque.", e.getMessage()); // Έλεγχος αν το μήνυμα του exception είναι αυτό
        }
    }
}