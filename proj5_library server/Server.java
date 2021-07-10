package javafinal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

class BookInfo {
	String title, author, user;
	
	public BookInfo(String t, String a, String u) {
		this.title = t;
		this.author = a;
		this.user = u;
	}
	
	public String toString() {
		return this.title + "\t" + this.author + "\t" + this.user;
	}
	
	public String info() {
		return this.title + "\t" + this.author;
	}
}

public class Server {
	
	static HashMap<String, BookInfo> bookList;
	HashMap<String, DataOutputStream> clients;
	
	Server() {
		bookList = new HashMap<>();
		clients = new HashMap<>();
		Collections.synchronizedMap(bookList);
		Collections.synchronizedMap(clients);
	}
	
	public void FileChange() {
		ArrayList<String> keys = new ArrayList<>(bookList.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		try {
			FileWriter fw = new FileWriter("books.txt");
			PrintWriter bw = new PrintWriter(fw);
			
			System.out.println("--List of Books--");
			for(String s : keys) {
				BookInfo tmp = bookList.get(s);
				System.out.println(tmp);
				bw.println(tmp);
			}
			System.out.println("-----------------");
			
			fw.close();
			bw.close();
		}
		catch(IOException e) {}
	}
	
	public void start(String args) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("books.txt");
			br = new BufferedReader(fr);
			
			String line, title;
			String[] parsed;
			while(true) {
				line = br.readLine();
				if(line == null) break;
				
				parsed = line.split("\t");
				title = parsed[0].toLowerCase();
				
				bookList.put(title, new BookInfo(parsed[0], parsed[1], parsed[2]));
			}
		}
		catch(IOException e) {}
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(Integer.parseInt(args));
			System.out.println("library server started.");
			
			while(true) {
				socket = serverSocket.accept();
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		}
		catch(Exception e) {
			System.out.println("error at server start");
		}
	}
	
	void sendMessage(DataOutputStream out, String msg) {
		try {
			out.writeUTF(msg);
		}
		catch(IOException e) {}
	}
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Please give the port number as an argument.");
			System.exit(0);
		}

		new Server().start(args[0]);
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket s) {
			this.socket = s;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}
			catch(IOException e) {}
		}
		
		public void run() {
			String name = "";
			
			try {
				name = in.readUTF();
				clients.put(name,  out);
				
				while(in != null) {
					String[] request = in.readUTF().split("\t");
					
					if(request[0].equals("add")) {

						BookInfo newBook = new BookInfo(request[1], request[2], "-");																
						
						String title = request[1].toLowerCase();
						
						if(bookList.get(title) == null) {
							sendMessage(out, "A new book added to the list.");
							bookList.put(title, newBook);
							FileChange();
						}
						else {
							bookList.put(title, newBook);
							sendMessage(out, "The book already exists in the list.");
						}
					}
					
					else if(request[0].equals("borrow")) {
						
						String title = request[1].toLowerCase();
						
						if(bookList.get(title) == null) {
							sendMessage(out, "The book is not available.");
						}
						else if(!bookList.get(title).user.equals("-")) {
							sendMessage(out, "The book is not available.");
						}
						else {
							bookList.get(title).user = request[2];
							FileChange();
							sendMessage(out, "You borrowed a book. - " + bookList.get(title).title);
						}
					}
					
					else if(request[0].equals("return")) {
						
						String title = request[1].toLowerCase();
						
						if(bookList.get(title) == null) {
							sendMessage(out, "You did not borrow the book.");
						}
						else if(!bookList.get(title).user.equals(request[2])) {
							sendMessage(out, "You did not borrow the book.");
						}
						else {
							bookList.get(title).user = "-";
							FileChange();
							sendMessage(out, "You returned a book. - " + bookList.get(title).title);
						}
					}
					
					else if(request[0].equals("info")) {
						
						String user = request[1];
						
						int num = 1;
						for(String s : new ArrayList<String>(bookList.keySet())) {
							BookInfo tmp = bookList.get(s);
							
							if(tmp.user.equals(user))
								sendMessage(out, num++ + ". " + tmp.info());
						}
					}
					
					else if(request[0].equals("search")) {
						
						String keyword = request[1];
						
						int num = 0;
						for(String s : new ArrayList<String>(bookList.keySet())) {
							BookInfo tmp = bookList.get(s);
							
							if(s.contains(keyword) || tmp.author.toLowerCase().contains(keyword))
								num++;
						}
						if(num == 0) continue;
						
						sendMessage(out, "Your search matched " + num + " results.");
						num = 1;
						for(String s : new ArrayList<String>(bookList.keySet())) {
							BookInfo tmp = bookList.get(s);
							
							if(s.contains(keyword) || tmp.author.toLowerCase().contains(keyword)) {
								sendMessage(out, num++ + ". " + tmp.info());
							}
						}
					}
				}
			}
			catch(IOException e) {}
			finally {
				clients.remove(name);
			}
		}
	}
}
