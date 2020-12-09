import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//
//
//                   THE TEST
//
//
//


public class Graph implements GraphInterface<Town, Road> {
	boolean disjointGraph = false;
	public ArrayList<Town> dijkstra_vertexList;
	public ArrayList<Integer> dijkstra_shortestDistanceFromSource;
	public ArrayList<Town> dijkstra_previousVertexList;
	
	
	
	//the amount of rows and columns in the graph
	//increases by 1 every time a vertex is added
	//decreases by 1 every time a vertex is added
	int adjMatrixLength = -1;
	//the adjacency matrix
	ArrayList<ArrayList<Road>> adjMatrix;
	//a ArrayList of vertices acting as the labeling for the adjacency matrix
	public ArrayList<Town> vertexList;
	
	public Graph(){
		adjMatrix = new ArrayList<ArrayList<Road>>();
		vertexList = new ArrayList<Town>();
	}
	
	//returns the index of the town given in the vertex list
	//returns -1 if the town does not exist in the vertexList
	public int vertexIndex(Town vertex, ArrayList<Town> a) {
		return a.indexOf(vertex);
	}
	
	public ArrayList<Town> getVertexList() {
		return vertexList;
	}

	@Override
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		int sourceIndex = vertexIndex(sourceVertex, vertexList);
		int destinationIndex = vertexIndex(destinationVertex, vertexList);
		
		if(adjMatrix.get(sourceIndex).get(destinationIndex)!=null) {
			//source is the column and destination is the row
			return adjMatrix.get(sourceIndex).get(destinationIndex);
		}
		else  if(adjMatrix.get(destinationIndex).get(sourceIndex)!=null) {
			//source is the column and destination is the row
			return adjMatrix.get(destinationIndex).get(sourceIndex);
		}
		else {
			System.out.println("edge null");
			return null;
		}
	}

	@Override
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		int sourceIndex = vertexIndex(sourceVertex, vertexList);
		int destinationIndex = vertexIndex(destinationVertex, vertexList);
		Road a = new Road(sourceVertex, destinationVertex, weight, description);
		
		
		//if the matrix has a road in going from a to b
		//or
		//b to a
		//return null as a road already exists
		//else create a road and return it 
//		if()
		if(adjMatrix.get(sourceIndex).get(destinationIndex)!=null||
				adjMatrix.get(destinationIndex).get(sourceIndex)!=null) {
			return null;
		}
		else {
			adjMatrix.get(sourceIndex).set(destinationIndex, a);
			return a;
		}
		
	}

	@Override
	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) {
		//returns false if the town is already in the vertex list
		//returns true and adds the town to the vertex list
		if(containsVertex(v)) {
			return false;
		}
		vertexList.add(v);
		adjMatrixLength++;
		
		//extends the length and width of the matrix
		//initialize arraylist and fill it with null for the mat length (adds a row)
		ArrayList<Road> e = new ArrayList<Road>();
		adjMatrix.add(e);
		for(int i=0;i < adjMatrixLength+1;i++) {
			adjMatrix.get(adjMatrixLength).add(null);
		}
		
		//add a null on to every previous arraylist (adds a column)
		for(int i=0;i < adjMatrixLength;i++) {
			adjMatrix.get(i).add(null);
		}
		
		return true;
	}

	@Override
	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		int sourceIndex = vertexIndex(sourceVertex, vertexList);
		int destinationIndex = vertexIndex(destinationVertex, vertexList);
		
		//if a road exists between the 2 vertexs return true
		//else false
		if(adjMatrix.get(sourceIndex).get(destinationIndex)!=null) {
			return true;
		}
		
		
		return false;
	}

	@Override
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v) {
		return vertexList.contains(v);
	}

	@Override
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		ArrayList<Road> list = new ArrayList<Road>();
		//adds every none null road in the matrix to the arraylist
		for(int v=0;v<adjMatrixLength+1;v++) {
			for(int h=0;h<adjMatrixLength+1;h++) {
				if(adjMatrix.get(v).get(h)!=null) {
					list.add(adjMatrix.get(v).get(h));
				}
			}	
		}
		
		//converts to set and returns
		Set<Road> edgeSet = new HashSet<Road>(list);
		return edgeSet;
	}

	@Override
	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) {
		ArrayList<Road> list = new ArrayList<Road>();
		//gives the location of the edges we are looking for
		int vertexIndex = vertexIndex(vertex, vertexList);
		
		//adds all the non-null horizontal edges (source)
		for(int i=0;i<adjMatrixLength+1;i++) {
			if(adjMatrix.get(vertexIndex).get(i)!=null) {
				list.add(adjMatrix.get(vertexIndex).get(i));
			}
		}
		
		//adds all the non-null vertical edges (destination)
		for(int i=0;i<adjMatrixLength+1;i++) {
			//a repeat
			if(i!=vertexIndex&&adjMatrix.get(i).get(vertexIndex)!=null) {
				list.add(adjMatrix.get(i).get(vertexIndex));
			}
		}
		
		//converts to set and returns
		Set<Road> edgeSet = new HashSet<Road>(list);
		return edgeSet;
	}

	@Override
	 /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		int sourceIndex = vertexIndex(sourceVertex, vertexList);
		int destinationIndex = vertexIndex(destinationVertex, vertexList);
		
		Road test = new Road(sourceVertex, destinationVertex, weight, description);
		
		if(adjMatrix.get(sourceIndex).get(destinationIndex).equals(test)) {
			adjMatrix.get(sourceIndex).set(destinationIndex , null);
			adjMatrix.get(destinationIndex).set(sourceIndex , null);
			return test;
		}
		
		return null;
	}

	@Override
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	public boolean removeVertex(Town theTown) {
		
		//if the vertex does not exist return false
		if(!containsVertex(theTown)) {
			return false;
		}
		
		//removes the vertex from the vertex list
		int vertexIndex = vertexIndex(theTown, vertexList);
		vertexList.remove(vertexIndex);
		
		
		
		//turns all the horizontal edges (source) to null
		for(int i=0;i<adjMatrixLength+1;i++) {
			adjMatrix.get(vertexIndex).set(i , null);
		}
		
		//turns all the vertical edges (destination) to null
		for(int i=0;i<adjMatrixLength+1;i++) {
			adjMatrix.get(i).set(vertexIndex , null);
		}
		
		//nested loop goes over every element in the matrix
		//this transforms the matrix
		//v is vertical
		//h is horizonal
		for(int v=0;v<adjMatrixLength+1;v++) {
			for(int h=0;h<adjMatrixLength+1;h++) {
				//skip the iteration if the element is null
				if(adjMatrix.get(v).get(h)==null) {
					continue;
				}
				
				//the road with the cords (v,h)
				Town tempSource = adjMatrix.get(v).get(h).getSource();
				Town tempDestination = adjMatrix.get(v).get(h).getDestination();
				String tempName = adjMatrix.get(v).get(h).getName();
				int tempWeight = adjMatrix.get(v).get(h).getWeight();
				Road tempRoad = new Road(tempSource, tempDestination, tempWeight, tempName);
				
				//top right quadrant
				if(v<vertexIndex&&h>vertexIndex) {
					adjMatrix.get(v).set(h-1, tempRoad);
				}
				
				//bottom left quadrant
				if(v>vertexIndex&&h<vertexIndex) {
					adjMatrix.get(v-1).set(h, tempRoad);
				}
				
				//bottom right quadrant
				if(v>vertexIndex&&h>vertexIndex) {
					adjMatrix.get(v-1).set(h-1, tempRoad);
				}
				
			}
		}
		adjMatrixLength--;
		return true;
	}

	@Override
	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		Set<Town> vertexSet = new HashSet<Town>(vertexList);
		return vertexSet;
	}

	@Override
	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> emptyArray = new ArrayList<String>();
		dijkstraShortestPath(sourceVertex);
		if(disjointGraph) {
			return emptyArray;
		}
		//return list
		ArrayList<String> shortestPath = new ArrayList<String>();
		//pathfinding list
		ArrayList<Town> previousVertexList = new ArrayList<Town>();
		//starts from the destinationVertex
		Town currentVertex = destinationVertex;
		
		//while loop starts from the destinationVertex and 
		//rights the path to previousVertexList backwards till we reach the source
		while(!(currentVertex.equals(sourceVertex))) {
			//index of current vertex
			int currentIndex = vertexIndex(currentVertex, this.dijkstra_vertexList);
			//adds current vertex to path
			previousVertexList.add(currentVertex);
			//currentVertex = its own previous vertex
			currentVertex = this.dijkstra_previousVertexList.get(currentIndex);
		}
		//add sourceVertex to the end
		previousVertexList.add(sourceVertex);
		
		//placment null values
		for(int i=0; i<previousVertexList.size()-1;i++) {
			shortestPath.add(null);
		}
		
		//loop fills the return list
		int count = 0;
		for(int i=previousVertexList.size()-2; i>=0;i--) {
			//returnLine gets put into the return list
			String returnLine="";
			//town/edge names and edge wieght
			String firstTown = previousVertexList.get(i+1).toString();
			String secondTown = previousVertexList.get(i).toString();
			String edge = getEdge(previousVertexList.get(i+1),previousVertexList.get(i)).toString();
			int edgeWeight = getEdge(previousVertexList.get(i+1),previousVertexList.get(i)).getWeight();
			
			returnLine+= firstTown+" via "+edge+" to "+secondTown+" "+edgeWeight+" mi";
			
			shortestPath.set(count, returnLine);
			count++;
		}
		
//		for(int i =0;i<shortestPath.size();i++) {
//			shortestPath.get(i);
//		}
		
		
		return shortestPath;
	}
	
	@Override
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     *   https://www.youtube.com/watch?v=pVfj6mxhdMw
     */
	public void dijkstraShortestPath(Town sourceVertex) {
		//holds the place values of the vertices
		ArrayList<Town> vertexList = new ArrayList<Town>(this.vertexList);
		
		
		//holds the vertices not yet visited
		ArrayList<Town> unvisitedVertexList = new ArrayList<Town>(this.vertexList);
		
		//holds the shortest known distance from the source to every vertex
		ArrayList<Integer> shortestDistanceFromSource = new ArrayList<Integer>();
		for(int i=0;i < adjMatrixLength+1;i++) {
			shortestDistanceFromSource.add(Integer.MAX_VALUE);
		}
		
		//holds the vertex previous on to path to the destination
		//fill with nulls so we can set values without exceptions
		ArrayList<Town> previousVertexList = new ArrayList<Town>();
		for(int i=0;i < adjMatrixLength+1;i++) {
			previousVertexList.add(null);
		}
		
		//the index of the source vertex
		int sourceIndex = vertexIndex(sourceVertex, vertexList);
		//the source is 0 away from itself this is so the algo starts with the source
		shortestDistanceFromSource.set(sourceIndex, 0);
		
		//while vertices remain unvisited
		while(!unvisitedVertexList.isEmpty()) {
			
			//the vertex with the unvisited vertex with the smallest known distance
			Town currentVertex = null;
			int minimum = Integer.MAX_VALUE;
			//this for loop finds the unvisited vertex with the smallest known distance from
			//start vertex 
			for(int i=0;i < unvisitedVertexList.size();i++) {
				//goes through every unvisted vertex
				Town tempVertex = unvisitedVertexList.get(i);
				//the index of the current vertex
				int tempIndex = vertexIndex(tempVertex, vertexList);
				//the shortest currently known distance this vertex is from the source
				int tempDistance = shortestDistanceFromSource.get(tempIndex);
				if(tempDistance<minimum) {
					minimum = tempDistance;
					currentVertex = tempVertex;
					
				}
			}
			
			
			int currentIndex = vertexIndex(currentVertex, vertexList);
			if(currentIndex==-1) {
				disjointGraph=true;
				return;
			}
			//the distance the current vertex is from the source
			int currentDistance = shortestDistanceFromSource.get(currentIndex);
			
			//all the edges of the currentVertex
			ArrayList<Road> edgesOfCurrent = new ArrayList<Road>(edgesOf(currentVertex));
			
			//for each unvisited neighbour of the minVertex
			for(int i=0;i < edgesOfCurrent.size();i++) {
				Town neighbourVertex = edgesOfCurrent.get(i).otherVertex(currentVertex);
				int distanceSourceToNeighbour = currentDistance + edgesOfCurrent.get(i).getWeight();
				//the index of the neighbour vertex
				int neighbourIndex = vertexIndex(neighbourVertex, vertexList);
				
				//if the vertex connected to edge(i) is within the unvisited list 
				//and the distance from the source to the neighbour is 
				//less than what is in the shortestDistanceFromSource
				if(unvisitedVertexList.contains(neighbourVertex)&&
						(shortestDistanceFromSource.get(neighbourIndex)>distanceSourceToNeighbour)){
					//update shortest distance to the neighbour vertex
					shortestDistanceFromSource.set(neighbourIndex, distanceSourceToNeighbour);
					//update previousVertexList to the current vertex
					previousVertexList.set(neighbourIndex, currentVertex);
				}//end if
			}//end for loop
			
			//remove currentVertex from unvisited
			unvisitedVertexList.remove(currentVertex);
			
		}//end while loop
			
			
		//transfers the data to instance data structure to be used by the return method
		this.dijkstra_vertexList = vertexList;
		this.dijkstra_shortestDistanceFromSource = shortestDistanceFromSource;
		this.dijkstra_previousVertexList = previousVertexList;
			
		
		
		
		
		
	}//end dijkstraShortestPath
		
}

	
	
	
	
	
	
	
	











