import edu.princeton.cs.algs4.*;

public class Trip {
	
	private static EdgeWeightedDigraph tripGraph;
	private static DijkstraSP shortestRoute;
	
	public Trip(String graphDataFile, int startingCity)
	{
		In in = new In(graphDataFile);
		setTripGraph(in);
		setShortestRoute(startingCity);
	}	
	
	public static void setTripGraph(In in){
		tripGraph =  new EdgeWeightedDigraph((in));
	}
	
	public EdgeWeightedDigraph getTripGraph(){
		return tripGraph;
	}
	
	public static void setShortestRoute(int source){
		shortestRoute = new DijkstraSP(tripGraph, source);
	}
	
	public DijkstraSP getShortestRoute(){
		return shortestRoute;
	}
	
	public static void main(String[] args) {
//		setTripGraph("data/dataset.txt", ",");
//		String graph = tripGraph.G().toString(); 
//		System.out.println(graph);
	}
}
