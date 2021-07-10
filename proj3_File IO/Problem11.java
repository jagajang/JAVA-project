package midterm;

class PalindromeChecker {
	static void check(String str) {
		int len = str.length();
		
		System.out.print(str + " is ");
		for(int i = 0; i < len / 2; i++)
			if(str.charAt(i) != str.charAt(len - 1 - i)) {
				System.out.print("not ");
				break;
			}
		System.out.println("a palindrome.");
		return;
	}
	
	static void check(int val) {
		int max = 1, min = 1;
		
		while(10 * max < val)
			max *= 10;
		
		System.out.print(val + " is ");
		while(min < max) {
			if((val / min) % 10 != (val / max) % 10) {
				System.out.print("not ");
				break;
			}
			min *= 10;
			max /= 10;
		}
		System.out.println("a palindrome.");
		return;
	}
}

public class Problem11 {
	public static void main(String[] args) {
		PalindromeChecker.check("abcde");
		PalindromeChecker.check("abcba");
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321);
	}
}
