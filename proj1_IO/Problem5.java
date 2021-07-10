
package hw1;

import java.util.Scanner;

public class Problem5
{
	// 20161563_°í¼ººó problem 5
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter exam scores of each student.");
		
		int firSt = 0, firSco = -1;
		int secSt = 0, secSco = -1;

		for(int i = 1; i <= 5; i++)
		{
			System.out.printf("Score of student %d: ", i);
			
			int score = sc.nextInt();
			
			if(score > firSco)
			{
				secSt = firSt;
				secSco = firSco;
				
				firSt = i;
				firSco = score;
			}
			else if(score > secSco)
			{
				secSt = i;
				secSco = score;
			}
		}
		
		System.out.printf("The 1st place is student %d with %d points.\n", firSt, firSco);
		System.out.printf("The 2nd place is student %d with %d points.\n", secSt, secSco);
		
		sc.close();
	}
}