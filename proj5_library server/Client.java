package javafinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	static String userID;
	
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;
		
		ClientSender(Socket s, String n) {
			this.socket = s;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = n;
			}
			catch(Exception e) {}
		}
		
		public void run() {
			Scanner sc = new Scanner(System.in);
						
			try {
				if(out != null)
					out.writeUTF(name);
				
				while(out != null) {
					System.out.print(name + ">> ");
					
					String order = sc.nextLine();
					
					if(order.equals("add")) {
						System.out.print("add-title> ");
						String title =  sc.nextLine();
						if(title.equals("")) continue;
						
						System.out.print("add-author> ");
						String author = sc.nextLine();
						if(author.equals("")) continue;
						
						out.writeUTF("add\t" + title + "\t" + author);
						
						sleep(10);
					}
					
					else if(order.equals("borrow")) {
						System.out.print("borrow-title> ");
						String title = sc.nextLine();
						if(title.equals("")) continue;
						
						out.writeUTF("borrow\t" + title + "\t" + name);
						
						sleep(10);
					}
					
					else if(order.equals("return")) {
						System.out.print("return-title> ");
						String title = sc.nextLine();
						if(title.equals("")) continue;

						out.writeUTF("return\t" + title + "\t" + name);
						
						sleep(10);
					}
					
					else if(order.equals("info")) {
						out.writeUTF("info\t" + name);
						
						sleep(10);
					}
					
					else if(order.equals("search")) {
						String keyword = "";
						while(true) {
							System.out.print("search-string>> ");
							keyword = sc.nextLine();
						
							if(keyword.length() > 2) {
								out.writeUTF("search\t" + keyword);
								sleep(10);
								break;
							}
							
							System.out.println("Search string must be longer than 2 characters.");
						}
					}
					
					else {
						System.out.println("[available commands]");
						System.out.println("add: add a new book to the list of books.");
						System.out.println("borrow: borrow a book from the library.");
						System.out.println("return: return a book to the library.");
						System.out.println("info: show list of books I am currently borrowingy.");
						System.out.println("search: search for books.");
					}
				}
			}
			catch(Exception e) {}
			finally {
				sc.close();
			}
		}
	}
	
	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		ClientReceiver(Socket s) {
			this.socket = s;
			try {
				in = new DataInputStream(socket.getInputStream());
			}
			catch(Exception e) {}
		}
		
		public void run() {
			try {
				while(in != null) {
					System.out.println(in.readUTF());
				}
				
				sleep(10);
			}
			catch(Exception e) {}

			
		}
	}
	
	public static void main(String[] args) {
		if(args.length != 2) {
			for(int i = 0; i < args.length; i++)
				System.out.println(args[i]);
			System.out.println("Please give the IP address and port number as arguments.");
			System.exit(0);
		}
		
		try {
			String serverIP = args[0];
			Integer port = Integer.parseInt(args[1]);
			Socket socket = new Socket(serverIP, port);
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.print("Enter userID>> ");
				
				userID = sc.nextLine();
				
				boolean goodID = true;
				for(int i = 0 ; i < userID.length(); i++) {
					if(userID.charAt(i) >= 'a' && userID.charAt(i) <= 'z')
						continue;
					if(userID.charAt(i) >= '0' && userID.charAt(i) <= '9')
						continue;
					
					goodID = false;
					System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
					break;
				}
				
				if(goodID) {
					System.out.println("Hello " + userID + "!");
					break;
				}
			}
			
			Thread sender = new Thread(new ClientSender(socket, userID));
			Thread receiver = new Thread(new ClientReceiver(socket));
			
			sender.start();
			receiver.start();
		}
		catch(ConnectException ce) {
			System.out.println("Connection establishment failed.");
		}
		catch(Exception e) {}
	}
}