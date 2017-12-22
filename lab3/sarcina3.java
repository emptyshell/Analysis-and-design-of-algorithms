import java.util.*;
import java.lang.*;

class Graph {
	static int noEdges, noConx;
	static int[][] adiacentMatrix, weightMatrix, kruscalTree, adiacentWeightMatrix;
	static private ArrayList<Integer>[] list;
	static long startTime,stopTime,duration;
	static long iteratii=0;

	public static void makeList(int[][] aM) {
		ArrayList<Integer>[] lst = new ArrayList[noEdges];
		for (int i=0; i<noEdges;i++) {
			lst[i] = new ArrayList<Integer>();
		}
		for (int i=0; i<noEdges; i++) {
			for (int j=0; j<noEdges;j++) {
				if(adiacentMatrix[i][j] == 1) {
					lst[i].add(j);
					lst[j].add(i);
				}
			}
		}
		list = lst;
	}

	public static void showList() {
		for(int i=0;i<noEdges; i++) {
			System.out.printf("[%d]\t",i);
			for (int j=0;j<list[i].size();j++) {
				System.out.printf("%d\t", list[i].get(j));
			}
			System.out.printf("\n\n");
		}
	}
	//caz mediu
	public static void initGraph(int v,int n) {
		int i,j;
		int[][] init = new int[v][v];
		Random rand = new Random();
		int edgeFrom,edgeTo,stop = 0;
		while(stop != n) {
			edgeFrom = rand.nextInt(v);
			edgeTo = rand.nextInt(v);
			if (init[edgeFrom][edgeTo] == 1) continue;
			init[edgeFrom][edgeTo] = 1;
			stop++;
		}
		adiacentMatrix = init;	
	}
	//caz favorabil
	public static void initGraph(int v) {
		noConx = 1;
		int i,j=0;;
		int[][] init = new int[v][v];
		for(i = 0; i<v; i++) {
			if (i+1 < v) j=i+1;
			if (i == v-1) break;
			init[i][j] = 1;
		}
		noConx=v-1;
		adiacentMatrix = init;	
	}

	public static void showGraph() {
			for (int i = 0; i<noEdges; i++) {
				for (int j = 0; j<noEdges; j++) {
					System.out.printf(" %d",adiacentMatrix[i][j]);
				}
				System.out.printf("\n");
			}
	}

	public static void setWeightMatrix() {
		weightMatrix = new int [noConx][3];
		int k = 0;
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		for (int i = 0; i<noEdges; i++) {
			for (int j = 0; j <noEdges; j++) {
				if (adiacentMatrix[i][j] == 1) {
					weightMatrix[k][0]=i;
					weightMatrix[k][1]=j;
					weightMatrix[k][2]= rand.nextInt(10000);
					k++;
				}
			}
		}
	}

	public static void showWeightMatrix() {
		for (int i = 0; i<noConx; i++) {
			for (int j = 0; j<3; j++) {
				System.out.printf("\t%d",weightMatrix[i][j]);
			}
			System.out.println();
		}
	}

	/*public static void removeCicles() {
		int[][] tmp;
		int r = 0;
		for(int i=0; i<weightMatrix.length-r; i++) {
			if (weightMatrix[i][0] == weightMatrix[i][1]) {
				for (int j = i; j<weightMatrix.length; j++) {
					if(j+1<=weightMatrix.length-1) {
						weightMatrix[j][0] = weightMatrix[j+1][0];
						weightMatrix[j][1] = weightMatrix[j+1][1];
						weightMatrix[j][2] = weightMatrix[j+1][2];
					}
				}
				r++;
			}
		}
		System.out.printf("\nremoved %d",r);
		tmp = new int[noConx-(r)][3];
		System.arraycopy(weightMatrix,0,tmp,0,tmp.length);
		weightMatrix = tmp;
		noConx = noConx-(r);
		System.out.printf("\ntotal edges %d\n",noConx);
	}

	public static void removeMultipleEdges() {
		int[][] tmp;
		int r = 0;
		for(int i=0; i<weightMatrix.length-r; i++) {
				for (int j = i+1; j<weightMatrix.length; j++) {
					if(((weightMatrix[i][0] == weightMatrix[j][0]) && (weightMatrix[i][1] == weightMatrix[j][1])) || ((weightMatrix[i][0] == weightMatrix[j][1]) && (weightMatrix[i][1] == weightMatrix[j][0]))) {
						if (weightMatrix[i][2] > weightMatrix[j][2]) {
							weightMatrix[i][0] = weightMatrix[j][0];
							weightMatrix[i][1] = weightMatrix[j][1];
							weightMatrix[i][2] = weightMatrix[j][2];
							weightMatrix[j][0] = weightMatrix[j][1];
						}
						else if (weightMatrix[i][2] < weightMatrix[j][2]) {
							weightMatrix[j][0] = weightMatrix[i][0];
							weightMatrix[j][1] = weightMatrix[i][1];
							weightMatrix[j][2] = weightMatrix[i][2];
							weightMatrix[i][0] = weightMatrix[i][1];
						}
						else if (weightMatrix[i][2] == weightMatrix[j][2]) {
							weightMatrix[j][0] = weightMatrix[j][1];
						}
					}
				}
		}
	}

	public static void sortWeightMatrix() {
		int[][] temp = new int[noConx][3];
		int[] arr = new int[noConx]; 
		int k=0;
		for(int i=0;i<noConx; i++) {
			arr[i] = weightMatrix[i][2];
		}
		Arrays.sort(arr);
		for (int i = 0; i<noConx; i++) {
			for(int j=0; j<noConx; j++)
				if (arr[i] == weightMatrix[j][2]) {
					temp[k][0] = weightMatrix[j][0];
					temp[k][1] = weightMatrix[j][1];
					temp[k][2] = weightMatrix[j][2];
					if(k<noConx) k++;
				}
		}
		weightMatrix = temp;
	}


	public static int[] appendVertex(int[] a,int toAppend) {
		int[] tmp;
		if((a.length == 1) && (a[0] == 0)) {
			tmp = new int[1];
			tmp[0] = toAppend;
			return tmp;
		}
		tmp = new int[a.length+1];
		System.arraycopy(a,0,tmp,0,a.length);
		tmp[tmp.length-1] = toAppend;
		return tmp;
	}

	public static int[][] appendEdge(int[][] a, int[] toAppend) {
		int[][] tmp = new int[1][3];
		if ((a.length == 1) && (a[0][2] == 0)) {
			tmp = new int[1][3];
			tmp[0] = toAppend;
			return tmp;
		}
		else
		{	
			tmp = new int[a.length+1][3];
			System.arraycopy(a,0,tmp,0,a.length);
			tmp[tmp.length-1] = toAppend;
		}
		return tmp;
	}
	public static boolean visited(int[] visited,int elem) {
		for (int i=0; i<visited.length; i++) {
			if (visited[i] == elem) 
				return true;
		}
		return false;
	}

	public static boolean isCicle(int[] a,int e) {
		int c=0;
		for(int i=0; i<a.length; i++) {
			if (a[i] == e) c++;
		}
		if (c>=2) return true;
		return false;
	}

	public static void kruskal() {
		int[][] tree = new int[1][3];
		int[] visited = new int[1];
		int[] tmp = new int[1];
		int t=0;
		removeMultipleEdges();
		removeCicles();
		sortWeightMatrix();
		for (int i = 0; i<noConx; i++) {
			tmp = visited;
			tmp = appendVertex(visited,weightMatrix[i][0]);
			tmp = appendVertex(visited,weightMatrix[i][1]);
			if(((!visited(visited,weightMatrix[i][0])) && (!isCicle(tmp,weightMatrix[i][0]))) || ((!visited(visited,weightMatrix[i][1]))  && (!isCicle(tmp,weightMatrix[i][1])))) {
				tree = appendEdge(tree,weightMatrix[i]);
				if (!visited(visited,weightMatrix[i][0])) {
					visited = appendVertex(visited,weightMatrix[i][0]);
				}
				if (!visited(visited,weightMatrix[i][1])) {
					visited = appendVertex(visited,weightMatrix[i][1]);
				}
			}				
		}
		System.out.printf("\nKruskal:\n");
		for (int i = 0; i<tree.length; i++) {
			for (int j = 0; j<3; j++) {
				System.out.printf("\t%d",tree[i][j]);
			}
			System.out.println();
		}
		
	}
	*/
	int findWeight(int u,int v) {
		for (int i=0; i<noConx;i++) {
			if ((weightMatrix[i][0] == u)&&(weightMatrix[i][1] == v) || (weightMatrix[i][1] == u)&&(weightMatrix[i][0] == v)) {
				return weightMatrix[i][2];
			}
		}
		return 0;
	}

	void setAdiacentWeightM (int[][] aM,int[][] wM) {
		adiacentWeightMatrix = new int[noEdges][noEdges];
		for (int i=0; i<noEdges;i++) {
			for(int j=0; j<noEdges;j++) {
				if (adiacentMatrix[i][j] == 1) {
					adiacentWeightMatrix[i][j] = findWeight(i,j);
					adiacentWeightMatrix[j][i] = findWeight(i,j);
				}
			}
		}
	}

	void printMST(int parent[], int n, int graph[][])
    {
        System.out.println("Edge   Weight");
        for (int i = 1; i < noEdges; i++)
            System.out.println(parent[i]+" - "+ i+"    "+
                               graph[i][parent[i]]);
    }

	public static int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < noEdges; v++) {
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

	void primMST(int graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[noEdges];
 
        // Key values used to pick minimum weight edge in cut
        int key[] = new int [noEdges];
 
        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[noEdges];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < noEdges; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        for (int x = 0; x<noEdges; x++) {
        	if(!list[x].isEmpty()) {
        		key[x] = 0;
        		break;
        	}
        }     // Make key 0 so that this vertex is
                        // picked as first vertex
        parent[0] = -1; // First node is always root of MST
 
        // The MST will have V vertices
        for (int count = 0; count < noEdges-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);
 			if(u == -1) break;
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
 
            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < noEdges; v++)
 
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 		
        // print the constructed MST
        //printMST(parent, noEdges, graph);
    }

    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;
 
        // Comparator function used for sorting edges based on
        // their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };
 
    // A class to represent a subset for union-find
    class subset
    {
        int parent, rank;
    };
 
    int V=noEdges, E=noConx;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges
 
    // A utility function to find set of an element i
    // (uses path compression technique)
    int find(subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
 
    // A function that does union of two sets of x and y
    // (uses union by rank)
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
 
        // If ranks are same, then make one as root and increment
        // its rank by one
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
 
    // The main function to construct MST using Kruskal's algorithm
    void KruskalMST()
    {
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        for (i=0; i<V; ++i)
            result[i] = new Edge();
 
        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Arrays.sort(edge);
 
        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();
 
        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
 
        i = 0;  // Index used to pick next edge
 
        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment the index
            // for next iteration
            Edge next_edge = new Edge();
            next_edge = edge[i++];
            if(i>=V) break;
 
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
 
            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }
 
        // print the contents of result[] to display the built MST
        /*System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src+" -- "+result[i].dest+" == "+
                               result[i].weight);
    	*/
    }

	Graph (int v,int n) {
		noEdges=v;
		noConx = n;
		initGraph(v,n);
		setWeightMatrix();
		makeList(adiacentMatrix);
		setAdiacentWeightM(adiacentMatrix,weightMatrix);
		V = noEdges;
        E = noConx;
        edge = new Edge[E];
        for (int i=0; i<E; i++){
            edge[i] = new Edge();
        	edge[i].src = weightMatrix[i][0];
        	edge[i].dest = weightMatrix[i][1];
        	edge[i].weight = weightMatrix[i][2];
        }
		System.out.println("initialized!");

	}

	Graph (int v) {
		noEdges=v;
		noConx=v;
		initGraph(v);
		setWeightMatrix();
		makeList(adiacentMatrix);
		setAdiacentWeightM (adiacentMatrix,weightMatrix);
		V = noEdges;
        E = noConx;
        edge = new Edge[E];
        for (int i=0; i<E; i++){
            edge[i] = new Edge();
        	edge[i].src = weightMatrix[i][0];
        	edge[i].dest = weightMatrix[i][1];
        	edge[i].weight = weightMatrix[i][2];
        }
		System.out.println("initialized!");

	}

	public static void main(String[] args) {
		int n,v;
		Scanner input = new Scanner(System.in);
		System.out.printf("Number of edges: ");
		n = input.nextInt();
		System.out.printf("Number of conexions: ");
		v = input.nextInt();
		Graph g = new Graph(n,v);
		g.showList();
		System.out.printf("PRIM: \n");
		startTime = System.nanoTime();
		g.primMST(g.adiacentWeightMatrix);
		stopTime = System.nanoTime();
		duration = stopTime - startTime;
		System.out.printf("Time: %d nS\n",duration);
		startTime = System.nanoTime();
		System.out.printf("Kruskal: \n");
		g.KruskalMST();
		stopTime = System.nanoTime();
		duration = stopTime - startTime;
		System.out.printf("Time: %d nS\n",duration);
	}


}