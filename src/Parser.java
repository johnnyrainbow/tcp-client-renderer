import java.util.ArrayList;

public class Parser {
    public static void parsePolyString(String polyString) {
        String firstSub[] = polyString.split("POLYGON:");
        String polysub[] = firstSub[1].split("ENTITY:");
        ArrayList<int[]> polyListX = new ArrayList<>();
        ArrayList<int[]> polyListY = new ArrayList<>();

        for(int i=1;i<polysub.length;i++) {
            System.out.println("polysub is");
            System.out.println(polysub[i]);
            String xySplit[] = polysub[i].split(":");
            int xPoly[] = convertArray(xySplit[0]);
            int yPoly[] = convertArray(xySplit[1]);
            polyListX.add(xPoly);
            polyListY.add(yPoly);
        }


        Client c = Renderer.getClients().get(0);
        System.out.println("setting polys");
        c.setPolyListX(polyListX);
        c.setPolyListY(polyListY);
        Renderer.getFrame().validate();
        Renderer.getFrame().getContentPane().repaint();
    }
    public static int[] convertArray(String arrString) {
        String[] items = arrString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }
        return results;
    }
}
