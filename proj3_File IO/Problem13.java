package midterm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Text {
	private int num[] = new int[26];
	
	public boolean readTextFromFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while(true) {
				String inp = br.readLine();

				if(inp == null) break;
			
				for(int i = 0; i < inp.length(); i++)
				{
					char c = inp.charAt(i);
				
					if('A' <= c && c <= 'Z')
						num[c - 'A']++;
					else if('a' <= c && c <= 'z')
						num[c - 'a']++;
				}
			}
		
			br.close();
			return true;
		}
		catch (IOException e) {
			System.out.println("Input file not found.");
			return false;
		}
	}
	
	public int countChar(char c) {			
		return this.num[c - 'a'];
	}
}

public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text();
		if(t.readTextFromFile("input_prob13.txt"))
			for(char c = 'a'; c <= 'z'; c++)
				System.out.println(c + ": " + t.countChar(c));
	}
}