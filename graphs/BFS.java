package graphs;

import graphs.Graph.EdgeNode;
import java.util.LinkedList;
import static graphs.GraphTraversal.Progress;


public class BFS extends GraphTraversal{

  @Override
  final public void execute(int s){

    LinkedList<Integer> q = new LinkedList<>();

    q.add(s);
    progress[s] = Progress.DISCOVERED;

    while(!q.isEmpty()){
        int u = q.remove(0);

        for(EdgeNode e: g.adjList[u]){
          int v = e.v;

          processVertexEarly(v);

          if(progress[v] != Progress.PROCESSED || g.isDirected){
            processEdge(u,v);
          }

          if(progress[v] == Progress.UNDISCOVERED){
            progress[v] = Progress.DISCOVERED;
            q.add(v);
            parent[v]=u;
            processVertexLate(v);
          }

        }
        progress[u] = Progress.PROCESSED;


    }


  }

}
