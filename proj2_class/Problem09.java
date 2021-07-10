package homework;

class Point {	
	private double[] val;
	private int size;
	
	public Point(double[] inp) {
		val = inp;
		size = inp.length;
	}
	
	protected double value(int i) {
		return val[i];
	}
	
	protected int dimension() {
		return size;
	}
}

class EuclideanDistance extends Point {
	public EuclideanDistance(double[] inp) {
		super(inp);
	}
	
	static double getDist(Point p1, Point p2) {
		if(p1.dimension() != p2.dimension())
			return -1.0;
		
		double dist = 0.0;
		
		for(int i = 0; i < p1.dimension(); i++)
			dist += Math.pow(p1.value(i) - p2.value(i), 2);
		
		return Math.sqrt(dist);
	}
}

class ManhattanDistance extends Point {

	public ManhattanDistance(double[] inp) {
		super(inp);
	}
	
	static double getDist(Point p1, Point p2) {
		if(p1.dimension() != p2.dimension())
			return -1.0;
		
		double dist = 0.0;
		
		for(int i = 0; i < p1.dimension(); i++)
			dist += Math.abs(p1.value(i) - p2.value(i));
		
		return dist;
	}
}

public class Problem09 {	
	public static void main(String[] args) {
		Point p1 = new Point(new double[] {1.0, 2.0, 3.0});
		Point p2 = new Point(new double[] {4.0, 5.0, 6.0});
	
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p1, p2));
		System.out.println("Euclidean Distance: " + ManhattanDistance.getDist(p1, p2));

		Point p3 = new Point(new double[] {1.0, 2.0, 3.0});
		Point p4 = new Point(new double[] {4.0, 5.0});
	
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p3, p4));
		System.out.println("Euclidean Distance: " + ManhattanDistance.getDist(p3, p4));
	}
}
