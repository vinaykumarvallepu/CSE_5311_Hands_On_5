import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
    private List<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public void bMinHeap(List<T> data) {
        heap = new ArrayList<>(data);
        for (int i = data.size() / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        smallest = findSmallest(smallest, leftChild);
        smallest = findSmallest(smallest, rightChild);

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }
    private void heapifyUp(int i){
        while (i > 0 && heap.get(parentIndex(i)).compareTo(heap.get(i)) > 0) {
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    private int findSmallest(int currentSmallest, int childIndex) {
        if (childIndex < heap.size() && heap.get(childIndex).compareTo(heap.get(currentSmallest)) < 0) {
            return childIndex;
        }
        return currentSmallest;
    }

    public void push(T value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
        
    }

    public T pop() {
        if (heap.isEmpty()) {
            return null;
        }

        T root = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown(0);
        return root;
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        List<Integer> data = List.of(2,10,12,9,18,21,5,33,11,25);

        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.bMinHeap(data);

        System.out.println("Intial Min_heap: " + minHeap.heap);
        minHeap.push(100);
        minHeap.push(200);
        System.out.println("After inserting elment with push(): " + minHeap.heap);
        System.out.println("Popped element: " + minHeap.pop());
        System.out.println("Popped element: " + minHeap.pop());
        System.out.println("After popping the array: " + minHeap.heap);
        
    }
}

// Output
// PS C:\Users\hp\Desktop\Java Practice> javac MinHeap.java
// PS C:\Users\hp\Desktop\Java Practice> java MinHeap.java
// Intial Min_heap: [2, 9, 5, 10, 18, 21, 12, 33, 11, 25]
// After inserting elment with push(): [2, 9, 5, 10, 18, 21, 12, 33, 11, 25, 100, 200]
// Popped element: 2
// Popped element: 5
// After popping the array: [9, 10, 12, 11, 18, 21, 200, 33, 100, 25]
