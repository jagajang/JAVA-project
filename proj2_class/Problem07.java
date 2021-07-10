package homework;

import java.util.Scanner;

interface IntSequenceStr
{
	abstract boolean hasNext();
	abstract int next();
}

class BinarySequenceStr implements IntSequenceStr
{
	private int val = 0;
	private int msb = 1;
	
	public BinarySequenceStr(int num) {
		val = num;
		while(2 * msb < val)
			msb *= 2;
	}
	
	public boolean hasNext() {
		return msb != 0;
	}
	
	public int next()
	{
		int ret = msb & val;
		
		val -= ret;
		msb /= 2;
		
		if(ret != 0)
			return 1;
		else
			return 0;
	}
}

public class Problem07 {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive interger: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: " + num);
		IntSequenceStr seq = new BinarySequenceStr(num);
		System.out.print("Binary number: ");
		while(seq.hasNext()) System.out.print(seq.next());
		System.out.println(" ");
	}
}
