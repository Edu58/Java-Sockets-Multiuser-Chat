import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /*
    * Starts the server and creates a new thread for each connected client
    * */
    public void startServer() throws IOException {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
    }

    /*
    * Cloes the server socket
    * */
    public void closeServerSocket() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
            System.out.println("Socket Closed!!!");
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        Server server = new Server(socket);
        server.startServer();
    }
}
