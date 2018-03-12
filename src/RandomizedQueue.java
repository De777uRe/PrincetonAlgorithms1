import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private class Node {
        Item item;
        Node next;
        
        Node (Item item) {
            this.item = item;
            this.next = null;
        }
    }
    
    private Node first;
    private int size;
    
    public RandomizedQueue() {
        first = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        
        if (first == null) {
            Node newFirst = new Node(item);
            newFirst.next = null;
            first = newFirst;
        }
        else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            
            Node addedNode = new Node(item);
            addedNode.next = null;
            current.next = addedNode;
        }
        size++;
    }
    
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        int nodeNum = StdRandom.uniform(size) + 1;
        
        Node current = first;
        Node previous = first;
        Item retrievedItem = null;
        
        if (size == 1) {
            retrievedItem = first.item;
            first = null;
        }
        else {
            if (nodeNum == 1) {
                first = current.next;
            }
            
            for (int i = 1; i < nodeNum; i++) {
                previous = current;
                current = current.next;
            }
            
            retrievedItem = current.item;
            previous.next = current.next;
            current = null;
        }
        
        size--;
        return retrievedItem;
    }
    
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int nodeNum = StdRandom.uniform(size) + 1;
        Node current = first;
        
        for (int i = 1; i < nodeNum; i++) {
            current = current.next;
        }
        
        return current.item;
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        int[] indexArr = new int[size];
        int index = 0;
        
        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                indexArr[i] = i;
            }
            StdRandom.shuffle(indexArr);
        }
        
        public boolean hasNext() {
            return index != size;
        }
        
        public Item next() {
            if (index == size) {
                throw new NoSuchElementException();
            }
            
            Node current = first;
            
            for (int i = 0; i < indexArr[index]; i++) {
                current = current.next;
            }
            
            index++;
            return current.item;
        }
        
        public void remove() {
            /* Not supported */
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
