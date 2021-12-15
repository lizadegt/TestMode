package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class AutoTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginActiveUser() {
        UserCreation user = DataGenerator.GenerateUser.generateActiveUser();
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        //сообщение что все гуд
        $(withText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldLoginBlockedUser() {
        UserCreation user = DataGenerator.GenerateUser.generateBlockedUser();
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void shouldLoginActiveUserInvalidLogin() {
        UserCreation user = DataGenerator.GenerateUser.generateActiveUser();
        $("[data-test-id=login] input").sendKeys(DataGenerator.GenerateUser.generateLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldLoginBlockedUserInvalidPassword() {
        UserCreation user = DataGenerator.GenerateUser.generateBlockedUser();
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword() + "1");
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
    }

    @Test
    void shouldLoginActiveUserInvalidLoginAndPassword() {
        UserCreation user = DataGenerator.GenerateUser.generateActiveUser();
        $("[data-test-id=login] input").sendKeys(DataGenerator.GenerateUser.generateLogin());
        $("[data-test-id=password] input").sendKeys(DataGenerator.GenerateUser.generatePassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
    }
}
