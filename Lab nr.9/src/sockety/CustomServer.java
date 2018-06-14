package sockety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomServer {

    private class Logic {

        private DataInputStream input = null;
        private DataOutputStream output = null;

        public Logic(Socket socket) throws IOException {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }

        public boolean checkLogin() throws IOException {
            String type;
            String login = input.readUTF();
            String password = input.readUTF();
            if ("user".equals(login) && "user".equals(password)) {
                System.out.println("Login success. ->user");
                type = "user";
            } else if ("admin".equals(login) && "admin".equals(password)) {
                System.out.println("Login success. ->admin");
                type = "admin";
            } else {
                System.out.println("Login failed.");
                type = null;
            }
            output.writeUTF(type);
            return type != null;
        }
    }
    ServerSocket server;
    Thread serverThread;
    Thread clientThread;
    Thread mainThread;
    final int backlog = 5;
    private boolean started = false;
    private boolean looped = true;
    private Socket client;
    private ServerSocket serverSocket;
    Set<Thread> threads = Collections.synchronizedSet(new HashSet<>());
    Set<Logic> logics = Collections.synchronizedSet(new HashSet<>());
    Set<Socket> clientsSockets = Collections.synchronizedSet(new HashSet<>());
    private static final int ECHO = 1;
    private static final int PING_FRAME = 2;
  //  private static final int HELLO_WORLD_FRAME = 3;
    private static final int EXIT_FRAME = 3;
    private static final int SHUTDOWN = 4;
    public boolean isStarted() {
        return this.started;
    }
    public boolean isLooped() {
        return this.looped;
    }
    public void run(int port) throws IOException {
        server = new ServerSocket(port, backlog);
        mainThread = Thread.currentThread();
        System.out.println("Server is listening on " + server.getLocalSocketAddress());
        serverThread = new Thread() {
            @Override
            public void run() {
                started = true;
                while (isLooped()) {
                    try {
                        client = server.accept();
                        clientsSockets.add(client);
                        clientThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    doClientLogic(client);
                                } catch (IOException | InterruptedException ex) {}
                            }
                        };
                        clientThread.start();
                        threads.add(clientThread);
                    } catch (IOException e) {}
                }
                try {
                    server.close();
                } catch (IOException ex) {
                    System.out.println("Server close() -> " + ex);
                }
            }
        };
        serverThread.start();
    }
    public void stop() throws IOException, InterruptedException {
        looped = false;
        logics.forEach(logic -> {
            try {
                logic.output.close();
            } catch (IOException ex) {}
            finally {
                try {
                    logic.input.close();
                } catch (IOException ex) {}            
            }
        });
        clientsSockets.forEach(socket ->{
            try {
                socket.close();
            } catch (IOException ex) {}
        });
        threads.forEach(thread->{
           thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException ex) {}
        });
        server.close();
        serverThread.interrupt();
        serverThread.join();
       // mainThread.interrupt();
        
    }
    private void doClientLogic(Socket socket) throws IOException, InterruptedException {
        Logic logic = new Logic(socket);
        logics.add(logic);
        logic.checkLogin();
        while (!Thread.interrupted()) {
            switch (logic.input.readInt()) {
                case ECHO:
                    String msg = logic.input.readUTF();
                    System.out.println(msg);
                    logic.output.writeUTF(msg);
                    logic.output.flush();
                    break;
                case PING_FRAME:
                    logic.output.writeInt(client.isConnected() ? 1 : 0);
                    logic.output.flush();
                    break;
                case EXIT_FRAME:
                    try {
                        logic.output.close();
                    } finally {
                        try {
                            logic.input.close();
                        } finally {
                            client.close();
                        }
                    }
                    threads.remove(Thread.currentThread());
                    Thread.currentThread().interrupt();
                    Thread.currentThread().join();
                    System.out.println("Client disconnected");
                    break;
                case SHUTDOWN:
                    stop();
                    System.out.println("Server shutting down!");
                    break;
                default:
                    break;
            }
        }
        System.out.println("doClientLogic() closed");
    }
}
