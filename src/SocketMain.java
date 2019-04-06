import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketMain {
    private Socket socket;
    public BufferedReader br;
    final int TIMEOUT_DURATION = 3000;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Renderer r = new Renderer();
        r.initFrame();
        try {
            new SocketMain().listen();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void listen() throws IOException, InterruptedException {
        try {
            //should try to reconnect on disconnection, attempt every 3s
            socket = new Socket("localhost", 6969);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("Could not connect to socket");
            System.out.println(e);
            Thread.sleep(TIMEOUT_DURATION);
            listen();
            return;
        }
        sendInitializer(socket);

        while (socket.isConnected()) {

            String mymessage = br.readLine(); // readline blocks
            if (mymessage == null) {
                System.out.println("We have disconnected, attempting to reconnect..");
                Thread.sleep(TIMEOUT_DURATION);
                listen();
                return;
            }
            if(mymessage.contains("POLYGON")) {
                Parser.parsePolyString(mymessage);
            } else if(mymessage.contains("PIXELS")) {
                System.out.println("parsing pixel string");
                Parser.parsePixelString(mymessage);
            }
        }
    }

    public void sendInitializer(Socket socket) throws IOException {
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        PrintWriter pw = new PrintWriter(os);

        pw.println("RENDERER");
        pw.flush();
    }
}