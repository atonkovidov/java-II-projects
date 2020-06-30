// An implementation of Min-Heap based on the Max-Heap presented in the text

public class Assignment1 {

	public static void main(String[] args) {
		Integer[] list = { -44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53 };
		minHeap<Integer> heap = new minHeap<>(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(heap.remove() + " ");
	}
}

class minHeap<E extends Comparable<E>> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	public minHeap() {
	}

	public minHeap(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	public void add(E newObject) {
		list.add(newObject);
		int currentIndex = list.size() - 1;

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;

			if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			}
			else
				break;

			currentIndex = parentIndex;
		}
	}

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

			if (leftChildIndex >= list.size())
				break;
			int minIndex = leftChildIndex;

			if (rightChildIndex < list.size()) {
				if (list.get(minIndex).compareTo(list.get(rightChildIndex)) > 0) {
					minIndex = rightChildIndex;
				}
			}

			if (list.get(currentIndex).compareTo(list.get(minIndex)) > 0) {
				E temp = list.get(minIndex);
				list.set(minIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = minIndex;
			} else
				break;
		}
		return removedObject;
	}

	public int getSize() {
		return list.size();
	}
}

/* Output

-44 -5 -4 -3 0 1 1 2 3 3 4 5 53

*/