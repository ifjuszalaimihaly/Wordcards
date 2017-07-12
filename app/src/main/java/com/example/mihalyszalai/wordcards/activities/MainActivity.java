package com.example.mihalyszalai.wordcards.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mihalyszalai.wordcards.R;
import com.example.mihalyszalai.wordcards.entities.WordPair;
import com.example.mihalyszalai.wordcards.filereader.FileWordPairReader;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonquestion = (Button) findViewById(R.id.questionButton);
        final FileWordPairReader fileWordPairReader = new FileWordPairReader(this);
        fileWordPairReader.readAllWords();
        buttonquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("wordpairs",fileWordPairReader.getWordPairs());
                startActivity(intent);
            }
        });
    }


    private ArrayList<WordPair> makesomewordpairs(){
        ArrayList<WordPair> wordPairs = new ArrayList<>();
        WordPair wordPair = new WordPair("kutya","dog");
        WordPair wordPair1 = new WordPair("macska","cat");
        WordPair wordPair2 = new WordPair("kacsa","duck");
        WordPair wordPair3 = new WordPair("disznó","pig");
        wordPairs.add(wordPair);
        wordPairs.add(wordPair1);
        wordPairs.add(wordPair2);
        wordPairs.add(wordPair3);
        return wordPairs;
    }
}
