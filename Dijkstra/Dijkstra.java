package Dijkstra;
import java.util.*;

import Objects.Edge;
import Objects.Graph;
import Objects.PathVertex;
import Objects.Vertex;

public class Dijkstra {

  public static ArrayList<PathVertex> dijkstra(Graph g, Vertex v) {
    ArrayList<PathVertex> paths = (ArrayList<PathVertex>) initializeSingleSource(g, v);
    PriorityQueue<PathVertex> pathsInPQ = updatePriorityQueueDistances(paths);
    while (!pathsInPQ.isEmpty()) {
      PathVertex pv = pathsInPQ.remove();
      pv.visited = true;
      ArrayList<Edge> pvEges = g.incidentEdges(pv);
      for (Edge edge : pvEges) {
        for (int i = 0; i < paths.size(); i++) {
          if (paths.get(i).getLabel().equals(edge.getVertex2().getLabel())) {
            if (relaxEdge(pv, paths.get(i), edge.getDistance())) {
              pathsInPQ = updatePriorityQueueDistances(paths);
            }
            break;
          }
        }
      }
    }
    return paths;
  }

  public static ArrayList<PathVertex> initializeSingleSource(Graph g, Vertex v) {
    ArrayList<PathVertex> output = new ArrayList<>();
    Iterator<Vertex> iterator = g.getVertices().values().iterator();
    while (iterator.hasNext()){
      PathVertex currentPathVertex = new PathVertex(iterator.next());
      if (currentPathVertex.getLabel().equals(v.getLabel())) 
        currentPathVertex.distance = 0;
      output.add(currentPathVertex);
    }
    return output;
  }

  private static PriorityQueue<PathVertex> updatePriorityQueueDistances(List<PathVertex> paths) {
    PriorityQueue<PathVertex> pq = new PriorityQueue<>(new SortByDistance());
    for (int i = 0; i < paths.size(); i++) {
      PathVertex currentPV = paths.get(i);
      if (currentPV.visited) {
        continue;
      }
      pq.add(currentPV);
    }
    return pq;
  }

  public static boolean relaxEdge(PathVertex v, PathVertex w, int weight) {
    if (
      !v.distance.equals(Integer.MAX_VALUE) && v.distance + weight < w.distance
    ) {
      w.distance = v.distance + weight;
      w.parent = v;
      return true;
    }
    return false;
  }
}

class SortByDistance implements Comparator<PathVertex> {

  public int compare(PathVertex v, PathVertex w) {
    return v.distance - w.distance;
  }
}
