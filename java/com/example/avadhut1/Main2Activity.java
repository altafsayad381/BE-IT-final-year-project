package com.example.avadhut1;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.avadhut1.Adapter.ChatMessageAdapter;
import com.example.avadhut1.Model.ChatMessage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {

    public static Chat chat;
    ListView listView;
    FloatingActionButton btnSend;
    EditText edtTextMsg;
    ImageView imageView;
    private Bot bot;
    private ChatMessageAdapter adapter;
    private String response;

    public static boolean isSDCartAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listView);
        btnSend = findViewById(R.id.btnSend);
        edtTextMsg = findViewById(R.id.edtTextMsg);
        imageView = findViewById(R.id.imageView);

        adapter = new ChatMessageAdapter(Main2Activity.this, new ArrayList<ChatMessage>());
        listView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtTextMsg.getText().toString();
                String response = chat.multisentenceRespond(edtTextMsg.getText().toString());
                if (TextUtils.isEmpty(message)) {
                    Toast.makeText(Main2Activity.this, "Please Enter A Query", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessage(message);
                botsReply(response);

                //clear Edit text
                edtTextMsg.setText("");
                listView.setSelection(adapter.getCount() - 1);
            }
        });

        boolean available = isSDCartAvailable();
        {
            AssetManager assets = getResources().getAssets();
            File fileName = new File(Environment.getExternalStorageDirectory().toString() + "/TBC/bota/TBC");
            boolean makeFile = fileName.mkdirs();

            if (fileName.exists()) ;

            //read the line

            try {
                for (String dir : assets.list("TBC")) {
                    File subDir = new File(fileName.getPath() + "/" + dir);
                    boolean subDir_Check = subDir.mkdirs();

                    for (String file : assets.list("TBC/" + dir)) {
                        File newFile = new File(fileName.getPath() + "/" + dir + "/" + file);

                        if (newFile.exists()) {
                            continue;
                        }
                        InputStream in;
                        OutputStream out;
                        in = assets.open("TBC/" + dir + "/" + file);
                        out = new FileOutputStream(fileName.getPath() + "/" + dir + "/" + file);
                        //copies file to the sd card

                        copyFile(in, out);
                        in.close();
                        out.flush();
                        out.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MagicStrings.root_path = Environment.getExternalStorageState() + "/TBC";
        AIMLProcessor.extension = new PCAIMLProcessorExtension();

        bot = new Bot("TBC", MagicStrings.root_path, "chat");
        chat = new Chat(bot);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;

        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private void botsReply(String response) {
        ChatMessage chatMessage = new ChatMessage(false, false, response);
        adapter.add(chatMessage);

    }

    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(false, true, message);
        adapter.add(chatMessage);
    }
}