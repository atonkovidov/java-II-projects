// Revision of MyPriorityQueue to use a generic parameter to compare objects 

import java.util.Comparator;

public class Assignment2 {
	public static void main(String[] args) {
		new Assignment2();
	}

	public Assignment2() {
		// construct queue with a comparator object, say
		// s1, s2 with compareToIgnoreCase

		MyPriorityQueue queue = new MyPriorityQueue(new CaseComparator());

		queue.enqueue("Macon");
		queue.enqueue("Atlanta");
		queue.enqueue("Savannah");
		queue.enqueue("Augusta");
		queue.enqueue("Columbus");
		while (queue.getSize() > 0) {
			System.out.print(queue.dequeue() + " ");
		}

		// construct queue1 with a different comparator object, say
		// s1, s2 with length of the string

		MyPriorityQueue queue1 = new MyPriorityQueue(new LengthComparator());

		queue1.enqueue("ABC");
		queue1.enqueue("A");
		queue1.enqueue("AB");
		queue1.enqueue("ABCDE");
		queue1.enqueue("ABCD");
		System.out.println();
		while (queue1.getSize() > 0) {
			System.out.print(queue1.dequeue() + " ");
		}

	}

	class CaseComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			return s1.compareToIgnoreCase(s2);
		}
	}

	class LengthComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			if (s1.length() > s2.length())
				return 1;
			else if (s1.length() == s2.length())
				return 0;
			else
				return -1;
		}
	}
}

class MyPriorityQueue<E> {
	private Heap<E> heap = new Heap<>();

	public MyPriorityQueue(Comparator<? super E> comparator) {
		heap = new Heap<>(comparator);
	}

	public void enqueue(E newObject) {
		if (getSize() == 0)
			heap.add(newObject);
		else {
			heap.add(newObject);
		}
	}

	public E dequeue() {
		return heap.remove();
	}

	public int getSize() {
		return heap.getSize();
	}
}

class Heap<E> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();
	public Comparator comparator = (s1, s2) -> ((Comparable<E>)s1).compareTo((E)s2);

	/** Create a default heap */
	public Heap() {
	}

	/** Create a heap from an array of objects */
	public Heap(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}
	
	public Heap(Comparator comparator) {
		this.comparator = comparator;
	}

	/** Add a new object into the heap */
	public void add(E newObject) {
		list.add(newObject); // Append to the heap
		int currentIndex = list.size() - 1; // The index of the last node

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			// Swap if the current object is greater than its parent
			if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			} else
				break; // The tree is a heap now

			currentIndex = parentIndex;
		}
	}

	/** Remove the root from the heap */
	public E remove() {
		if (list.size() == 0)
			return null;

		E removedObject = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);

		int currentIndex = 0;
		while (currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;

			// Find the maximum between two children
			if (leftChildIndex >= list.size())
				break; // The tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (comparator.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// Swap if the current node is less than the maximum
			if (comparator.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			} else
				break; // The tree is a heap
		}

		return removedObject;
	}

	/** Get the number of nodes in the tree */
	public int getSize() {
		return list.size();
	}
}


/* Output

Savannah Macon Columbus Augusta Atlanta 
ABCDE ABCD ABC AB A 

*/
