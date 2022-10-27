package thread;

import java.io.*;
import java.net.Socket;

public class ReceiveThread implements Runnable {

    private Socket socket;
    private BufferedReader input;

    public ReceiveThread(Socket socket) {
        this.socket = socket;
    }

    public void allClose() throws IOException {
        this.socket.close();
        this.input.close();
    }

    @Override
    public void run() {
        try {
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            while (true) {
                String receiveMessage = this.input.readLine();

                if (receiveMessage.equals("null")) {
                    this.allClose();
                    System.exit(0);
                } else {
                    System.out.println(receiveMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
