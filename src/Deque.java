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
    
    Node first;
    int size;
    
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
        else
        {
            Node newFirst = new Node(item);
            newFirst.next = first;
            first = newFirst;
            size++;
        }
    }
    
    public void addLast (Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is nul");
        }
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        Node newLast = new Node(item);
        newLast.next = null;
        current.next = newLast;
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
        test.addFirst(testVal);
        test.addFirst(testVal2);
        System.out.println("Size: " + test.size());
    }
}
