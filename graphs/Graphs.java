package graphs;

import static graphs.GraphTraversal.Progress;

public final class Graphs{
  private Graphs(){}

  public static enum VertexColour{
    UNCOLOURED,
    BLACK,
    WHITE
  }

  public static int connectedComponents(Graph g){
    int c=0;
    BFS bfs = new BFS();
    bfs.init(g);

    for (int v=0; v < g.vertices; v++){
      if(!bfs.visited(v)){
        c++;
        bfs.excute(v);
      }
    }
    return c;
  }

  public static boolean bipartite(Graph g, final VertexColour[] coloring){

    boolean isBipartite = true;
    BFS bfs = new BFS(){

      @Override
      public void init(Graph g){
        super.init(g);
        for(int v=0; v<g.vertices; v++){
          coloring[v] = UNCOLOURED;
        }
      }

      @Override
      public void processEdge(int u, int v, double weight){
        if(coloring[u]==coloring[v]){
          isBipartite = false;
        }
      }

      @Override
      public void processVertexLate(int v){
        if(coloring[v]==UNCOLOURED){
          coloring[v] = coloring[parent[v]]==BLACK ? WHITE : BLACK;
        }
      }
    };

    bfs.init(g);

    for(int v=0; v<g.vertices; v++){
      if(!bfs.visited(v)){
        coloring[v] = BLACK;
        bfs.excute(v);
      }
    }
    return isBipartite;
  }
}
