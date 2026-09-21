package org.example;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Iterator;

public class Main {
    public static void main(String[] args)
    {   //локаль написана, чтоб Double писались с точкой
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Выберите номер города");
        //итератор и переменная созданы для выявления последнего элемента перебора
        int lastsector = 0;
        Iterator<City> iterator = EnumSet.allOf(City.class).iterator();
        while (iterator.hasNext())
        {
            City city = iterator.next();
            System.out.printf(Locale.US,"%d: %s(%.3f, %.3f)\n",city.getNumber(),city.name(),city.getLots(),city.getLons());
            if (!iterator.hasNext()) {
                // запоминаем последний элемент
                lastsector = city.getNumber() + 1;
                System.out.printf("%d: внести свои корды \n",lastsector);
            }
        }
        int city = sc.nextInt();
        if(city == lastsector) {
            System.out.println("Впишите ширину города(пример: 52.6667)");
            double latitude = sc.nextDouble();
            System.out.println("Впишите долготу города(пример: 41.3333)");
            double longitude = sc.nextDouble();
            Weather.weather(latitude, longitude, "unknow");
        }else {
            try {
                for (City f : City.values()) {
                    if (f.getNumber() == city) {
                        Weather.weather(f.getLons(), f.getLots(), f.name());
                    }
                }
            }catch(Exception e){
                System.out.println("Вы ввели неправильный код города, запустите программу заново");
            }
        }
    }
}