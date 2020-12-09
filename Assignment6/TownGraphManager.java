import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TownGraphManager implements TownGraphManagerInterface {
	Graph graph;
	
	
	TownGraphManager(){
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		//towns to compare to the real towns
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		//get the index of the real towns
		int sourceIndex = graph.vertexIndex(source, graph.getVertexList());
		int destinationIndex = graph.vertexIndex(destination, graph.getVertexList());
		
		//if addEdge returns null than the edge was not added
		//else it was added
		if(graph.addEdge(graph.vertexList.get(sourceIndex),
				graph.vertexList.get(destinationIndex), weight, roadName)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String getRoad(String town1, String town2) {
		//towns to compare to the real towns
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		//get the index of the real towns
		int sourceIndex = graph.vertexIndex(source, graph.getVertexList());
		int destinationIndex = graph.vertexIndex(destination, graph.getVertexList());
		
		return graph.getEdge(graph.vertexList.get(sourceIndex),
				graph.vertexList.get(destinationIndex)).toString();
	}

	@Override
	public boolean addTown(String v) {
		//town to add
		Town town = new Town(v);
		
		return graph.addVertex(town);
	}

	@Override
	public Town getTown(String name) {
		//town to compare to the real towns
		Town town = new Town(name);
		
		//get the index of the real town
		int townIndex = graph.vertexIndex(town, graph.getVertexList());
		
		return graph.vertexList.get(townIndex);
	}

	@Override
	public boolean containsTown(String v) {
		//town to compare to the real towns
		Town town = new Town(v);
		return graph.containsVertex(town);
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		//towns to compare to the real towns
		Town source = new Town(town1);
		Town destination = new Town(town2);

		return graph.containsEdge(source, destination);
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<Road> roadArray = new ArrayList<Road>(graph.edgeSet());
		ArrayList<String> returnArray = new ArrayList<String>();

		for(int i=0;i<roadArray.size();i++){
			returnArray.add(roadArray.get(i).toString());
		}
		Collections.sort(returnArray);

		return returnArray;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		//towns to compare to the real towns
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		//get the index of the real towns
		int sourceIndex = graph.vertexIndex(source, graph.getVertexList());
		int destinationIndex = graph.vertexIndex(destination, graph.getVertexList());
		
		Road a = graph.getEdge(graph.vertexList.get(sourceIndex),
				graph.vertexList.get(destinationIndex));
		
		
		if(graph.removeEdge(a.getSource(),a.getDestination(),a.getWeight(),a.getName())==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public boolean deleteTown(String v) {
		//town to compare to the real towns
		Town town = new Town(v);
		
		//get the index of the real town
		int townIndex = graph.vertexIndex(town, graph.getVertexList());
		
		
		return graph.removeVertex(graph.vertexList.get(townIndex));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<Town> townArray = new ArrayList<Town>(graph.vertexSet());
		ArrayList<String> returnArray = new ArrayList<String>();

		for(int i=0;i<townArray.size();i++){
			returnArray.add(townArray.get(i).toString());
		}
		Collections.sort(returnArray);

		return returnArray;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> emptyArray = new ArrayList<String>();
		//towns to compare to the real towns
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		//get the index of the real towns
		int sourceIndex = graph.vertexIndex(source, graph.getVertexList());
		int destinationIndex = graph.vertexIndex(destination, graph.getVertexList());
		
		if(sourceIndex==-1||destinationIndex==-1) {
			return emptyArray;
		}
		
		return graph.shortestPath(graph.vertexList.get(sourceIndex),graph.vertexList.get(destinationIndex));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(selectedFile));
		
		String st; 
		try {
			while ((st = br.readLine()) != null) { 
				//every string of chars seperated by a , or ; is an element
				String[] splitUp = st.split(";");
				String[] splitUp2 = splitUp[0].split(",");
				String roadName = splitUp2[0];
				int roadWeight = Integer.parseInt(splitUp2[1]);
				String sourceTown = splitUp[1];
				String destinationTown = splitUp[2];
				
				addTown(sourceTown);
				addTown(destinationTown);
				addRoad(sourceTown, destinationTown, roadWeight, roadName);
			}
		} catch (IOException e) {
		} 
	}

}
