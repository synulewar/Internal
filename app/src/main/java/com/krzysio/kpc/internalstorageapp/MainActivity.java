package com.krzysio.kpc.internalstorageapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private String filename;
    private String filecontent;
    private EditText file_name;
    private EditText file_content;
    private Button save;
    private Button read;
    private TextView read_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        file_name = (EditText) findViewById(R.id.file_name);
        file_content = (EditText) findViewById(R.id.file_content);
        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);
        read_content = (TextView) findViewById(R.id.show_file_content);


        save.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = file_name.getText().toString();
                filecontent= file_content.getText().toString();

                FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(filecontent.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        read.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                BufferedReader input = null;
                File file = null;
                StringBuffer buffer = null;
                try {
                    file = new File(getFilesDir(), filename);
                    input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String line;
                    buffer = new StringBuffer();
                    while ((line = input.readLine()) != null) {
                        buffer.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                read_content.setText(buffer.toString());
            }
        });


    }
}
