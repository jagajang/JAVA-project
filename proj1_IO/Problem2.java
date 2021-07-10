package hw1;

import java.util.Scanner;

public class Problem2
{
	// 20161563_°í¼ººó problem 2
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int randomAnswer = (int)(100 * Math.random() + 1);
	
		int small = 1, large = 100;
		for(int t = 1; ; t++)
		{
			System.out.printf("[%d] Guess a number (%d-%d): ", t, small, large);
			
			int userInput = sc.nextInt();
			
			if(userInput == randomAnswer)
			{
				System.out.println("Correct! Number of guesses: "+ t);
				break;
			}
			else if(userInput < randomAnswer)
			{
				System.out.println("Too small!");
				small = Math.max(small, userInput);
			}
			else
			{
				System.out.println("Too large!");
				large = Math.min(large, userInput);
			}
		}
		
		sc.close();
	}
}
