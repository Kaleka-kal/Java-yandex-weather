package org.example;

import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    String sunrise = "";
    String sunset = "";
    double windSpeed = 0.0;
    String condition = "";
    int feelsLike = 0;
    int temp = 0;
    String Day = "";
    JsonNode firstForecast;
    Parser(JsonNode par,JsonNode root, Boolean Today) {
        if (Today) temp = par.path("temp").asInt();
        else temp = par.path("temp_avg").asInt();
        feelsLike = par.path("feels_like").asInt();
        condition = par.path("condition").asText();
        windSpeed = par.path("wind_speed").asDouble();
        int day = Today ? 0 : 1;
        Day = day == 0 ? "СЕЙЧАС" : "ЗАВТРА";
        firstForecast =  root.path("forecasts").get(day);
        if (firstForecast != null) {
            sunrise = firstForecast.path("sunrise").asText();
            sunset = firstForecast.path("sunset").asText();
        }
    }

}
