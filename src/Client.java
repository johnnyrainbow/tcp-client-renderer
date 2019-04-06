import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Client extends JPanel {
    private int[] polyX = null;
    private int[] polyY = null;
    private ArrayList<int[]> polyListX;
    private ArrayList<int[]> polyListY;
    private Graphics graphics;
    private int port;
    private BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPolyListX(ArrayList<int[]> polyListX) {
        this.polyListX = polyListX;
    }

    public void setPolyListY(ArrayList<int[]> polyListY) {
        this.polyListY = polyListY;
    }

    public int getPort() {
        return port;
    }

    public Client(int port) {
        this.port = port;
      this.setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, Color.black));
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    public void setPolyX(int[] polyX) {
        this.polyX = polyX;
    }

    public void setPolyY(int[] polyY) {
        this.polyY = polyY;
    }

    public int[] getPolyX() {
        return polyX;
    }

    public int[] getPolyY() {
        return polyY;
    }

    public void paintPoints(Graphics g, int[] polyX, int[] polyY) {

        System.out.println(polyX.length + ":" + polyY.length);
        for(int i=0;i<polyX.length-1;i++) {
           //System.out.println(polyX[i] + polyY[i]);

            if(polyX.length != polyY.length) continue;
            if((polyX[i+1] == 0 ) && (polyY[i+1] == 0)) continue;
            g.drawLine(polyX[i], polyY[i], polyX[i+1], polyY[i+1]);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        graphics = g;
        super.paintComponent(g);
//        if(polyListX == null || polyListY == null) return;
//        for(int i=0;i<polyListX.size();i++) {
//            paintPoints(g, polyListX.get(i), polyListY.get(i));
//        }
        g.drawImage(image,0,0,this);
    }
}
