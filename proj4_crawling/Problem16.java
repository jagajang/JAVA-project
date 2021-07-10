package midterm2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// implement Comparable<Element> so that Element can be compared
class Element implements Comparable<Element> {
	private String name;
	private double price;

	// constructor
	public Element (String eName, double ePrice) {
		this.name = eName;
		this.price = ePrice;
		return;
	}
	
	// return name
	public String getName() {
		return this.name;
	}
	
	// return price
	public double getPrice() {
		return this.price;
	}

	// compare function (low price -> fast alphabet)
	public int compareTo(Element e) {
		if(this.price > e.getPrice())
			return 1;
		else if(this.price < e.getPrice())
			return -1;
		else
			return this.name.compareTo(e.getName());
	}
	
	// made for System.out.println(it.next()) in main
	public String toString() {
		return name + " " + price;
	}
}

class ElementReader {
	static ArrayList<Element> readElements(String filename) {
		
		// make ArrayList to return
		ArrayList <Element> ret = new ArrayList<Element>();
		
		try {
			// create File reader
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while(true) {
				// read file by 1 line
				String line = br.readLine();
				
				if(line == null)
					break;
				
				// cut string by blank(" ")
				String[] parse = line.split(" ");
				
				// add data to ArrayList
				ret.add(new Element(parse[0], Double.parseDouble(parse[1])));
			}
			
			// close file reader
			br.close();
			return ret;
		}
		// if exception occurs, return null
		catch (IOException e)
		{
			return null;
		}
	}
}

public class Problem16 {
	public static void main(String args[]) {
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		if(list == null) {
			System.out.println("Input file not found.");
			return;
		}
		Collections.sort(list);
		Iterator<Element> it = list.iterator();
		while(it.hasNext()) System.out.println(it.next());
	}
}