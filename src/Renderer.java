import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer  {
    private static ArrayList<Client> clients = new ArrayList<Client>();
    private static int clientWidth = -1;
    private static int clientHeight = -1;
    private int frameWidth = 1500;
    private int frameHeight = 1500;
    private static JFrame frame;

    public static JFrame getFrame() {
        return frame;
    }

    public void initFrame() {
        frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);

        addClientsToList(1);

        JPanel mainPanel = new JPanel();
       // mainPanel.setLayout(new GridLayout(1,clients.size()));
        //mainPanel.setSize(frameWidth,frameHeight);
        //addClientsToGUI(mainPanel);
        frame.add(clients.get(0));
        frame.setVisible(true);
    }

    private void addClientsToList(int clientNum) {
        for(int i=0;i<clientNum;i++) {
            System.out.println("adding client " + i);
            clients.add(new Client(i));
        }
    }
    private void addClientsToGUI(JPanel frame) {
        clientWidth = frameWidth/clients.size();
        clientHeight = frameHeight/clients.size();
        for(int i=0;i<clients.size();i++) {
            frame.add(clients.get(i));
        }
    }

    public static int getClientWidth() {
        return clientWidth;
    }

    public static int getClientHeight() {
        return clientHeight;
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }
}
