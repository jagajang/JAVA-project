package homework;

interface IntSequence
{
	default boolean hasNext() {
		return true;
	}
	
	abstract int next();
}

class FibonacciSequence implements IntSequence
{
	private int pre = 1;
	private int val = 0;
	
	public int next()
	{
		int nex = pre + val;
		pre = val;
		val = nex;
		return this.pre;
	}
}

public class Problem06
{
	public static void main(String[] args)
	{
		IntSequence seq = new FibonacciSequence();
		for(int i = 0; i < 20; i++)	{
			if(seq.hasNext() == false) break;
			System.out.print(seq.next() + " ");
		}
		System.out.println(" ");
	}
}
