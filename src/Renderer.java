import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer  {
    private static ArrayList<Client> clients = new ArrayList<Client>();
    private static int clientWidth = -1;
    private static int clientHeight = -1;
    private static int frameWidth = 1500;
    private static int frameHeight = 1500;
    private static JFrame frame;
    private static JPanel mainPanel;
    public static JFrame getFrame() {
        return frame;
    }
    public static JPanel getMainPanel() {
        return mainPanel;
    }
    public void initFrame() {
        frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,5));
        mainPanel.setSize(frameWidth,frameHeight);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public static Client getClientByPort(int port) {
        for(Client c: clients) {
            if(c.getPort() == port) {
                return c;
            }
        }
        return null;
    }
    public static void addClient(Client client) {

        clients.add(client);
        calculateFrames();
        client.setSize(clientWidth,clientHeight);
        mainPanel.add(client);

    }

    private static void calculateFrames() {
        clientWidth = frameWidth/clients.size();
        clientHeight = frameHeight/clients.size();
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
