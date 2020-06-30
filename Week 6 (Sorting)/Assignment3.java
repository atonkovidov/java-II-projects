// A program to calculate and display execution time for various sorting methods 

import java.util.*;

public class Assignment3 {

	public static void main(String[] args) {
		// Basic initialization
		final int initialSize = 1000;
		final int finalSize = 9000;

		// Beginning of table construction
		System.out.println(" Array\t | Selection\t Bubble\t\t Merge\t\t Quick\t\t Heap\t\t Radix\t");
		System.out.println(" size \t | Sort\t\t Sort\t\t Sort\t\t Sort\t\t Sort\t\t Sort\t");
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		// Running all the sorts for various input sizes
		for (int i = initialSize; i <= finalSize; i += initialSize) {
			long[] times = allSorts(i);
			System.out.printf(" %-6d\t | ", i);
			for (int j = 0; j < 6; j++)
				System.out.print(" " + times[j] + "\t\t");
			System.out.println();
		}
	}

	public static long[] allSorts(int amount) {
		long[] times = new long[6];

		double[] numbers = new double[amount];
		for (int i = 0; i < amount; i++)
			numbers[i] = Math.random() * amount + 1;

		// Selection sort
		long startTime = System.currentTimeMillis();
		RecursiveSelectionSort.sort(numbers);
		long endTime = System.currentTimeMillis();
		times[0] = endTime - startTime;

		// Bubble sort
		startTime = System.currentTimeMillis();
		BubbleSort.bubbleSort(numbers);
		endTime = System.currentTimeMillis();
		times[1] = endTime - startTime;

		// Merge sort
		startTime = System.currentTimeMillis();
		MergeSort.mergeSort(numbers);
		endTime = System.currentTimeMillis();
		times[2] = endTime - startTime;

		// Quick sort
		startTime = System.currentTimeMillis();
		QuickSort.quickSort(numbers);
		endTime = System.currentTimeMillis();
		times[3] = endTime - startTime;

		// Heap sort
		Double[] numbers2 = new Double[amount];
		for (int i = 0; i < amount; i++)
			numbers2[i] = numbers[i];
		startTime = System.currentTimeMillis();
		HeapSort.heapSort(numbers2);
		endTime = System.currentTimeMillis();
		times[4] = endTime - startTime;

		// Radix sort
		ArrayList<Integer> numbers3 = new ArrayList<Integer>();
		for (int i = 0; i < amount; i++)
			numbers3.add((int) numbers[i]);
		startTime = System.currentTimeMillis();
		RadixSort.radixSort(numbers3);
		endTime = System.currentTimeMillis();
		times[5] = endTime - startTime;
		
		// Returning the array with sorting times
		return times;
	}

}

class RecursiveSelectionSort {
	public static void sort(double[] list) {
		sort(list, 0, list.length - 1); // Sort the entire list
	}

	private static void sort(double[] list, int low, int high) {
		double[] list2 = list.clone();
		if (low < high) {
// Find the smallest number and its index in list[low .. high]
			int indexOfMin = low;
			double min = list2[low];
			for (int i = low + 1; i <= high; i++) {
				if (list2[i] < min) {
					min = list2[i];
					indexOfMin = i;
				}
			}

// Swap the smallest in list[low .. high] with list[low]
			list2[indexOfMin] = list2[low];
			list2[low] = min;

//Sort the remaining list[low+1 .. high]
			sort(list2, low + 1, high);
		}
	}
}

class BubbleSort {
	/** Bubble sort method */
	public static void bubbleSort(double[] list) {
		boolean needNextPass = true;
		double[] list2 = list.clone();

		for (int k = 1; k < list2.length && needNextPass; k++) {
// Array may be sorted and next pass not needed
			needNextPass = false;
			for (int i = 0; i < list2.length - k; i++) {
				if (list2[i] > list2[i + 1]) {
// Swap list[i] with list[i + 1]
					double temp = list2[i];
					list2[i] = list2[i + 1];
					list2[i + 1] = temp;

					needNextPass = true; // Next pass still needed
				}
			}
		}
	}
}

class MergeSort {
	/** The method for sorting the numbers */
	public static void mergeSort(double[] list) {
		double[] list2 = list.clone();

		if (list2.length > 1) {
			// Merge sort the first half
			double[] firstHalf = new double[list2.length / 2];
			System.arraycopy(list2, 0, firstHalf, 0, list2.length / 2);
			mergeSort(firstHalf);

			// Merge sort the second half
			int secondHalfLength = list2.length - list2.length / 2;
			double[] secondHalf = new double[secondHalfLength];
			System.arraycopy(list2, list2.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list2);
		}
	}

	/** Merge two sorted lists */
	public static void merge(double[] list1, double[] list2, double[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2])
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}
}

class QuickSort {
	public static void quickSort(double[] list) {
		double[] list2 = list.clone();
		quickSort(list2, 0, list2.length - 1);
	}

	public static void quickSort(double[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	public static int partition(double[] list, int first, int last) {
		int pivot = (int) list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
// Search forward from left
			while (low <= high && list[low] <= pivot)
				low++;

// Search backward from right
			while (low <= high && list[high] > pivot)
				high--;

// Swap two elements in the list
			if (high > low) {
				double temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;

// Swap pivot with list[high]
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}
}

class Heap<E extends Comparable<E>> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	/** Create a default heap */
	public Heap() {
	}

	/** Create a heap from an array of objects */
	public Heap(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Add a new object into the heap */
	public void add(E newObject) {
		list.add(newObject); // Append to the heap
		int currentIndex = list.size() - 1; // The index of the last node

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
// Swap if the current object is greater than its parent
			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
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
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// Swap if the current node is less than the maximum
			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
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

class HeapSort {
	/** Heap sort method */
	public static <E extends Comparable<E>> void heapSort(E[] list) {
// Create a Heap of integers
		Heap<E> heap = new Heap<>();

//Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);

// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}
}

class RadixSort {
	public static void radixSort(ArrayList<Integer> numbers) {
		int listSize = numbers.size();
		List<Integer>[] bucket = new ArrayList[10];
		for (int i = 0; i < 10; i++)
			bucket[i] = new ArrayList<Integer>();

		for (int i = 1; i <= Math.floor(Math.log10(listSize)) + 1; i++) {
			for (int j = 0; j < listSize; j++) {
				int current = numbers.get(j);
				int key = (int) (current % Math.pow(10, i) / Math.pow(10, i - 1));
				bucket[key].add(current);
			}

			int k = 0;
			for (int r = 0; r < 10; r++) {
				if (!bucket[r].isEmpty()) {
					int bucketSize = bucket[r].size();
					for (int s = 0; s < bucketSize; s++) {
						numbers.set(k++, bucket[r].get(s));
					}
				}
			}

			for (int e = 0; e < 10; e++)
				bucket[e].clear();

		}
	}
}

/* Output

Array	 | Selection	 Bubble		 Merge		 Quick		 Heap		 Radix	
size 	 | Sort		 Sort		 Sort		 Sort		 Sort		 Sort	
---------------------------------------------------------------------------------------------------
1000  	 |  10		 10		 0		 10		 10		 0		
2000  	 |  10		 10		 0		 0		 0		 10		
3000  	 |  45		 14		 1		 0		 1		 0		
4000  	 |  40		 20		 0		 0		 10		 0		
5000  	 |  40		 40		 0		 10		 0		 0		
6000  	 |  60		 60		 0		 0		 0		 10		
7000  	 |  90		 80		 0		 0		 0		 0		
8000  	 |  120		 110		 10		 0		 0		 10		
9000  	 |  91		 150		 10		 0		 0		 10		

*/
