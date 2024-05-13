package UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Dijkstra.Dijkstra;
import Objects.*;

class VertexClick extends MouseAdapter {

    Vertex vertex;
    Window window;
  
    public VertexClick(Vertex vertex, Window window) {
      this.vertex = vertex;
      this.window = window;
    }
  
    @Override
    public void mouseClicked(MouseEvent e) {
      if (window.startVertex != null && vertex.getLabel().equals(window.startVertex.getLabel())) {
        window.startVertex.unclick();
        window.removePath();
        window.startVertex = null;
        return;
      } else if (window.endVertex != null && vertex.getLabel().equals(window.endVertex.getLabel())) {
        window.endVertex.unclick();
        window.removePath();
        window.endVertex = null;
        return;
      }
      if (window.startVertex == null) {
        window.startVertex = vertex;
        window.startVertex.click();
        window.distances = Dijkstra.dijkstra(window.graph, vertex);
      } else {
        if (window.endVertex != null){
          window.endVertex.unclick();
          window.removePath();
        }
        window.endVertex = vertex;
        window.endVertex.click();
      }
      if (window.startVertex != null && window.endVertex != null) {
        for(PathVertex distance: window.distances)
          if (distance.getLabel().equals(window.endVertex.getLabel()))
            window.plotPath(distance);
      }
    }
  }
