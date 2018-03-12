import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
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
    
    public Deque() {
        first = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst (Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        
        Node newFirst = new Node(item);
        newFirst.next = first;
        first = newFirst;
        size++;
    }
    
    public void addLast (Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is nul");
        }
        
        if (first == null) {
            Node newFirst = new Node(item);
            first = newFirst;
        }
        else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            
            Node newLast = new Node(item);
            newLast.next = null;
            current.next = newLast;
        }
        size++;
    }
    
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        Node retrieveFirst = first;
        first = retrieveFirst.next;
        size--;
        return retrieveFirst.item;
    }
    
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }
        
        Node retrieveLast = current.next;
        current.next = null;
        size--;
        return retrieveLast.item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (current.next == null) {
                throw new NoSuchElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            /* Not supported */
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main (String[] args) {
        Deque<String> test = new Deque<String>();
        String testVal = "12";
        String testVal2 = "23";
        String testVal3 = "34";
        String testVal4 = "45";
        test.addFirst(testVal);
        test.addLast(testVal2);
        test.addFirst(testVal3);
        test.addLast(testVal4);
        System.out.println("Size: " + test.size());
        while (test.size() != 0) {
            System.out.println("Item: " + test.removeFirst());
        }
    }
}
