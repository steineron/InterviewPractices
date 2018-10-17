package graphs;

import graphs.Graph.EdgeNode;
import static graphs.GraphTraversal.Progress;

public class DFS extends GraphTraversal{


  public static DFS over(Graph g){
    return new DFS().init(g);
  }


  public int [] parent;
  public int [] entry;
  public int [] exit;

  int time;

  boolean execute=true; // allow early termination eg. when looking for a cycle (and finding one)


  @Override
  public DFS init(Graph g){
    super.init(g);

    entry = new int[g.vertices];
    exit = new int[g.vertices];
    time=0;
    return this;
  }

  @Override
  public void execute( int s){
    dfsVisit(s);
  }

  protected final void dfsVisit(int u){
    if(!execute){
      return;
    }

    progress[u] = Progress.DISCOVERED;
    processVertexEarly(u);

    entry[u] = time++;
    for(EdgeNode e : g.adjList[u]){


      if(progress[e.v] == Progress.UNDISCOVERED){
        parent[e.v]=u;
        processEdge(u,e.v);
        dfsVisit(e.v);
      }
      else if(progress[e.v]!=Progress.PROCESSED || g.isDirected){
        processEdge(u,e.v);
      }

      processVertexLate(u);

    }
    exit[u] = ++time;
    progress[u] = Progress.PROCESSED;

  }
}
