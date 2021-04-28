package com.example.pc.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView actword;
    Button btSearch;
    TextView tvMeanig;

    ArrayList<String> words=new ArrayList<String>();

    ArrayList<String> meanings=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actword=(AutoCompleteTextView)findViewById(R.id.actword);
        btSearch=(Button)findViewById(R.id.btSearch);
        tvMeanig=(TextView)findViewById(R.id.textview);



        initialiseList();

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=actword.getText().toString();
                for(int i=0; i<words.size(); i++){
                    if(word.equalsIgnoreCase(words.get(i))){
                        tvMeanig.setText(meanings.get(i));
                        break;
                    }
                }
            }
        });

    }

    void initialiseList()
    {
        Scanner scan=new Scanner(getResources().openRawResource(R.raw.dictionary));
        while(scan.hasNext()){
            String[] splits =scan.nextLine().split(":");

            words.add(splits[0]);
            meanings.add(splits[1]);
        }
        scan.close();

        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, words);

        actword.setThreshold(3);

        actword.setAdapter(adapter);
        }

}

