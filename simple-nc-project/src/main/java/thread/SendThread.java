package thread;

import java.io.*;
import java.net.Socket;

public class SendThread implements Runnable {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter printWriter;

    public SendThread(Socket socket) {
        this.socket = socket;
    }

    public void allClose() throws IOException {
        this.br.close();
        this.printWriter.close();
        this.socket.close();
    }

    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            printWriter = new PrintWriter(socket.getOutputStream());

            while (!this.socket.isClosed()) {
                String sendMessage = br.readLine();
                printWriter.println(sendMessage);
                printWriter.flush();
            }
            this.allClose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
