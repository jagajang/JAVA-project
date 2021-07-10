package midterm;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Fruit {
	private String name;
	private double cost;
	
	public Fruit(String fname, double fcost) {
		this.name = fname;
		this.cost = fcost;
	}
		
	public String getName() {
		return this.name;
	}
	
	public double getCost() {
		return this.cost;
	}
}

class FruitBox <content extends Fruit> {
	private ArrayList<content> itemList = new ArrayList<content>();

	public void add(content addition) {
		System.out.println(addition.getName() + " " + addition.getCost());
		this.itemList.add(addition);
		return;
	}
	
	public int getNumItems() {
		return this.itemList.size();
	}
	
	public String getMaxItem() {
		double max = 0;
		content ret = null;
		
		for(int i = 0; i < this.getNumItems(); i++)
			if(max < this.itemList.get(i).getCost())
			{
				max = this.itemList.get(i).getCost();
				ret = this.itemList.get(i);
			}
		
		return ret.getName();
	}
		
	public double getMaxPrice() {
		double max = 0;
		
		for(int i = 0; i < this.getNumItems(); i++)
			if(max < this.itemList.get(i).getCost())
				max = this.itemList.get(i).getCost();
		
		return max;
	}
	
	public String getMinItem() {
		double min = 99999.9;
		content ret = null;
		
		for(int i = 0; i < this.getNumItems(); i++)
			if(min > this.itemList.get(i).getCost())
			{
				min = this.itemList.get(i).getCost();
				ret = this.itemList.get(i);
			}
		
		return ret.getName();
	}
	
	public double getMinPrice() {
		double min = 99999.9;
		
		for(int i = 0; i < this.getNumItems(); i++)
			if(min > this.itemList.get(i).getCost())
				min = this.itemList.get(i).getCost();

		return min;
	}
	
	public double getAvgPrice() {
		double tot = 0.0;
		
		for(int i = 0; i < this.getNumItems(); i++)
			tot += this.itemList.get(i).getCost();

		return tot / this.getNumItems();
	}
}

class ItemReader extends FruitBox<Fruit> {
	static boolean fileToBox(String filename, FruitBox<Fruit> box) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while(true) {
				String inp = br.readLine();

				if(inp == null) break;
				
				String[] spl = inp.split(" ");
				
				Fruit addition = new Fruit(spl[0], Double.valueOf(spl[1]));
				box.add(addition);
			}
		
			br.close();
			return true;
		}
		catch(IOException e) {
			System.out.println("Input file not found.");
			return false;
		}
	}
}

public class Problem14 {
	public static void main(String[] args) {
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box);
		if(rv == false) return;
		box.add(new Fruit("orange", 9.99));
		System.out.println("----------------");
		System.out.println("    Summary");
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems());
		System.out.println("most expensive item: " + box.getMaxItem() + " (" + 
		box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" +
		box.getMinPrice() + ")");
		System.out.printf("average price of items: %.2f", box.getAvgPrice());
	}
}