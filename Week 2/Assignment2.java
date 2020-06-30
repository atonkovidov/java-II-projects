// Use a method reference to an instance method. 
// A functional interface for numeric predicates that operate 
// on integer values. 
interface IntPredicate {
	boolean test(int n);
}

// This class stores an int value and defines the instance
// method isFactor(), which returns true if its argument
// is a factor of the stored value.
class MyIntNum {
	private int v;

	MyIntNum(int x) {
		v = x;
	}

	int getNum() {
		return v;
	}

// Return true if n is a factor of v.
	boolean isFactor(int n) {
		return (v % n) == 0;
	}

	boolean hasCommonFactor(int n) {
// your work
		boolean result = false;
		int factor = 0;
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				factor = n / i;
			
			if (v % factor == 0)
				result = true;
		}
		return result;
	}
}

public class Assignment2 {
	public static void main(String args[]) {
		boolean result;
		MyIntNum myNum = new MyIntNum(12);
		MyIntNum myNum2 = new MyIntNum(16);
// Here, a method reference to isFactor on myNum is created.
		IntPredicate ip = myNum::isFactor;
// Now, it is used to call isFactor() via test().
		result = ip.test(3);
		if (result)
			System.out.println("3 is a factor of " + myNum.getNum());
// This time, a method reference to isFactor on myNum2 is created.
// and used to call isFactor() via test().
		ip = myNum2::isFactor;
		result = ip.test(3);
		if (!result)
			System.out.println("3 is not a factor of " + myNum2.getNum());
// your work
		IntPredicate cf = myNum::hasCommonFactor;
		result = cf.test(9);
		if (result)
			System.out.println("9 and " + myNum.getNum() + " have at least one common factor.");
		else
			System.out.println("9 and " + myNum.getNum() + " have no common factors.");

		cf = myNum2::hasCommonFactor;
		result = cf.test(9);
		if (result)
			System.out.println("9 and " + myNum2.getNum() + " have at least one common factor.");
		else
			System.out.println("9 and " + myNum2.getNum() + " have no common factors.");
	}
}
// 3 is a factor of 12
// 3 is not a factor of 16

/* Output

3 is a factor of 12
3 is not a factor of 16
9 and 12 have at least one common factor.
9 and 16 have no common factors.

*/