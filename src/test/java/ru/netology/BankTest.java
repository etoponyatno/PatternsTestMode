package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BankTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void StatusActive() {
        SelenideElement form = $("[action]");
        UserData userData = DataGenerator.statusActive();
        form.$("[name=login]").setValue(userData.getLogin());
        form.$("[name=password]").setValue(userData.getPassword());
        form.$("[data-test-id=action-login]").click();
        $$(".heading").find(exactText("Личный кабинет")).shouldBe(exist);
    }

    @Test
    void StatusBlocked() {
        SelenideElement form = $("[action]");
        UserData userData = DataGenerator.statusBlocked();
        form.$("[name=login]").setValue(userData.getLogin());
        form.$("[name=password]").setValue(userData.getPassword());
        form.$("[data-test-id=action-login]").click();
        $(byText("Пользователь заблокирован")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void InvalidLogin() {
        SelenideElement form = $("[action]");
        UserData userData = DataGenerator.invalidLogin();
        form.$("[name=login]").setValue(userData.getLogin());
        form.$("[name=password]").setValue(userData.getPassword());
        form.$("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void InvalidPassword() {
        SelenideElement form = $("[action]");
        UserData userData = DataGenerator.invalidPassword();
        form.$("[name=login]").setValue(userData.getLogin());
        form.$("[name=password]").setValue(userData.getPassword());
        form.$("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }
}