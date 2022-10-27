import thread.ReceiveThread;
import thread.SendThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Socket socket = new Socket();
        while (true) {
            System.out.print("$ ");
            String line = br.readLine();
            String[] tokens = line.split(" ");

            if (!tokens[0].equals("snc")) {
                continue;
            }

            if (tokens[1].equals("-l")) {
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(tokens[2]));
                socket = serverSocket.accept();
            } else if (tokens[1].equals("127.0.0.1")) {
                socket = new Socket(tokens[1], Integer.parseInt(tokens[2]));
            } else {
                continue;
            }
            break;
        }

        Thread sendThread = new Thread(new SendThread(socket));
        Thread receiveThread = new Thread(new ReceiveThread(socket));

        sendThread.start();
        receiveThread.start();

    }
}
