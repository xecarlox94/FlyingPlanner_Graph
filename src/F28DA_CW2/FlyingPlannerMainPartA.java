package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class FlyingPlannerMainPartA {	
	
	private static void deleteDemo() {
		
		// DELETEEE SOOOOOOOOOOOOOOOOOOOOOOOOOOOON !!!!!!!!!!!!!!!!!!!!!!!!

		// The following code is from HelloJGraphT.java of the org.jgrapth.demo package
		System.err.println("The example code is from HelloJGraphT.java from the org.jgrapt.demo package.");
		System.err.println("Use similar code to build the small graph from Part A by hand.");
		System.err.println("Note that you will need to use a different graph class as your graph is not just a Simple Graph.");
		System.err.println("Once you understand how to build such graph by hand, move to Part B to build a more substantial graph.");
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        Graph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        DefaultWeightedEdge e1 = g.addEdge(v1, v2);

        e1 = g.addEdge(v2, v3);
        g.setEdgeWeight(e1, 4);

        e1 = g.addEdge(v2, v4);
        g.setEdgeWeight(e1, 6);
        
        e1 = g.addEdge(v1, v3);
        g.setEdgeWeight(e1, 1);

        e1 = g.addEdge(v3, v2);
        g.setEdgeWeight(e1, 2);

        e1 = g.addEdge(v3, v4);
        g.setEdgeWeight(e1, 3);
        

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println("-- toString output");
        // @example:toString:begin
        System.out.println(g.toString());
        // @example:toString:end
        System.out.println();
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        
//        DijkstraShortestPath<>
        
        DijkstraShortestPath<String,DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<String, DefaultWeightedEdge>(g);
        
        System.out.println(dijkstra.getPath(v1, v3));
        
	}



	public static void main(String[] args) 
	{
		deleteDemo();
		
		

        FlyingPlanner fp = new FlyingPlanner();
        
        FlightsReader fr;
        
        try {
        	fr = new FlightsReader();
            
            fp.populate(fr);
            
            
            
            
            
		} catch (FileNotFoundException | FlyingPlannerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	
}
