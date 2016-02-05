import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class Distance {

	private List<String[]> routeDistance = new ArrayList<String[]>();
	private String graphFile = new String(); 
	
	/* Calculates distance between 2 points in Kilometers
	 * modified from http://www.geodatasource.com/developers/java */
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = Math.abs(lon1 - lon2);
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515 * 1.609344;
    
		  return (dist);
	}

	/*  This function converts decimal degrees to radians */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	/*  This function converts radians to decimal degrees */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	public Distance(CityHashTable ht)throws IOException{
		//construct hash table (key: city name, value: longitude and latitude of that city)
		
		//load pairs of connected city into a list
	    CSVReader reader = new CSVReader(new FileReader("data/dataset.csv"));
	    List<String[]> connectedCities = reader.readAll();
	    reader.close();
	    
	    //find and save the distance between connected city pairs
	    for (int i = 0; i < connectedCities.size(); i++){
	    	double[] lonlat1 = ht.lookUp(connectedCities.get(i)[0]);
	    	double[] lonlat2 = ht.lookUp(connectedCities.get(i)[1]);
	    	int city1 = ht.hashFunction(connectedCities.get(i)[0]);
	    	int city2 = ht.hashFunction(connectedCities.get(i)[1]);
	    	
	    	String[] temp = {Integer.toString(city1),Integer.toString(city2),Integer.toString((int)distance(lonlat1[0],lonlat1[1],lonlat2[0],lonlat2[1]))};
	    	routeDistance.add(temp);
	     }
	    
	    //create a new .txt file with Graph data (Vertices: Cities, Edges: connect the connecting cities, weight: distance between cities
		try {
			PrintStream output = new PrintStream(new File("data/weightedGraphData.txt"));
			output.println(ht.size());
			output.println(routeDistance.size());
		     for (int i = 0; i < routeDistance.size(); i++){
		    	 String[] temp = routeDistance.get(i);
		    	 output.print(temp[0] + " " + temp[1] + " " + temp[2]);
		    	 output.println();
		     }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setGraphFile("data/weightedGraphData.txt");
	}
	
	public void setGraphFile(String file) {
		graphFile = "data/weightedGraphData.txt";
	}
	
	public String getGraphFile(){
		return graphFile;
	}
	
	public static void main(String[] args) throws IOException {
	
		CityHashTable ht = new CityHashTable();
		
		ht.setHt("data/zips1990.csv", "data/cities.csv");
		Distance distance = new Distance(ht);
		System.out.println(distance.getGraphFile());
		
	}
	
}
	
	

