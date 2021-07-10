package midterm;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Item {
	private String name;
	private int time;
	
	public Item(String newName, int newTime) {
		this.name = newName;
		this.time = newTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addTime() {
		this.time++;
	}
	
	public String toString() {
		return this.name + " " + this.time;
	}
}

class MyFileReader {
	static boolean readDataFromFile (String filename, ArrayList<Item> list) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while(true) {
				String inp = br.readLine();

				if(inp == null) break;
				
				inp = inp.toLowerCase();
				
				String[] spl = inp.split(" ");
				
				for(int i = 0; i < spl.length; i++) {
					String now = spl[i];
					
					boolean found = false;
					for(int j = 0; j < list.size(); j++)
					{
						if(now.equals(list.get(j).getName())) {
							found = true;
							list.get(j).addTime();
						}
					}
					
					if(found == false)
						list.add(new Item(now, 1));
				}
			}
		
			br.close();
			return true;
		}
		catch(IOException e) {
			return false;
		}
	}
}

public class Problem15 {
	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		if(rv == false) {
			System.out.println("Input file not found.");
			return;
		}
		
		for(Item it: list) System.out.println(it);
	}
}