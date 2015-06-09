package lt.bazi.progr4mer.oeebrowser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    InputStream input = null;
    OutputStream output = null;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int Send(String text) {
        if (output != null) {
            try {
                output.write(text.getBytes(), 0, text.getBytes().length);
                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return 1;
            }
        } else return -1;
    }

    private int Receive(byte[] buff) {
        if (input != null) {
            try {
                wait(500);
                return input.read(buff);
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1;
            }
        } else return -1;
    }
}
