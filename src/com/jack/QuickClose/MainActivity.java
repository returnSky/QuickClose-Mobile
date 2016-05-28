package com.jack.QuickClose;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends Activity {
    private Button button = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button) findViewById(R.id.btn_start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sendCmdByTCP();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    private final void sendCmdByTCP() throws IOException {
        Socket socket = null;
        socket = new Socket("192.168.23.1", 6789);
        OutputStream outputStream = socket.getOutputStream();
        String cmd = "shutdown";
        byte[] bytes = cmd.getBytes();
        int length = bytes.length;
        outputStream.write(bytes, 0, length);
        outputStream.close();
    }
}
