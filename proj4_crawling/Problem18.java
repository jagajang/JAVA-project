package midterm2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*---read---*/
// no same name in one package
// temporally changed name to FruitMap2 & MapManager2
// before submit change name to FruitMap & MapManager
/*----------*/

// TreeMap cannot be sorted by value
// just use HashMap and sort when changed to String
class FruitMap<K, V> extends HashMap<K, V> {
	public String toString() {

		// copy data in HashMap to ArrayList
		List<Entry<K, V>> mapList = new ArrayList<Entry<K, V>>(this.entrySet());
				
		// sort mapList
		Collections.sort(mapList,
			// sort using Comparator (low price -> fast alphabet)
			new Comparator<Entry<K, V>> () {
				public int compare(Entry<K, V> o1, Entry<K, V> o2) {
					int comp = ((Double)o1.getValue()).compareTo((Double)o2.getValue());
				
					if (comp != 0)
						return comp;
					else
						return ((String)o1.getKey()).compareTo((String)o2.getKey());
				}
			}
		);
		
		String ret = new String();
		
		for(Entry<K, V> str : mapList) {	
			ret = ret.concat(str.getKey() + " " + str.getValue() + "\n");
		}
		return ret;
	}
}

class MapManager {
	static Map<String, Double> readData(String filename) {
		
		Map<String, Double> ret = new FruitMap<> ();
		
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

public class Problem18 {
	public static void main(String args[]) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if(map == null) {
			System.out.println("Input file not found.");
			return;
		}
		System.out.println(map);
	}
}