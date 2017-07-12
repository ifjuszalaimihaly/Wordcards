package com.example.mihalyszalai.wordcards.filereader;

import android.content.Context;
import android.util.Log;

import com.example.mihalyszalai.wordcards.R;
import com.example.mihalyszalai.wordcards.entities.WordPair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Mihaly Szalai on 2016. 12. 06..
 */

public class FileWordPairReader {

    ArrayList<WordPair> wordPairs = new ArrayList<>();
    Context context;

    public FileWordPairReader(Context context) {
        this.context = context;
    }

    public void readAllWords() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.people);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.ready()) {
                String s = bufferedReader.readLine();
                String foreignlanguage = slice(s,';');
                int charat = foreignlanguage.length()+1;
                String nativelanguage = s.substring(charat);
                Log.d("DEBUG",foreignlanguage);
                Log.d("DEBUG",nativelanguage);
                WordPair wordPair = new WordPair(nativelanguage,foreignlanguage);
                wordPairs.add(wordPair);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<WordPair> getWordPairs() {
        return wordPairs;
    }

    public void setWordPairs(ArrayList<WordPair> wordPairs) {
        this.wordPairs = wordPairs;
    }

    private static String slice(String s, char separator) {
        int i = 0;
        while (s.charAt(i) != separator && i < s.length()) {
            i++;
        }
        s = s.substring(0, i--).trim();
        return s;

    }

    private static String slice2(String s, char separator) {
        int i = 0;
        while (s.charAt(i) != separator && i < s.length()) {
            i++;
        }
        i++;
        s = s.substring(i).trim();
        return s;
    }
}
