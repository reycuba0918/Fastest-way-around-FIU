import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import UI.Window;

public class Main {

  public static void main(String[] args) {

    BufferedImage icon = inportImage("Data\\FIU_logo.png");
    BufferedImage image = inportImage("Data\\MMC_cut_high_resolution.jpg");
    File verticesFile = new File("Data\\Vertices.txt");
    File pathsFile = new File("Data\\Paths_with_Reverse.txt");
    
    Window window = new Window(icon, image, verticesFile, pathsFile, Color.blue, Color.white, 3, Color.BLACK, 5);
    window.setVisible(true);
  }

  public static BufferedImage inportImage(String address){
    BufferedImage image = null;
      try {
        image = ImageIO.read(new File(address));
      } catch (IOException e) {
        e.printStackTrace();
      }
    return image;
  }
}
