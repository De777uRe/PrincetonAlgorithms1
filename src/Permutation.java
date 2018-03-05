import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int inputSize = Integer.parseInt(args[0]);
        
        String inputString = "";
        while (!StdIn.isEmpty()) {
            inputString = StdIn.readString();
            rq.enqueue(inputString);
        }
        
        Iterator<String> it = rq.iterator();
        for (int i = 0; i < inputSize; i++) {
            if (it.hasNext()) {
                StdOut.println(it.next());
            }
        }
    }
}
