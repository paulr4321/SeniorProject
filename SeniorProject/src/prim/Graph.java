package prim;

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
	public static Graph initialize(int e, int from, int container, Graph rug){
		Random random = new Random();
		int count = 0, to;
		boolean clean;
		int nodeTracker[]= new int[10000];
		container = e/1000;
		//System.out.println("test2");
		rug.setEdge(Math.abs(random.nextInt(container + 1 - 1) + 1), from);
		rug.setEdge(Math.abs(random.nextInt(container + 1 - 1) + 1), from+1);
		for(int j = 0; j <= (e/1000)-2; j++){
			to = Math.abs(random.nextInt(container + 1 - 1) + 1);
			//System.out.println(to);
			from = j+2;
			nodeTracker[count]= to;
			count++;
			System.out.println("initialize");
			clean = checkNode(nodeTracker, to, rug, from, container);
			if(clean == true){
				to = Math.abs(random.nextInt(container + 1 - 1) + 1);
				System.out.println("test3");
			}


			//System.out.println(rug.adjacencyList.size());

		}

		return rug;

	}
	public static Graph EdgeHandler(int e, int from, int container, Graph rug){
		System.out.println("testJ");
		Random random = new Random();
		int count = 0, to;
		boolean clean;
		int nodeTracker[]= new int[10000];
		for(int j = 0; j <= e/1000; j++){
			to = Math.abs(random.nextInt(container + 1 - 1) + 1);
			nodeTracker[count]= from;
			count++;
			clean = checkNode(nodeTracker, to, rug, from, container);
			System.out.println("fozzywozzy");
			while(clean == true){
				to = Math.abs(random.nextInt(container + 1 - 1) + 1);
				clean = checkNode(nodeTracker, to, rug, from, container);
			}


			System.out.println(rug.adjacencyList.size());
			if (from < 1000){
				rug.setEdge(to, from);
				count++;
				container++;
			
			}
		}
		return rug;
	}
	public static boolean checkNode(int nodeTracker[], int to, Graph rug, int from, int container){
		boolean boolReturn = false;
		boolean control = false;
		boolean run = false;
		//System.out.println("testK");
		if (to < 1000 && from < 1000){
			List<Integer> edgeList = rug.getEdge(to);
			for (int i = 0; i < edgeList.size(); i++){
//				if (from > 1000 || to > 1000){
//					break;
//					
//				}
				//System.out.println("testT");
				while(control == false && run == false){
					edgeList = rug.getEdge(to);
					
					for (int j = 0;j < edgeList.size(); j++) {
						
						if(edgeList.get(j)==from){
							System.out.println("test");
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

		for (int i = 1; i <= v; i++) 
		{
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
	public static void main(String args[]) 
	{
		System.out.println("Enter the number of edges: ");

		Scanner sc = new Scanner(System.in);
		int e = sc.nextInt();
		try 
		{
			int minV = (int) Math.ceil((1 + Math.sqrt(1 + 8 * e)) / 2);
			int maxV = e + 1;
			int v = 1000;
			System.out.println("Random graph has "+v+" vertices");

			Graph rug = new Graph(v);

			//int container = 1;
			int check =0;
			int count = 0, to, from;
			Random random = new Random();
			int nodeTracker[] = new int[v];
			boolean clean;
			//from = Math.abs(random.nextInt(container + 1 - 1) + 1);
			System.out.println("test");
			int container = e/1000;
			from = 1;
			rug = initialize(e, from, container, rug);
			from = (e/1000)+1;
			
			while (count < v) 
			{
				
				System.out.println("test");
				from = container;
				rug = EdgeHandler(e, from, container, rug);
				count++;
				container++;
			}
			System.out.println("test");
			printGraph(rug,v);

			//            System.out
			//                    .println("The Adjacency List Representation of the graph is: ");
			//
			//            for (int i = 1; i <= v; i++) 
			//            {
			//                System.out.print(i + " -> ");
			//                List<Integer> edgeList = rug.getEdge(i);
			//                if (edgeList.size() == 0)
			//                    System.out.print("null");
			//                else 
			//                {
			//                    for (int j = 1;; j++) 
			//                    {
			//                        if (j != edgeList.size())
			//                            System.out.print(edgeList.get(j - 1) + " -> ");
			//                        else {
			//                            System.out.print(edgeList.get(j - 1));
			//                            break;
			//                        }
			//                    }
			//                }
			//                System.out.println();
			//            }
		} 
		catch (Exception E) 
		{
			System.out.println("Something went wrong");
		}
		sc.close();
	}
}
