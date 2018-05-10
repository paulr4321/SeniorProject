package prim;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Graph 
{
	public Map<Integer, List<Integer>> adjacencyList;

	public Graph(int v) 
	{
		adjacencyList = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i <= v; i++)
			adjacencyList.put(i, new LinkedList<Integer>());
	}

	public void setEdge(int to, int from) 
	{
		if (to > adjacencyList.size() || from > adjacencyList.size())
			System.out.println("The vertices does not exists");

		List<Integer> sls = adjacencyList.get(to);
		sls.add(from);
		List<Integer> dls = adjacencyList.get(from);
		dls.add(to);
	}

	public List<Integer> getEdge(int to) 
	{
		if (to > adjacencyList.size()) 
		{
			System.out.println("The vertices does not exists");
			return null;
		}
		return adjacencyList.get(to);
	}
	public static Graph initialize(int e, int from, int container, Graph rug, int v){
		Random random = new Random();
		int count = 0, to;
		boolean clean;
		int nodeTracker[]= new int[10000];
		container = e/1000;
		rug.setEdge(Math.abs(random.nextInt(container + 1 - 1) + 1), from);
		rug.setEdge(Math.abs(random.nextInt(container + 1 - 1) + 1), from+1);
		for(int j = 0; j <= (e/1000)-2; j++){
			to = Math.abs(random.nextInt(container + 1 - 1) + 1);
			from = j+2;
			nodeTracker[count]= to;
			count++;
			clean = checkNode(nodeTracker, to, rug, from, container, v);
			if(clean == true){
				to = Math.abs(random.nextInt(container + 1 - 1) + 1);
			}


			System.out.println(rug.adjacencyList.size());

		}

		return rug;

	}
	public static Graph EdgeHandler(int e, int from, int container, Graph rug, int v){
		
		Random random = new Random();
		int count = 0, to;
		boolean clean;
		int nodeTracker[]= new int[10000];
		for(int j = 0; j <= e/1000; j++){
			to = Math.abs(random.nextInt(container + 1 - 1) + 1);
			nodeTracker[count]= from;
			count++;
			clean = checkNode(nodeTracker, to, rug, from, container, v);
			
			while(clean == true){
				to = Math.abs(random.nextInt(container + 1 - 1) + 1);
				clean = checkNode(nodeTracker, to, rug, from, container, v);
			}


			
			if (from < v){
				rug.setEdge(to, from);
				count++;
				container++;
			
			}
		}
		return rug;
	}
	public static boolean checkNode(int nodeTracker[], int to, Graph rug, int from, int container, int v){
		boolean boolReturn = false;
		boolean control = false;
		boolean run = false;
		
		if (to < v && from < v){
			List<Integer> edgeList = rug.getEdge(to);
			for (int i = 0; i < edgeList.size(); i++){

				while(control == false && run == false){
					edgeList = rug.getEdge(to);
					
					for (int j = 0;j < edgeList.size(); j++) {
						
						if(edgeList.get(j)==from){
							
							boolReturn = true;
							control = true;
							}
						else{
							if(j==edgeList.size()-1){
								control =true;
						}
						
						}
					}
				}
			}
		}
		return boolReturn;
	}

	public static void printGraph(Graph rug, int v){
		System.out
		.println("The Adjacency List Representation of the graph is: ");
		Random rn = new Random();
		double answer = rn.nextInt(10) + 1;
		String weight;
		

		for (int i = 1; i <= v; i++) 
		{
			answer = rn.nextInt(10) + 1;
			weight = Double.toString(answer/10);
			System.out.print(i + " ");
			List<Integer> edgeList = rug.getEdge(i);
			if (edgeList.size() == 0)
				System.out.print("null");
			else 
			{
				for (int j = 1;; j++) 
				{
					if (j != edgeList.size())
						System.out.print(edgeList.get(j - 1)+ " ");
					else {
						System.out.print(edgeList.get(j - 1));
						
						break;
					}
				}
			}
			
			
			System.out.println();
		}

	}
	public static void main(String args[]) throws FileNotFoundException 
	{
		System.out.println("Enter the number of edges: ");
		PrintStream printStream = new PrintStream(new FileOutputStream("test.txt"));
		System.setOut(printStream);
		Scanner sc = new Scanner(System.in);
		int e = sc.nextInt();
		try 
		{
			int minV = (int) Math.ceil((1 + Math.sqrt(1 + 8 * e)) / 2);
			int maxV = e + 1;
			int v = 100000;
			System.out.println("Random graph has "+v+" vertices");

			Graph rug = new Graph(v);

			//int container = 1;
			int check =0;
			int count = 0, to, from;
			Random random = new Random();
			int nodeTracker[] = new int[v];
			boolean clean;
			int container = e/1000;
			from = 1;
			rug = initialize(e, from, container, rug, v);
			from = (e/1000)+1;
			
			while (count < v) 
			{
				from = container;
				rug = EdgeHandler(e, from, container, rug, v);
				count++;
				container++;
			}
			
			printGraph(rug,v);


		} 
		catch (Exception E) 
		{
			System.out.println("Something went wrong");
		}
		sc.close();
	}
}
