package sockety;

import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class ClientThread extends Thread{
    final Socket clientSocket; //initialize in const'r
    private final Set<ClientThread> connections;

    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientThread(Socket clientSocket, Set<ClientThread> connections) {
        this.clientSocket = clientSocket;
        this.connections = connections;
    }

    public void broadcastMessage(Message msg){
        connections.forEach((t) -> {
            try {
                if(t.clientSocket.isConnected()) {
                    t.out.writeObject(msg);
                    t.out.flush();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void run() {
        try {
            System.out.println("run");
          //  clientSocket.connect(clientSocket., clientSocket.port);
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                String msg = (String) in.readObject();
                System.out.println(msg);    
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
