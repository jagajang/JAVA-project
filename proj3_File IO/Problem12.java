package midterm;

import java.util.ArrayList;

class SubsequenceChecker {
	static void check(String str1, String str2) {
		int len = str1.length();
		int found = 0;
		
		ArrayList<Integer> queue = new ArrayList<Integer>();
		
		int err = 1;
		for(int i = 0; i < len; i++)
		{
			if(found == str2.length()) {
				err = 0;
				break;
			}
			
			if(str1.charAt(i) == str2.charAt(found)) {
				queue.add(i);
				found++;
			}
		}
		
		if(found == str2.length())
			err = 0;
		
		System.out.print(str2 + " is ");
		if(err == 1) System.out.print("not ");
		System.out.println("a subsequence of " + str1);
		
		if(err == 0) {
			for(int i = 0; i < found; i++)
				System.out.print(queue.get(i) + " ");
			System.out.println();
		}
		
		return;
	}
}

public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
	}
}