package graphs;


import java.util.LinkedList;
import java.util.List;

public class Graph{



  public static class EdgeNode {
    public final int v;
    public final double weight;

    EdgeNode (int v, double weight){
      this.v=v;
      this.weight=weight;
    }

    EdgeNode (int v){
      this(v,0.0);
    }
  }

  final int vertices;

  final boolean isDirected;

  final List<EdgeNode> [] adjList;

  private Graph(int n){
    this(n,false);
  }

  private Graph(int n, boolean directed){

    vertices = n;
    adjList = new List[n];

    for (int i=0; i<n; i++){
      adjList[i] = new LinkedList<EdgeNode>();
    }

    isDirected = directed;
  }

  private static Graph newInstance(int n, int[][] edges, boolean directed){
    Graph instance = new Graph(n,directed);
    for (int i=0; i<edges.length; i++){
      int[] edge = edges[i];

      instance.adjList[edge[0]].add(new EdgeNode(edge[1]));
      if(!directed){
        instance.adjList[edge[1]].add(new EdgeNode(edge[0]));

      }
    }
    return instance;
  }

  public static Graph undirected(int n, int[][] edges){
    return newInstance(n,edges,false);
  }

  public static Graph directed(int n, int[][] edges){
    return newInstance(n,edges,true);
  }
}
