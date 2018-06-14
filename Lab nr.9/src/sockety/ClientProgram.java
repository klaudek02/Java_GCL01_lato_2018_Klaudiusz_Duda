package sockety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Klaudiusz
 */
public class ClientProgram {

    public static void main(String[] args) throws IOException, InterruptedException {
        CustomClient client = new CustomClient();
        String address = "localhost";
        int port = 8080;
        try {
            client.connect(address, port);
            System.out.println("Client connected.");
        } catch (IOException ex) {
            System.out.println("Client connection problem.");
            return;
        }
        client.launchMenu();

    }
}
