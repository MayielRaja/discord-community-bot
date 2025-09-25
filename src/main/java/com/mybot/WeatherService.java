package com.mybot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = "0274c942e8cd43da9eb160914252509";

    public String getWeather(String city) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "Could not find weather for the city: " + city;
            }

            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONObject current = json.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            String description = condition.getString("text");
            double temperature = current.getDouble("temp_c");
            return String.format("The current weather in %s is %.1f°C with %s.", city, temperature, description);

        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
            return "An error occurred while fetching the weather.";
        }
    }
}
