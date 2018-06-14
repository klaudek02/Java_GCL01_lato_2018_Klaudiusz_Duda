package sockety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CustomClient {

    private boolean connected = false;
    private String userType;
    private Socket clientSocket;
    DataInputStream input;
    DataOutputStream output;

    public boolean isConnected() {
        return this.connected;
    }

    public void prepareStreams() throws IOException {
        input = new DataInputStream(clientSocket.getInputStream());
        output = new DataOutputStream(clientSocket.getOutputStream());
    }
    public void launchMenu() throws IOException, InterruptedException {
        this.prepareStreams();
        Scanner scanner = new Scanner(System.in);
     //   System.out.print("Podaj login :");
        String login = "user";
      //  System.out.print("Podaj haslo :");
        String password = "user";
        login(login, password);
        while (this.isConnected()) {
            System.out.println("1. ECHO \n2. PING \n3. DISCONNECT");
            int line = scanner.nextInt();
                switch (line) {
                    case 1:
                        output.writeInt(1);
                        echo("WIADOMOSC Z ECHO");
                        break;
                    case 2:
                        output.writeInt(2);
                        System.out.println(ping()? "Jest polaczenie":"Blad polaczenia");
                        break;
                    case 3:
                        output.writeInt(3);
                       // input.readInt();
                        this.disconnect();
                        System.out.println("Client disconnected.");
                        break;
                    case 4:
                        output.writeInt(4);
//                        input.readInt();
                        this.disconnect();
                        System.out.println("Client disconnected.");
                        break;                        
                    default:
                        break;
                }
            }
        
    }

    public void connect(String host, int port) throws IOException {
        if (this.connected) {
            throw new IOException("Client is already connected.");
        }

        this.clientSocket = new Socket(host, port);
        this.connected = true;
    }

    public void disconnect() {
        if (isConnected()) {
            this.connected = false;
        }
    }

    public boolean login(String username, String password) throws IOException {
        output.writeUTF(username);
        output.writeUTF(password);
        output.flush();
        userType = input.readUTF();
        return userType != null;
    }

    public boolean logout() throws IOException {
        return true;
    }

    public boolean ping() throws IOException {
        return input.readInt() == 1;
    }

    public boolean echo(String text) throws IOException {
        output.writeUTF(text);
        String received = input.readUTF();
        System.out.println("[S]: " + received);
        return received != null;
    }
}
