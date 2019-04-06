import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class Parser {
    public static void parsePolyString(String polyString) {
    //System.out.println(polyString);
        if(!polyString.contains("ENTITY")) return;
        String firstSub[] = polyString.split(":POLYGON:");
        if(firstSub.length < 2) return;
        String polysub[] = firstSub[1].split("ENTITY:");
        ArrayList<int[]> polyListX = new ArrayList<>();
        ArrayList<int[]> polyListY = new ArrayList<>();

        int port = Integer.parseInt(firstSub[0]);
        for (int i = 1; i < polysub.length; i++) {
            String xySplit[] = polysub[i].split(":");
            if (xySplit.length < 2) continue;
            int xPoly[] = convertArray(xySplit[0]);
            int yPoly[] = convertArray(xySplit[1]);
            polyListX.add(xPoly);
            polyListY.add(yPoly);
        }


        Client c = Renderer.getClientByPort(port);
        if (c == null) {
            c = new Client(port);
            Renderer.addClient(c);
            System.out.println("added client");
        }

        c.setPolyListX(polyListX);
        c.setPolyListY(polyListY);
        Renderer.getFrame().validate();
        Renderer.getFrame().getContentPane().repaint();
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public static void parsePixelString(String polyString) {
        polyString = polyString.split("::EN")[0];
        System.out.println(polyString);
        String firstSub[] = polyString.split(":PIXELS:");
        int port = Integer.parseInt(firstSub[0]);
        byte[] decoded = null;

        try {
            decoded = Base64.getDecoder().decode(firstSub[1]);
        } catch(IllegalArgumentException e) {
            System.out.println("byte error: " + e);
        }
        if(decoded == null) return;
        System.out.println("bytelen:" + decoded.length);
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(decoded));
            System.out.println(port);
            Client c = Renderer.getClientByPort(port);
            if (c == null) {
                c = new Client(port);
                Renderer.addClient(c);
                System.out.println("added client");
            }
            img = resize(img,c.getWidth(),c.getWidth());
            c.setImage(img);
            Renderer.getFrame().validate();
            Renderer.getFrame().getContentPane().repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] convertArray(String arrString) {
        String[] items = arrString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            }
        }
        return results;
    }
}
