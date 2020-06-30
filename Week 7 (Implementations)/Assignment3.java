// A Fibonacci number iterator 

import java.util.Iterator;

public class Assignment3 {
	public static void main(String[] args) {
		Iterator<Integer> iterator = new FibonacciIterator(100000);
// print out all Fibonacci numbers before 100000
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
	}

	static class FibonacciIterator implements java.util.Iterator<Integer> {
		private int limit;
		int sum;
		int f1 = 1;
		int f2 = 1;

		public FibonacciIterator(int limit) {
			this.limit = limit;
		}

		@Override
		public Integer next() {
			sum = f1 + f2;
			f1 = f2;
			f2 = sum;
			return sum;
		}

		@Override
		public boolean hasNext() {
			if (f1 + f2 <= limit)
				return true;
			else
				return false;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Method not supported");
		}
	}
}
