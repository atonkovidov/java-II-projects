package Assignment1;

public class Test {

	public static void main(String[] args) {
		OpenDouble<Integer, String> test = new OpenDouble<>();
		
		test.put(12, "A");
		test.put(1, "B");
		test.put(2, "C");
		test.put(3, "D");
		test.put(4, "E");
		test.put(16, "F");
		test.put(32, "G");
		test.put(48, "H");
		
		System.out.print(test);
	}
}