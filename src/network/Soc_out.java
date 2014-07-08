package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Soc_out {
            private String host;
	    private int port;
	    private Socket socket;
	    private BufferedWriter bufferedWriter;

	    
	    //constructor
	    public Soc_out() {
	        this.host = Network_config.IP;
	        this.port = Integer.parseInt(Network_config.outputPort);
	    }

            //connnect to the server
	    private void connect() {
	        try {
	            socket = new Socket(host, port);
	        } catch (IOException ex) {
	            Logger.getLogger(Soc_out.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

            //send the given message to the server
	    public void sendMessage(String message) {
	        try {
	            if (socket == null) {
	                connect();
	            }
	            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	            bufferedWriter.write(message);
	            bufferedWriter.close();
	            socket.close();
	            socket = null;

	        } catch (IOException ex) {
	            Logger.getLogger(Soc_out.class.getName()).log(Level.SEVERE, null, ex);

	        }
	    }
}
