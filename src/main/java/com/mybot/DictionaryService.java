package com.mybot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException; // Semicolon was missing here
import java.util.ArrayList;
import java.util.List;

public class DictionaryService {
    private final OkHttpClient client = new OkHttpClient();

    public WordEntry getDefinition(String word) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return null;
            }

            String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            JSONObject firstEntry = jsonArray.getJSONObject(0);

            String wordText = firstEntry.getString("word");
            String phonetic = firstEntry.optString("phonetic", "");
            List<Meaning> meanings = new ArrayList<>();
            JSONArray meaningsArray = firstEntry.getJSONArray("meanings");
            for (int i = 0; i < meaningsArray.length(); i++) {
                JSONObject meaningObj = meaningsArray.getJSONObject(i);
                String partOfSpeech = meaningObj.getString("partOfSpeech");

                List<Definition> definitions = new ArrayList<>();
                JSONArray definitionsArray = meaningObj.getJSONArray("definitions");
                for (int j = 0; j < definitionsArray.length(); j++) {
                    JSONObject defObj = definitionsArray.getJSONObject(j);
                    String definitionText = defObj.getString("definition");
                    definitions.add(new Definition(definitionText));
                }
                meanings.add(new Meaning(partOfSpeech, definitions));
            }
            return new WordEntry(wordText, phonetic, meanings);
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}