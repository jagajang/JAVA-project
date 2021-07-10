package homework;

interface Shape {
	abstract double area();
}

class Circle implements Shape {
	private double r;
	
	public Circle(double val) {
		r = val;
	}
	
	public double area() {
		return  Math.PI * r * r;
	}
}

class Square implements Shape {
	private double r;
	
	public Square(double val) {
		r = val;
	}
	
	public double area() {
		return r * r;
	}
}

class Rectangle implements Shape {
	private double sero, garo;
	
	public Rectangle(double y, double x) {
		sero = y;
		garo = x;
	}
	
	public double area() {
		return sero * garo;
	}
}


public class Problem08 {
	static double sumArea(Shape[] arr) {
		double ret = 0.0;
		for(int i = 0; i < 4; i++)
			ret += arr[i].area();
		return ret;
	}
	
	public static void main(String[] args) {
		Shape[] arr = {new Circle(5.0), new Square(4.0),
				new Rectangle(3.0, 4.0), new Square(5.0) };
		
		System.out.println("Total area of the shape is: " + sumArea(arr));
	}
}
