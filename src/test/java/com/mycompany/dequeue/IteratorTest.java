package com.mycompany.dequeue;

import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class IteratorTest {

    @Test
    public void IteratorTest() {
        DeQueue<Character> deQueue = new CircularArrayDeque<>(); // Δημιουργία μιας νέας ουράς για χαρακτήρες
        assertTrue(deQueue.isEmpty()); // Έλεγχος αν η ουρά είναι άδεια
        char character = 'A';

        for (int i = 0; i < 26; i++) {
            deQueue.pushLast(character++); // Εισαγωγή των γραμμάτων του αγγλικού αλφαβήτου στο τέλος της ουράς
        }

        Iterator<Character> iterator = deQueue.iterator(); // Δημιουργία νέου iterator
        StringBuilder result = new StringBuilder(); // Δημιουργία StringBuilder για τη συγκέντρωση των χαρακτήρων

        while (iterator.hasNext()) { // Οσο υπάρχει επόμενο στοιχείο στην ουρά
            char current = iterator.next(); // Το επόμενο στοιχείο της ουράς απο την αρχή της προς το τέλος της
            result.append(current); // Προσθήκη του χαρακτήρα στη μεταβλητή result
        }

        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", result.toString()); // Έλεγχος αν το αποτέλεσμα είναι αυτό
    }
}