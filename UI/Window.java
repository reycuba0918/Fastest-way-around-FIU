package UI;


import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import Objects.Edge;
import Objects.Graph;
import Objects.PathVertex;
import Objects.Vertex;

public class Window extends JFrame {

  private final int windowSizeDesplacemnet = 200;
  private JLabel backGround;
  private double scale;
  Graph graph;
  Vertex startVertex;
  Vertex endVertex;
  ArrayList<PathVertex> distances;
  Stack<Edge> path;

  Color vertexUnclickColor;
  Color edgesColor;
  int edgeStroke;
  Color pathColor;
  int pathStroke;

  public Window(BufferedImage icon, BufferedImage backgroundImage, File verteciesFile, File edgesFile,Color vertexUnclickColor, Color edgesColor, int edgeStroke, Color pathColor,int pathStroke) {

    this.setIconImage(icon);
    this.vertexUnclickColor = vertexUnclickColor;
    this.edgesColor = edgesColor;
    this.edgeStroke = edgeStroke;
    this.pathColor = pathColor;
    this.pathStroke = pathStroke;

    this.setSize(
        Toolkit.getDefaultToolkit().getScreenSize().width -
        windowSizeDesplacemnet,
        Toolkit.getDefaultToolkit().getScreenSize().height -
        windowSizeDesplacemnet
      );
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    graph = new Graph(new HashMap<>(), new ArrayList<>());
    path = new Stack<>();

    setBackgroundImage(backgroundImage);
    plotAllVertices(verteciesFile);
    plotAllEdges(edgesFile);

  }

  private void setBackgroundImage(BufferedImage image) {
    int shortestSide;
    int scalingSide;
    if (this.getWidth() < this.getHeight()) {
      shortestSide = this.getWidth();
      scalingSide = image.getWidth();
    } else {
      shortestSide = this.getHeight();
      scalingSide = image.getHeight();
    }

    scale = ((double) shortestSide / scalingSide);

    int backgroundWidth = (int) (image.getWidth() * scale);
    int backgroundHeight = (int) (image.getHeight() * scale);

    backGround = new JLabel(
      new ImageIcon(
        image.getScaledInstance(
          backgroundWidth,
          backgroundHeight,
          Image.SCALE_SMOOTH
        )
      )
    );
    backGround.setSize(backgroundWidth, backgroundHeight);
    this.setSize(backgroundWidth, backgroundHeight);
    this.getLayeredPane().add(backGround, Integer.valueOf(0));
  }

  private void plotAllVertices(File verticesFile) {
    Scanner scan = null;
    try {
      scan = new Scanner(verticesFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      new Exception("File not found", e);
    }

    while (scan.hasNextLine()) {
      String[] tempArray = scan.nextLine().split(", ");
      int x = (int) (Integer.parseInt(tempArray[1]) * scale);
      int y = (int) (Integer.parseInt(tempArray[2]) * scale); 
      Vertex currentVertex = new Vertex(tempArray[0],pathColor, vertexUnclickColor, x, y);
      try {
        Integer.parseInt(currentVertex.getLabel());
      } catch (Exception e) {
        currentVertex.addMouseListener(new VertexClick(currentVertex, this));
        this.getLayeredPane().add(currentVertex, Integer.valueOf(4));
      }
      graph.getVertices().put(tempArray[0], currentVertex);
    }
    scan.close();
  }

  private void plotAllEdges(File pathsFile) {
    Scanner scan = null;
    try {
      scan = new Scanner(pathsFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      new Exception("File not found", e);
    }
    while (scan.hasNextLine()) {
      String[] currentPath = scan.nextLine().split(", ");
      Edge currentEdge = new Edge(graph.getVertices().get(currentPath[0]), graph.getVertices().get(currentPath[1]), edgeStroke, edgesColor, this.getSize());
      this.getLayeredPane().add(currentEdge, Integer.valueOf(1));
      graph.getEdges().add(currentEdge);
    }
  }

  public void plotPath(PathVertex pathVertex){
    PathVertex parent = pathVertex.parent;
    while(parent != null){
      Edge currentEdge = new Edge(pathVertex, parent, pathStroke, pathColor, this.getSize());
      path.push(currentEdge);
      this.getLayeredPane().add(currentEdge, Integer.valueOf(3));
      pathVertex = pathVertex.parent;
      parent = pathVertex.parent;
    }
  }

  public void removePath(){
    while(!path.empty()){
      this.getLayeredPane().remove(path.pop());
    }
    this.getLayeredPane().repaint();
  }
}
