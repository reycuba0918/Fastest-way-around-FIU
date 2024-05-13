package Utilities;

//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//import javax.imageio.ImageIO;

public class Converting {

  public static void main(String[] args) throws Exception {
    //BufferedImage fullMap = ImageIO.read(new File("Data\\FullMap.jpg"));
    //BufferedImage MMC_full = ImageIO.read(new File("Data\\MMC_full.jpg"));

    //double Wscale = MMC_full.getWidth()/fullMap.getWidth();
    //double Hscale =  MMC_full.getHeight()/(double)fullMap.getHeight();
    double scale = 1;
    int X = -425;
    int Y = -250;
    Scanner scan = null;
    File Vertices = new File("Data\\Vertices_bigger.txt");
    scan = new Scanner(Vertices);

    FileWriter Vertices_bigger = new FileWriter(
      "Data\\Vertices_bigger2.txt",
      true
    );
    while (scan.hasNextLine()) {
      String[] currentLine = scan.nextLine().split(", ");
      currentLine[1] = "" +
      (int) ((Integer.parseInt(currentLine[1]) * scale) + X);
      currentLine[2] = "" +
      (int) ((Integer.parseInt(currentLine[2]) * scale) + Y);
      for (int i = 0; i < 3; i++) {
        if (i != 2) {
          Vertices_bigger.write(currentLine[i] + ", ");
        } else {
          Vertices_bigger.write(currentLine[i] + "\n");
        }
      }
    }
    Vertices_bigger.close();
    scan.close();
  }
}
