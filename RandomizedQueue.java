/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        validateArgument(item);
        if (n == q.length) {
            resize(q.length * 2);
        }
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        validateQueue();
        int index = StdRandom.uniform(n);
        Item item = q[index];
        // shuffle
        q[index] = q[n - 1];
        q[n - 1] = null;
        n--;
        if (n == q.length / 4) {
            resize(q.length / 4);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateQueue();
        int index = StdRandom.uniform(n);
        return q[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // randomized queue iterator
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private final Item[] items;

        RandomizedQueueIterator() {
            validateQueue();
            current = 0;
            items = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                items[i] = q[i];
            }
            StdRandom.shuffle(items);
        }


        public boolean hasNext() {
            return current < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate over");
            }
            Item item = items[current];
            current++;
            return item;
        }
    }

    // resize array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // validate argument
    private void validateArgument(Item arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
    }

    // validate queue
    private void validateQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("first");
        randomizedQueue.enqueue("second");
        randomizedQueue.enqueue("third");
        randomizedQueue.enqueue("fourth");
        // System.out.println(randomizedQueue.dequeue());
        // System.out.println(randomizedQueue.dequeue());
        // System.out.println(randomizedQueue.dequeue());
        // System.out.println(randomizedQueue.dequeue());
        // System.out.println(randomizedQueue.sample());
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        // checklist:
        // enqueue & exception -done
        // dequeue(random) & exception -done
        // sample & exception -done
        // iteration (random) -done
    }

}
