import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class Client extends JPanel {
    private int[] polyX = null;
    private int[] polyY = null;
    private ArrayList<int[]> polyListX;
    private ArrayList<int[]> polyListY;
    private int identifier;
    private Graphics graphics;

    public void setPolyListX(ArrayList<int[]> polyListX) {
        this.polyListX = polyListX;
    }

    public void setPolyListY(ArrayList<int[]> polyListY) {
        this.polyListY = polyListY;
    }

    public Client(int identifier) {
        this.identifier = identifier;
      this.setBorder(BorderFactory.createMatteBorder(
                1, 0, 1, 1, Color.black));
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
        if(polyX == null || polyY == null) return;
        for(int i=0;i<polyX.length-1;i++) {

            if(polyX[i+1] == 0 && polyY[i+1] == 0) continue;
            System.out.println("Drawing point " + polyX[i] +":" +polyY[i]);
            g.drawLine(polyX[i], polyY[i], polyX[i+1], polyY[i+1]);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
    System.out.println("Calling paint");
    graphics = g;
        super.paintComponent(g);
        for(int i=0;i<polyListX.size();i++) {
            paintPoints(g, polyListX.get(i), polyListY.get(i));
        }
    }
}
