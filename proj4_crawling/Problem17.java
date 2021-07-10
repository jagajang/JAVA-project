package midterm2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;

// TreeMap sorts components by key automatically
// class FruitMap extends TreeMap with 2 variable
class FruitMap<K, V> extends TreeMap<K, V> {
	// made for System.out.println(map) in main
	// we have to override toString using new class extending TreeMap
	public String toString() {
		String ret = new String();
		
		for(K str : this.keySet()) {
			ret = ret.concat(str + " " + this.get(str) + "\n");
		}
		return ret;
	}
}

class MapManager {
	static Map<String, Double> readData(String filename) {
		// make Map to return
		Map<String, Double> ret = new FruitMap<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while(true) {
				String line = br.readLine();
				
				if(line == null)
					break;
				
				String[] parse = line.split(" ");
				
				ret.put(parse[0], Double.parseDouble(parse[1]));
			}
			
			br.close();
			return ret;
		}
		catch(IOException e) {
			return null;
		}
		
	}
}

public class Problem17 {
	public static void main(String args[]) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if(map == null) {
			System.out.println("Input file not found.");
			return;
		}
		System.out.println(map);
	}
}