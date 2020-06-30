// A non-recursive method (using queues) of finding the file size of a directory

import java.io.File;
import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		// Same code as recursive approach
		System.out.print("Enter a directory or a file: ");
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();
		
		System.out.print(getSize(new File(directory)) + " bytes");
		input.close();
	}
	
	// The queue method of finding directory size
	public static long getSize(File directory) {
		long size = 0;
		java.util.Queue<File> queue = new java.util.LinkedList<>();
		queue.offer(directory);
		
		while(queue.peek() != null) {
			File t = queue.poll();
			if (t.isFile())
				size += t.length();
			else {
				File[] files = directory.listFiles();
				for (int i = 0; files != null && i < files.length; i++)
					queue.offer(files[i]);
			}
		}
		return size;
	}
}

/* Output

Enter a directory or a file: c:\Intel\gp
0 bytes

Enter a directory or a file: c:\Intel\Logs\IntelCPHS.log
0 bytes

Enter a directory or a file: c:\Intel\Logs
30144 bytes

Enter a directory or a file: c:\Intel\Logs\IntelGFX.log
23930 bytes

Enter a directory or a file: c:\Intel\AMD.txt
0 bytes

*/