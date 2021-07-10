package hw1;

import java.util.Scanner;

public class Problem4
{
	// 20161563_°í¼ººó problem 4
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String getText = sc.nextLine();
				
		System.out.print("Enter a string: ");
		String getStr = sc.nextLine();
		
		while(getStr.length() == 0)
		{
			System.out.print("You must enter a string.\nEnter a string: ");
			getStr = sc.nextLine();
		}
		
		int ans = 0;
		int compareTimes = getText.length() - getStr.length();
		for(int i = 0; i < compareTimes; i++)
		{
			if(getText.substring(i, i + getStr.length()).equals(getStr))
				ans++;
		}
		
		System.out.printf("There are %d instances of \"%s\"", ans, getStr);
		
		sc.close();
	}
}