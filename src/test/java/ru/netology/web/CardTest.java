package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class CardTest {
    @Test
    void shouldSuccessfulSendValidForm() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79250000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(text(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldGetErrorMessageIfNameInvalid() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetErrorMessageIfNameEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorMessageIfPhoneInvalid() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+792500000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetErrorMessageIfPhoneEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79250000000");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                        " и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
