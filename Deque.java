/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

// this will be a doubly linked list implementation
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateArgument(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateArgument(item);
        Node oldLast = last; // may point to null(if empty) or Node
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateList();
        Item item = first.item;
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.previous = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateList();
        Item item = last.item;
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            last = last.previous;
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first; // Note: current is the item TO BE returned.

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in Deque");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // validate list
    private void validateList() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    // validate argument
    private void validateArgument(Item arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.addLast("second");
        deque.addLast("Last");
        // String first = deque.removeFirst();
        // String second = deque.removeFirst();
        // String last = deque.removeLast();
        System.out.println(deque.isEmpty());
        for (String s : deque) {
            System.out.println(s);
        }

        // Checklist:
        // addFirst, addLast & exception(null),
        // removeFirst, removeLast & exception(remove from empty list)
        // isEmpty
        // iteration & exception
    }
}
