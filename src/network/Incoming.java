package network;

import java.util.Observable;
import java.util.Observer;


public class Incoming extends Observable implements Observer {

    private Soc_in socket;

    //constructor
    public Incoming() {
        socket = new Soc_in();
        socket.addObserver(this);
        Thread thread = new Thread(socket);
        thread.start();
    }

    //get updates from the socket and notify the handler object instance
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Soc_in) {
            //print the server messages 
            System.out.println((String) arg);
            setChanged();
            notifyObservers((String) arg);
        }

    }
}
