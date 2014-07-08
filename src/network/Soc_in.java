package network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Soc_in extends Observable implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;
    private byte[] buffer;

    public Soc_in() {
        int port = Integer.parseInt(Network_config.inputPort);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Soc_in.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new byte[10000];
    }

    //start the listening thread 
    @Override
    public void run() {
        listen();
    }

    private void listen() {
        while (true) {
            try {
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                inputStream.read(buffer);
                String message = (new String(buffer)).split("#")[0];

                setChanged();
                // System.out.println(message);
                notifyObservers(message);
            } catch (IOException ex) {
                Logger.getLogger(Soc_in.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
