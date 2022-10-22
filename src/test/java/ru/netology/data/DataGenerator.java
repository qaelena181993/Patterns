package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    // генерацию городов выполняем, используя массив валидных городов и класс Random
    public static String generateCity() {
        String[] city = {"Архангельск", "Москва", "Казань", "Уфа", "Самара", "Тюмень", "Калининград", "Нижний Новгород"};
        Random random = new Random();
        int rand = random.nextInt(city.length);
        return city[rand];
    }

    // генерируем имя,  используя Faker
    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    //генерируем телефон, используя Faker
    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

//        // создание пользователя user с использованием методов generateCity(locale), Name, Phone
//        public static UserInfo generateUser(String locale) {
//            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
//        }
//    }
//    @Value
//    public static class UserInfo {
//        String city;
//        String name;
//        String phone;
//    }
    }
}
