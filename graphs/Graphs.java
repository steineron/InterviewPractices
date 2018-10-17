package graphs;

import static graphs.GraphTraversal.Progress;

public final class Graphs{
  private Graphs(){}

  public static enum VertexColour{
    UNCOLOURED,
    BLACK,
    WHITE
  }



/**
return the number of connected components ion the Graph
*/
  public static int connectedComponents(Graph g){
    int c=0;
    BFS bfs = new BFS();
    bfs.init(g);

    for (int v=0; v < g.vertices; v++){
      if(!bfs.visited(v)){
        c++;
        bfs.execute(v);
      }
    }
    return c;
  }


/**
  return true/false if the graph is bipartite and fill the coloring array
*/
  public static boolean bipartite(Graph g, final VertexColour[] coloring){

    BipartiteFinder bfs = new BipartiteFinder(coloring);
    bfs.init(g);

    for(int v=0; v<g.vertices; v++){
      if(!bfs.visited(v)){
        coloring[v] = VertexColour.BLACK;
        bfs.execute(v);
      }
    }
    return bfs.isBipartite;
  }


  /**
  find circles in the graphs
  */

  public boolean findCircle(Graph g){
    CircleFinder dfs = new CircleFinder();
    dfs.init(g);

    for (int v=0; v<g.vertices; v++){
      if(!dfs.visited(v) && !dfs.hasCircle){
        dfs.execute(v);
      }
    }
    return dfs.hasCircle;
  }



  private static class BipartiteFinder extends BFS{
    boolean isBipartite = true;

    final VertexColour[] coloring;
    
    BipartiteFinder(final VertexColour[] coloring){
      this.coloring=coloring;
    }


    @Override
    public BFS init(Graph g){
      super.init(g);
      for(int v=0; v<g.vertices; v++){
        coloring[v] = VertexColour.UNCOLOURED;
      }
      return this;
    }

    @Override
    public void processEdge(int u, int v, double weight){
      if(coloring[u]==coloring[v]){
        isBipartite = false;
      }
      coloring[v] = coloring[u]== VertexColour.BLACK ? VertexColour.WHITE : VertexColour.BLACK;
    }
  }

  private static class CircleFinder extends DFS{
    boolean hasCircle = false;

    @Override
    public void processEdge(int u, int v, double weight){
      if(parent[u]!=v){
        execute=false;
        hasCircle=true;
      }
    }
  }

}
