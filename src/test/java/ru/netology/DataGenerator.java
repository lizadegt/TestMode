package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.util.Locale;

import static io.restassured.RestAssured.*;

public class DataGenerator {
    private final static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    static void setUpAll(UserCreation userCreation) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(userCreation) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static class GenerateUser {
        private GenerateUser() {
        }

        public static String generateLogin() {
            return faker.name().username();
        }

        public static String generatePassword() {
            return faker.internet().password();
        }

        public static UserCreation generateActiveUser() {
            UserCreation userCreation = new UserCreation(generateLogin(), generatePassword(), "active");
            setUpAll(userCreation);
            return userCreation;
        }

        public static UserCreation generateBlockedUser() {
            UserCreation userCreation = new UserCreation(generateLogin(), generatePassword(), "blocked");
            setUpAll(userCreation);
            return userCreation;
        }
    }
}