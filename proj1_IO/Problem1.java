package hw1;

import java.util.Scanner;

public class Problem1
{
	// 20161563_°í¼ººó problem 1
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
	
		System.out.print("ASCII code teller. Enter a letter: ");
		String inp = sc.nextLine();
		
		if(inp.length() != 1)
			System.out.println("You must input a single uppercase or lowercase alphabet");
		else
		{
			char alpha = inp.charAt(0);
		
			if(('A' <= alpha && alpha <= 'Z') || ('a' <= alpha && alpha <= 'z'))
				System.out.println("The ASCII code of "+alpha+" is "+ (int)alpha);
			else
				System.out.println("You must input a single uppercase or lowercase alphabet");
		}	
		
		sc.close();
	}
}
