package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

class Weather {
    public static void weather(double latitude, double longitude, String city){
        try {
            // адрес api яндекс weather с нашими кордами
            String url = String.format(Locale.US,"https://api.weather.yandex.ru/v2/forecast/?lat=%f&lon=%f&limit=2&lang=ru_RU",latitude,longitude);
            //ключ к api
            String apiKey = Key.getKey();

            //создаем http клиент, который и будет общаться с яндекс weather
            HttpClient client = HttpClient.newHttpClient();
            // билдим запрос для нашего клиента
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("X-Yandex-API-Key", apiKey)
                    .GET()
                    .build();
            //отправка
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //расспознаем код который получили в ответ
            if (response.statusCode() == 200) {
                // логирования получения ответа
                System.out.println("Данные успешно получены!");
                // вызов jacson, который нам пропарсит полученное
                ObjectMapper mapper = new ObjectMapper();
                // составление древовидной структуры ответа
                JsonNode rootNode =mapper.readTree(response.body());
                // сам парсинг(выбран .path() вместо .get() для защиты от ошибок яндекс, тк он возвращает пустой узел, а не null или NullPointerException, благодаря чем код не запнется и пойдет дальше)
                JsonNode factNode =rootNode.path("fact");
                Parser parser = new  Parser(factNode,rootNode, true);
                // Вывод результатов в консоль
                //день 0(сегодня)
                Weather_list.list(city,parser.temp,parser.feelsLike,parser.condition,parser.windSpeed,parser.Day, parser.sunrise, parser.sunset);
                JsonNode tomorrowForecast = rootNode.path("forecasts").get(1);
                //день 1(завтра)
                JsonNode dayPart = tomorrowForecast.path("parts").path("day");
                Parser parser_tommorow = new  Parser(dayPart,rootNode, false);
                Weather_list.list(city,parser_tommorow.temp,parser_tommorow.feelsLike,parser_tommorow.condition,parser_tommorow.windSpeed,parser_tommorow.Day, parser_tommorow.sunrise, parser_tommorow.sunset);

                // код оказался неудачным, допустим 404(проблемы на нашей стороне) или 302(нас перенаправили)
            } else {
                System.out.println("Ошибка выполнения: HTTP " + response.statusCode());
                System.out.println("Ответ сервера: " + response.body());
            }

        } catch (Exception e) {
            System.out.println("Ошибка при отправке запроса: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
