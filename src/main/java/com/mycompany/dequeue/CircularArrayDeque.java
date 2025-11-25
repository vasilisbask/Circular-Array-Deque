package com.mycompany.dequeue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayDeque<E> implements DeQueue<E> {
    private static final int INITIAL_CAPACITY = 64; // Το αρχικό μέγεθος του κυκλικού πίνακα
    private E[] array; // Κυκλικός πίνακας που αποθηκεύει τα στοιχεία της ουράς
    private int f; // Δείκτης πρώτου στοιχείου στον πίνακα
    private int r; // Δείκτης επόμενης διαθέσιμης θέσης στον πίνακα
    private transient int modCount=0; // Μετρητής για την παρακολούθηση αλλαγών στην ουρά

    public CircularArrayDeque() { // Constructor για την αρχικοποίηση των μεταβλητών
        this.array =(E[]) new Object[INITIAL_CAPACITY];
        this.f = 0; 
        this.r = 0;
    }
    
    @Override
    public void pushFirst(E elem) { // Για εισαγωγή στοιχείου στην αρχή του πίνακα
        if (size() == array.length - 1) { // Όταν ο χρήστης πάει να βάλει το τελευταίο στοιχείο που χωράει στον πίνακα, διπλασιάζουμε το μέγεθος του πίνακα
            doubleCapacity(); 
        }
        f = (f - 1 + array.length) % array.length; // Μείωση του δείκτη f
        array[f] = elem; // Προσθήκη στοιχείου στην αρχή της ουράς
        modCount++; // Αύξηση του μετρητή modCount
    }

    @Override
    public void pushLast(E elem) { // Για εισαγωγή στοιχείου στο τέλος του πίνακα
        if (size() == array.length - 1) { // Όταν ο χρήστης πάει να βάλει το τελευταίο στοιχείο που χωράει στον πίνακα, διπλασιάζουμε το μέγεθος του πίνακα
            doubleCapacity();
        }
        array[r] = elem; // Προσθήκη στοιχείου στο τέλος της ουράς
        r = (r + 1) % array.length; // Αύξηση του δείκτη r
        modCount++; // Αύξηση του μετρητή modCount
    }

    @Override
    public E popFirst() { // Για διαγραφή στοιχείου από την αρχή του πίνακα
        if (isEmpty()) { // Έλεγχος αν η ουρά είναι άδεια
            throw new NoSuchElementException("The deque is empty. So you can not pop from the start of the deque.");
        }
        E result = array[f]; // Αποθήκευση του πρώτου στοιχείου του πίνακα πριν την αφαίρεση
        array[f] = null; // Αφαίρεση του πρώτου στοιχείου του πίνακα από τη θέση f
        f = (f + 1) % array.length; // Αύξηση του δείκτη f
        if (4 * size() <= array.length) { // Αν το μέγεθος του πίνακα είναι 4 φορές μεγαλύτερο από τον αριθμό των στοιχείων που περιέχει, υποδιπλασιάζουμε το μέγεθος του πίνακα
            halfCapacity();
        }
        modCount++; // Αύξηση του μετρητή modCount
        return result; // Επιστροφή του στοιχείου που αφαιρέθηκε
    }

    @Override
    public E popLast() { // Για διαγραφή στοιχείου από το τέλος του πίνακα
        if (isEmpty()) { // Έλεγχος αν η ουρά είναι άδεια
            throw new NoSuchElementException("The deque is empty. So you can not pop from the end of the deque.");
        }
        r = (r - 1 + array.length) % array.length; // Μείωση του δείκτη r
        E result = array[r]; // Αποθήκευση του τελευταίου στοιχείου του πίνακα πριν την αφαίρεση
        array[r] = null; // Αφαίρεση του τελευταίου στοιχείου του πίνακα από τη θέση r
        if (4 * size() <= array.length) { // Αν το μέγεθος του πίνακα είναι 4 φορές μεγαλύτερο από τον αριθμό των στοιχείων που περιέχει, υποδιπλασιάζουμε το μέγεθος του πίνακα
            halfCapacity();
        }
        modCount++; // Αύξηση του μετρητή modCount
        return result; // Επιστροφή του στοιχείου που αφαιρέθηκε
    }

    @Override
    public E first() { // Για το πρώτο στοιχείο του πίνακα
        if (isEmpty()) { // Έλεγχος αν η ουρά είναι άδεια
            throw new NoSuchElementException("The deque is empty. So you can not get the first element of the deque.");
        }
        return array[f]; // Επιστροφή του πρώτου στοιχείου του πίνακα
    }

    @Override
    public E last() { // Για το τελευταίο στοιχείο του πίνακα
        if (isEmpty()) { // Έλεγχος αν η ουρά είναι άδεια
            throw new NoSuchElementException("The deque is empty. So you can not get the last element of the deque.");
        }
        return array[(r - 1 + array.length) % array.length]; // Επιστροφή του τελευταίου στοιχείου του πίνακα
    }

    @Override
    public boolean isEmpty() { // Για το αν είναι άδειος ο πίνακας
        return f == r;
    }

    @Override
    public int size() { // Για τον αριθμό των στοιχείων του πίνακα
        return (r - f + array.length) % array.length;
    }

    @Override
    public void clear() { // Για τη διαγραφή όλων των στοιχείων του πίνακα
        this.array =(E[]) new Object[INITIAL_CAPACITY]; // Οι εντολές του constructor
        this.f = 0; 
        this.r = 0; 
        modCount++; // Αύξηση του μετρητή modCount
    }

    @Override
    public Iterator<E> iterator() { // Για τον iterator
        return new DequeIterator();
    }

    @Override
    public Iterator<E> descendingIterator() { // Για τον descending iterator
        return new DescendingDequeIterator();
    }

    private class DequeIterator implements Iterator<E> {
        private int cur; // Ο δείκτης για το στοιχείο στο οποίο βρισκόμαστε
        private int expectedModCount = modCount; // Η αρχική τιμή του μετρητή αλλαγών

        public DequeIterator() { // Constructor
            cur = f; // Αρχικοποίηση του δείκτη στο f δηλαδή στην πρώτη θέση του πίνακα
        }

        @Override
        public boolean hasNext() { // Αν υπάρχει επόμενο στοιχείο
            return cur != r ;
        }
           
        @Override
        public E next() { // Για το επόμενο στοιχείο του πίνακα

            checkForComodification(); // Έλεγχος για παράλληλη τροποποίηση του πίνακα

            if (!hasNext()) { // Έλεγχος αν υπάρχει επόμενο στοιχείο
                throw new NoSuchElementException("There are not more elements in the deque.");
            }

            E result = array[cur]; // Αποθήκευση του στοιχείου
            cur = (cur + 1) % array.length; // Αύξηση του δείκτη cur
            return result; // Επιστροφή του στοιχείου
        }
        
        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class DescendingDequeIterator implements Iterator<E> {
        private int cur; // Ο δείκτης για το στοιχείο στο οποίο βρισκόμαστε
        private final int expectedModCount=modCount; // Η αρχική τιμή του μετρητή αλλαγών

        public DescendingDequeIterator() { // Constructor
            cur = (r - 1 + array.length) % array.length; // Αρχικοποίηση του δείκτη στο r-1 δηλαδή στην τελευταία θέση του πίνακα
        } 

        @Override
        public boolean hasNext() { // Αν υπάρχει επόμενο στοιχείο
            return cur != (f - 1 + array.length) % array.length;
        }

        @Override
        public E next() { // Για το επόμενο στοιχείο του πίνακα

            checkForComodification(); // Έλεγχος για παράλληλη τροποποίηση του πίνακα

            if (!hasNext()) { // Έλεγχος αν υπάρχει επόμενο στοιχείο
                throw new NoSuchElementException("There are not more elements in the deque.");
            }

            E result = array[cur]; // Αποθήκευση του στοιχείου
            cur = (cur - 1 + array.length) % array.length; // Μείωση του δείκτη cur
            return result; // Επιστροφή του στοιχείου
        }
        
        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public void doubleCapacity() { // Για τον διπλασιασμό του μεγέθους του πίνακα
        int newCapacity = 2 * array.length; // Το νέο μέγεθος του πίνακα
        int size=size();
        E[] newArray=(E[]) new Object[newCapacity]; // Ο νέος πίνακας
        for (int i=0;i<size;i++) {
            newArray[i]=array[(f + i) % array.length]; // Τοποθετώ τα στοιχεία του αρχικού πίνακα στον καινούριο με την ίδια σειρά
        }
        array=newArray; // Ο αρχικός πίνακας δείχνει στον νέο πίνακα
        f=0; // Αρχικοποίηση του δείκτη f στο πρώτο στοιχείο του πίνακα
        r=size; // Αρχικοποίηση του δείκτη r στο τελευταίο στοιχείο του πίνακα
    }

    public void halfCapacity() { // Για τον υποδιπλασιασμό του μεγέθους του πίνακα
        if (array.length <= INITIAL_CAPACITY) {
            return;
	    }
        int newCapacity= array.length / 2; // Το νέο μέγεθος του πίνακα
        int size=size();
        assert size <= newCapacity; // Έλεγχος αν το αρχικό μέγεθος του πίνακα είναι μικρότερο απο το νέο μέγεθος
        E[] newArray=(E[]) new Object[newCapacity]; // Ο νέος πίνακας
        for(int i=0;i<size;i++) {
            newArray[i]=array[(f + i) % array.length]; // Τοποθετώ τα στοιχεία του αρχικού πίνακα στον καινούριο με την ίδια σειρά
        }
	    array=newArray; // Ο αρχικός πίνακας δείχνει στον νέο πίνακα
        f = 0; // Αρχικοποίηση του δείκτη f στο πρώτο στοιχείο του πίνακα
        r = size; // Αρχικοποίηση του δείκτη r στο τελευταίο στοιχείο του πίνακα
    }
}