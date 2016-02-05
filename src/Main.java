import java.io.IOException;
import edu.princeton.cs.algs4.*;

public class Main {
	     //output routeDistance to .txt file
	     //calculate gas using %	
	
		private static CityHashTable ht = new CityHashTable();
		private static Distance distance; 
		private static Trip trip;
		
		private static String StartingCity = "Rochester";
		private static String EndCity = "New York";
		
		//fuel efficiency in Litres/100km
		private static double fuelEfficiency = 8.2;
		private static double totalDistance = 0;
		
	public static void main(String[] args) throws IOException {
	
		ht.setHt("data/zips1990.csv", "data/cities.csv");
		distance = new Distance(ht);
		trip = new Trip(distance.getGraphFile(), ht.hashFunction(StartingCity));
		
		Iterable<DirectedEdge> path = trip.getShortestRoute().pathTo(ht.hashFunction(EndCity));
				
		System.out.println("Origin of Trip: " + StartingCity);
		System.out.println("Final destinaton of Trip: " + EndCity);
		System.out.println();
	
		for(DirectedEdge e : path){
			System.out.println(ht.inverseHashFunction(e.from()) + " to " + ht.inverseHashFunction(e.to()) + "\t" + "(" + e.weight() + "km)");
			totalDistance = totalDistance + e.weight();
		}
		
		double totalGas = totalDistance/100 * fuelEfficiency;
		System.out.println();
		System.out.println("Total distance travelled on this trip: " + totalDistance + " Kilometres");
		System.out.println("Total gas used on this trip: " + totalGas + " Litres");
	}
}