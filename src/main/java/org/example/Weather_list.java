package org.example;

public class Weather_list {
    static void list(String city, int temp, int feelsLike, String condition, double windSpeed,String Day,String sunrise, String sunset){
        System.out.printf("=== ПОГОДА %s (%s) ===\n",Day,city);
        System.out.println("Температура воздуха : " + temp + "°C");
        System.out.println("Ощущается как       : " + feelsLike + "°C");
        System.out.println("Состояние неба      : " + condition);
        System.out.println("Скорость ветра      : " + windSpeed + " м/с");
        System.out.println("Восход солнца       : " + sunrise);
        System.out.println("Закат солнца        : " + sunset);
        System.out.println("==============================");
    }
}
