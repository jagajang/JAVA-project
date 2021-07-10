package hw1;

import java.util.Scanner;

public class Problem3
{
	// 20161563_°í¼ººó problem 3
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String getText = sc.nextLine();

		System.out.print("Enter a letter: ");
		String getLetter = sc.nextLine();
		
		while(getLetter.length() != 1)
		{
			System.out.print("You must enter a single letter.\nEnter a letter: ");
			getLetter = sc.nextLine();
		}
		
		char oneLetter = getLetter.charAt(0);
		
		int ans = 0;
		for(int i = 0; i < getText.length(); i++)
		{
			if(getText.charAt(i) == oneLetter)
				ans++;
		}
		
		System.out.printf("There are %d %c's in the text.", ans, oneLetter);
		
		sc.close();
	}
}
