package com.example.mihalyszalai.wordcards.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihalyszalai.wordcards.R;
import com.example.mihalyszalai.wordcards.entities.WordPair;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends Activity {

    private ArrayList<WordPair> wordPairs = new ArrayList<>();
    private ArrayList<WordPair> askedWordPairs = new ArrayList<>();
    private static final boolean NATIVE_LANGUAGE = true;
    private static final boolean FOREIGN_LANGUAGE = false;
    private boolean direction;
    private RadioGroup radioGroup;
    private TextView textViewword;
    private int index;
    private ImageButton imageButtonSwap;
    private Button buttonKnow;
    private Button buttonDontKnow;
    private int wordpairssize;
    private int knowsize=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        final Intent intent = getIntent();
        wordPairs = (ArrayList<WordPair>) intent.getSerializableExtra("wordpairs");
        wordpairssize=wordPairs.size();
        textViewword = (TextView) findViewById(R.id.textviewword);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroudirction);
        final RadioButton radioButtonForeignButton = (RadioButton) findViewById(R.id.buttonforeignlanguage);
        final RadioButton radioButtonNativeNativeLanguage = (RadioButton) findViewById(R.id.buttonforeignlanguage);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedid) {
                if(checkedid == radioButtonForeignButton.getId()){
                    direction = FOREIGN_LANGUAGE;
                }
                if(checkedid == radioButtonNativeNativeLanguage.getId()){
                    direction = NATIVE_LANGUAGE;
                }
            }
        });

        index=generateIndex();
        if(direction==FOREIGN_LANGUAGE){
            textViewword.setText(wordPairs.get(index).getForeignlanguage());
        } else {
            textViewword.setText(wordPairs.get(index).getNativelanguage());
        }

        imageButtonSwap = (ImageButton) findViewById(R.id.imagebuttonswap);
        imageButtonSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wordPairs.size()>0) {
                    if (textViewword.getText().toString().equals(wordPairs.get(index).getForeignlanguage())) {
                        Log.d("DEBUG", "first case");
                        textViewword.setText(wordPairs.get(index).getNativelanguage());
                    } else {
                        Log.d("DEBUG", "second case");
                        textViewword.setText(wordPairs.get(index).getForeignlanguage());
                    }
                }
            }
        });
        final TextView textViewKnowRate = (TextView) findViewById(R.id.textviwknowrate);
        textViewKnowRate.setText(knowsize+ "/" +wordpairssize);
        final TextView textViewAskedRate = (TextView) findViewById(R.id.textviewaskedrate);
        textViewAskedRate.setText(askedWordPairs.size()+"/"+wordpairssize);
        buttonKnow = (Button) findViewById(R.id.buttonKnow);
        buttonKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(!askedWordPairs.contains(wordPairs.get(index))){
                        askedWordPairs.add(wordPairs.get(index));
                    }
                    wordPairs.remove(index);
                    knowsize++;
                    textViewKnowRate.setText(knowsize + "/" + wordpairssize);
                    textViewAskedRate.setText(askedWordPairs.size() + "/" + wordpairssize);
                } catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                if(wordPairs.size()>0) {
                    index = generateIndex();
                    if (direction == FOREIGN_LANGUAGE) {
                        textViewword.setText(wordPairs.get(index).getForeignlanguage());
                    } else {
                        textViewword.setText(wordPairs.get(index).getNativelanguage());
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Már mindent tudsz",Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonDontKnow = (Button) findViewById(R.id.buttonDontKnow);
        buttonDontKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(!askedWordPairs.contains(wordPairs.get(index))){
                        askedWordPairs.add(wordPairs.get(index));
                    }
                    textViewAskedRate.setText(askedWordPairs.size() + "/" + wordpairssize);
                } catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                if(wordPairs.size()>0) {
                    index = generateIndex();
                    if (direction == FOREIGN_LANGUAGE) {
                        textViewword.setText(wordPairs.get(index).getForeignlanguage());
                    } else {
                        textViewword.setText(wordPairs.get(index).getNativelanguage());
                    }
                }
            }
        });
    }


    private int generateIndex(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(wordPairs.size());
    }


}
