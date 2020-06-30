// Comparing MyHashSet and MyArrayList 

package Assignment2;

import java.util.*;

public class Assignment2 {
	public static void main(String[] args) {
		MyHashSet<Double> set = new MyHashSet<>();
		MyArrayList<Double> list = new MyArrayList<>();
		final int times = 10;
		final int base = 100;

		for (int k = 1; k < 4; k++) {
			for (int i = 0; i < base * Math.pow(times, k); i++) {
				double value = Math.random() * base * Math.pow(times, k);
				set.add(value);
				list.add(value);
			}
			
			List<Double> list1 = new ArrayList<>();
			for (int i = 0; i < base * Math.pow(times, k); i++) {
				list1.add(Math.random() * base * Math.pow(times, k) * 2);
			} // to do
			
			// Timer for MyHashSet
			long setStart = System.currentTimeMillis();
			for (Double e: list1) {
				set.contains(e);
			}
			long setStop = System.currentTimeMillis();
			
			// Timer for MyArrayList
			long listStart = System.currentTimeMillis();
			for (Double e: list1) {
				list.contains(e);
			}
			long listStop = System.currentTimeMillis();
			
			// Displaying results
			System.out.print(base * Math.pow(times, k) + " numbers\nMyHashSet: " + (setStop - setStart) + " milliseconds\n");
			System.out.print("MyArrayList: " + (listStop - listStart) + " milliseconds\n\n");
		}
	}

	public static class MyHashSet<E> implements Collection<E> {
		// Define the default hash table size. Must be a power of 2
		private static int DEFAULT_INITIAL_CAPACITY = 4;

		// Define the maximum hash table size. 1 << 30 is same as 2^30
		private static int MAXIMUM_CAPACITY = 1 << 30;

		// Current hash table capacity. Capacity is a power of 2
		private int capacity;

		// Define default load factor
		private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

		// Specify a load factor threshold used in the hash table
		private float loadFactorThreshold;

		// The number of elements in the set
		private int size = 0;

		// Hash table is an array with each cell that is a linked list
		private LinkedList<E>[] table;

		/** Construct a set with the default capacity and load factor */
		public MyHashSet() {
			this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
		}

		/**
		 * Construct a set with the specified initial capacity and default load factor
		 */
		public MyHashSet(int initialCapacity) {
			this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
		}

		/**
		 * Construct a set with the specified initial capacity and load factor
		 */
		public MyHashSet(int initialCapacity, float loadFactorThreshold) {
			if (initialCapacity > MAXIMUM_CAPACITY)
				this.capacity = MAXIMUM_CAPACITY;
			else
				this.capacity = trimToPowerOf2(initialCapacity);

			this.loadFactorThreshold = loadFactorThreshold;
			table = new LinkedList[capacity];
		}

		@Override /** Remove all elements from this set */
		public void clear() {
			size = 0;
			removeElements();
		}

		@Override /** Return true if the element is in the set */
		public boolean contains(Object e) {
			int bucketIndex = hash(e.hashCode());
			if (table[bucketIndex] != null) {
				LinkedList<E> bucket = table[bucketIndex];
				for (E element : bucket)
					if (element.equals(e))
						return true;
			}

			return false;
		}

		@Override /** Add an element to the set */
		public boolean add(E e) {
			if (contains(e)) // Duplicate element not stored
				return false;

			if (size + 1 > capacity * loadFactorThreshold) {
				if (capacity == MAXIMUM_CAPACITY)
					throw new RuntimeException("Exceeding maximum capacity");

				rehash();
			}

			int bucketIndex = hash(e.hashCode());

			// Create a linked list for the bucket if it is not created
			if (table[bucketIndex] == null) {
				table[bucketIndex] = new LinkedList<E>();
			}

			// Add e to hashTable[index]
			table[bucketIndex].add(e);

			size++; // Increase size

			return true;
		}

		@Override /** Remove the element from the set */
		public boolean remove(Object e) {
			if (!contains(e))
				return false;

			int bucketIndex = hash(e.hashCode());

			// Create a linked list for the bucket if it is not created
			if (table[bucketIndex] != null) {
				LinkedList<E> bucket = table[bucketIndex];
				for (E element : bucket)
					if (e.equals(element)) {
						bucket.remove(element);
						break;
					}
			}

			size--; // Decrease size

			return true;
		}

		@Override /** Return true if the set contains no elements */
		public boolean isEmpty() {
			return size == 0;
		}

		@Override /** Return the number of elements in the set */
		public int size() {
			return size;
		}

		@Override /** Return an iterator for the elements in this set */
		public java.util.Iterator<E> iterator() {
			return new MyHashSetIterator(this);
		}

		/** Inner class for iterator */
		private class MyHashSetIterator implements java.util.Iterator<E> {
			// Store the elements in a list
			private java.util.ArrayList<E> list;
			private int current = 0; // Point to the current element in list
			private MyHashSet<E> set;

			/** Create a list from the set */
			public MyHashSetIterator(MyHashSet<E> set) {
				this.set = set;
				list = setToList();
			}

			@Override /** Next element for traversing? */
			public boolean hasNext() {
				if (current < list.size())
					return true;

				return false;
			}

			@Override /** Get current element and move cursor to the next */
			public E next() {
				return list.get(current++);
			}

			@Override /** Remove the current element and refresh the list */
			public void remove() {
				// Delete the current element from the hash set
				set.remove(list.get(current));
				list.remove(current); // Remove current element from the list
			}
		}

		/** Hash function */
		private int hash(int hashCode) {
			return supplementalHash(hashCode) & (capacity - 1);
		}

		/** Ensure the hashing is evenly distributed */
		private static int supplementalHash(int h) {
			h ^= (h >>> 20) ^ (h >>> 12);
			return h ^ (h >>> 7) ^ (h >>> 4);
		}

		/** Return a power of 2 for initialCapacity */
		private int trimToPowerOf2(int initialCapacity) {
			int capacity = 1;
			while (capacity < initialCapacity) {
				capacity <<= 1;
			}

			return capacity;
		}

		/** Remove all e from each bucket */
		private void removeElements() {
			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					table[i].clear();
				}
			}
		}

		/** Rehash the set */
		private void rehash() {
			java.util.ArrayList<E> list = setToList(); // Copy to a list
			capacity <<= 1; // Double capacity
			table = new LinkedList[capacity]; // Create a new hash table
			size = 0; // Reset size

			for (E element : list) {
				add(element); // Add from the old table to the new table
			}
		}

		/** Copy elements in the hash set to an array list */
		private java.util.ArrayList<E> setToList() {
			java.util.ArrayList<E> list = new java.util.ArrayList<>();

			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					for (E e : table[i]) {
						list.add(e);
					}
				}
			}

			return list;
		}

		@Override
		public String toString() {
			java.util.ArrayList<E> list = setToList();
			StringBuilder builder = new StringBuilder("[");

			// Add the elements except the last one to the string builder
			for (int i = 0; i < list.size() - 1; i++) {
				builder.append(list.get(i) + ", ");
			}

			// Add the last element in the list to the string builder
			if (list.size() == 0)
				builder.append("]");
			else
				builder.append(list.get(list.size() - 1) + "]");

			return builder.toString();
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for (Object e : c)
				if (!this.contains(e))
					return false;

			return true;
		}

		/**
		 * Adds the elements in otherList to this list. Returns true if this list
		 * changed as a result of the call
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			int originalSize = size;

			for (Object e : c)
				add((E) e);
			if (originalSize == size)
				return false;
			return true;
		}

		/**
		 * Removes all the elements in otherList from this list Returns true if this
		 * list changed as a result of the call
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			int originalSize = size;

			for (Object e : c)
				remove((E) e);
			if (originalSize == size)
				return false;
			return true;
		}

		/**
		 * Retains the elements in this list that are also in otherList Returns true if
		 * this list changed as a result of the call
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			int originalSize = size;
			MyHashSet temp = new MyHashSet(capacity, loadFactorThreshold);

			for (Object e : c) {
				if (this.contains(e))
					temp.add(e);
			}

			clear();
			addAll(temp);
			temp.clear();

			if (originalSize == size)
				return false;
			return true;
		}

		@Override
		public Object[] toArray() {
			Object[] array = new Object[size];
			MyHashSetIterator iterator = new MyHashSetIterator(this);
			;

			for (int i = 0; i < size; i++)
				array[i] = iterator.next();
			return array;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			T[] array = Arrays.copyOf(a, size);
			MyHashSetIterator iterator = new MyHashSetIterator(this);
			;

			for (int i = 0; i < size; i++)
				array[i] = (T) iterator.next();
			return array;
		}

		public MyHashSet(Collection<E> collection) {
			this(collection.size());
			this.addAll(collection);
		}

		public MyHashSet(E[] list) {
			this(list.length);
			for (E e : list) {
				this.add(e);
			}
		}
	}
}


/* Output

1000.0 numbers
MyHashSet: 0 milliseconds
MyArrayList: 25 milliseconds

10000.0 numbers
MyHashSet: 3 milliseconds
MyArrayList: 310 milliseconds

100000.0 numbers
MyHashSet: 15 milliseconds
MyArrayList: 32876 milliseconds

*/