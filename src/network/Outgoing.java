package network;

public class Outgoing {
    
    private Soc_out socket;

    //constructor
    public Outgoing() {
        socket = new Soc_out();
    }
    
    //forward messages to the socket
    public void sendMessage(String message){
    	socket.sendMessage(message);
    }
}
