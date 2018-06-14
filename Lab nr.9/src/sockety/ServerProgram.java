/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockety;

import java.io.IOException;
public class ServerProgram {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        CustomServer server = new CustomServer();
        try {
            server.run(port);
        } catch (IOException ex) {
            System.out.println("Server starting problem.");
        }

    }
}
