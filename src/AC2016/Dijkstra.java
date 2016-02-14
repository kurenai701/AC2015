package AC2016;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import AC2016.Move;
import AC2016.Pos;
import AC2016.Problem;
public class Dijkstra {
	
	/*
//
// class Vertex implements Comparable<Vertex>
// {
// public final String name;
// public Edge[] adjacencies;
// public double minDistance = Double.POSITIVE_INFINITY;
// public Vertex previous;
// public Vertex(String argName) { name = argName; }
// public String toString() { return name; }
// public int compareTo(Vertex other)
// {
// return Double.compare(minDistance, other.minDistance);
// }
// }
//
// class Edge
// {
// public final Vertex target;
// public final double weight;
// public Edge(Vertex argTarget, double argWeight)
// { target = argTarget; weight = argWeight; }
// }
public static void computePaths(Pos source)
{
	source.minDistance = 0;
	PriorityQueue<Pos> posQueue = new PriorityQueue<Pos>();
	posQueue.add(source);
	while (!posQueue.isEmpty())
	{
		Pos u = posQueue.poll();
		// Visit each edge exiting u
		for (Move m : u.moves)
		{
			Pos v = m.nextPos;
			int cost = m.cost;
			int distanceThroughU = u.minDistance + cost;
			if (distanceThroughU < v.minDistance) {
				posQueue.remove(v);
				v.minDistance = distanceThroughU ;
				v.previous = u;
				posQueue.add(v);
			}
		}
	}
}

public static void computeAllPaths(Problem pb, List posInterest)
{
	for(Pos p : pb.AllPos)
	{
			p.minDistance = Integer.MAX_VALUE;
		
	}
	
	for(Pos curP : posInterest)
	{
		computePaths(curP);
		for( nextP : posInterest)
		{
			cost[curP.numInPosInterest]nextP.numInPosInterest] = pb.
		}
	}
	
}
// public static List<Vertex> getShortestPathTo(Vertex target)
// {
// List<Vertex> path = new ArrayList<Vertex>();
// for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
// path.add(vertex);
// Collections.reverse(path);
// return path;
// }
public static fillFullPaths(Vertex target)
{
	List<Vertex> path = new ArrayList<Vertex>();
	for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
	path.add(vertex);
	Collections.reverse(path);
	return path;
}


*/
}