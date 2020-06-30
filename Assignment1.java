
// A program to read and evaluate a postfix expression using stacks

import java.util.Scanner;
import java.util.Stack;

public class Assignment1 {
	public static void main(String[] args) {
		// Initializing stack and scanner
		Stack<Double> expression = new Stack();
		Scanner input = new Scanner(System.in);
		double result;

		// Processing the postfix expression
		String problem = input.nextLine();
		for (String e : problem.split(" ")) {
			if (e.equals("+")) {
				result = expression.pop() + expression.pop();
				expression.push(result);
			}
			else if (e.equals("-")) {
				result = expression.pop() - expression.pop();
				expression.push(result);
			}
			else if (e.equals("*")) {
				result = expression.pop() * expression.pop();
				expression.push(result);
			}
			else if (e.equals("/")) {
				result = expression.pop() / expression.pop();
				expression.push(result);
			}
			else
				expression.push(Double.parseDouble(e));
		}
		
		// Printing out result
		System.out.print("The result is " + expression.peek() + ".");
		input.close();
	}
}

/* Output

1 2 + 3 *
The result is 9.0.

3 1 * 12 /
The result is 4.0.

*/