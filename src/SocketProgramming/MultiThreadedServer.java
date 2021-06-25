package SocketProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8080);
        System.out.println("Server started ..\nWaiting for the clients to connect ..");

        while(true){
            Socket clientSocket = socket.accept();
            System.out.println("Client connected ..");
            Messaging obj = new Messaging(clientSocket);
            obj.start();

        }
    }
}
