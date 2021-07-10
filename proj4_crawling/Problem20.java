package midterm2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
	static ArrayList<BookInfo> readBooksJsoup(String urlname) {
		// make ArrayList of Books
		ArrayList<BookInfo> ret = new ArrayList<BookInfo>();
		
		Document doc = null;
		try {
			// read data from website "urlname"
			doc = Jsoup.connect(urlname).get();
			
			Elements title1 = doc.select("h3.product-info-title");
			Elements title2 = title1.select("a[title]");
			
			Elements author1 = doc.select("div.product-shelf-author");
						
			Elements price1 = doc.select("span.current");
			
			for(int i = 0; i < title2.size(); i++) {
				
				String tmp = author1.eq(i).text();
				int en = tmp.indexOf(",");
				if(en == -1) en = tmp.length();
				
				ret.add(new BookInfo(
						i + 1,
						title2.eq(i).text(),
						tmp.substring(3, en),
						Double.parseDouble(price1.eq(i).text().substring(1))
				));
			}
		}
		catch(IOException e) { // error case. don't return null
			e.printStackTrace();
			return new ArrayList<BookInfo>();
		}
		
		return ret;
	}
}

public class Problem20 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i = 0; i < books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
	}
}