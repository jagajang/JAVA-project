package midterm2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

class BookInfo implements Comparable<BookInfo> {
	private int rank;
	private String title;
	private String author;
	private double price;
	
	public BookInfo(int rank, String title, String author, double price) {
		this.rank = rank;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		String ret = new String();
		
		ret = ret.concat("#" + this.rank + " ");
		ret = ret.concat(this.title + ", ");
		ret = ret.concat(this.author + ", ");
		ret = ret.concat("$" + this.price);
		
		return ret;
	}
	
	public int compareTo(BookInfo b) {
		if(this.rank > b.getRank())
			return -1;
		else if(this.rank < b.getRank())
			return 1;
		else
			return 0;
	}
}

class BookReader {
	static ArrayList<BookInfo> readBooks(String urlname) {
		
		// make ArrayList of Books
		ArrayList<BookInfo> ret = new ArrayList<BookInfo>();
		
		try {
			// read data from website "urlname"
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(urlname).openStream()));
			
			int rank = 1;
			String title = null;
			String author = null;
			double price;
			
			// status means which data you have read
			// 0 : read none & searching book data block
			// 1 : read none & searching title
			// 2 : read title & searching author
			// 3 : read title, author & searching price
			int status = 0;
			
			while(true) {
				String line = br.readLine();
				
				if(line == null)
					break;
				
				// searching book data block (not necessary) 
				if(status == 0) {
					if(line.contains("<h3 class=\"product-info-title\">")) {
						status = 1;
					}
				}
				// searching title
				else if(status == 1) {
					if(line.contains("<a title=\"\" class=\" \"")) {	
						int begin = line.indexOf(">") + ">".length();
						int end = line.indexOf("</a>");
												
						title = line.substring(begin, end);

						status = 2;
					}
				}
				// searching author
				else if(status == 2) {
					if(line.contains("<div class=\"product-shelf-author contributors\">")) {
						line = line.substring("<div class=\"product-shelf-author contributors\">".length(), line.length());
						
						int begin = line.indexOf("\">") + "\">".length();
						int end = line.indexOf("</a>");
						
						author = line.substring(begin, end);
						
						status = 3;
					}
				}
				// searching price
				else if(status == 3) {
					if(line.contains("<a title=\"\" class=\" current link\"")) {
						
						int begin = line.indexOf("$") + "$".length();
						int end = line.indexOf("</a>");
						
						price = Double.valueOf(line.substring(begin, end)).doubleValue();
				
						// we have found all info.
						// so save book information in ArrayList & reset status to 0
						ret.add(new BookInfo(rank, title, author, price));
						
						rank++;
						status = 0;
					}
				}
			}
		}
		catch(Exception e) { // error case. don't return null
			e.printStackTrace();
			return new ArrayList<BookInfo>();
		}
		
		return ret;
	}
}

public class Problem19 {
	public static void main(String args[]) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i = 0; i < books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
	}
}