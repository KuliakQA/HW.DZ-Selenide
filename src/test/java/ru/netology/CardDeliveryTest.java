package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    @Test
    public void shouldSuccessfulSendValidForm() {
        Configuration.holdBrowserOpen = true;
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("14.01.2022");
        $("[data-test-id='name'] input").setValue("Комаров Виктор");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на 14.01.2022"),
                        Duration.ofSeconds(15));

    }

    @Test
    public void shouldSuccessfulSendValidFormWithSmallName() {
        Configuration.holdBrowserOpen = true;
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Калининград");
        $("[data-test-id='date'] input").setValue("28.01.2022");
        $("[data-test-id='name'] input").setValue("Ян Гэ");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на 28.01.2022"),
                        Duration.ofSeconds(15));

    }

    @Test
    public void shouldSuccessfulSendValidFormWithDistantDate() {
        Configuration.holdBrowserOpen = true;
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Калининград");
        $("[data-test-id='date'] input").setValue("15.01.2023");
        $("[data-test-id='name'] input").setValue("Ян Гэ");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на 15.01.2023"),
                        Duration.ofSeconds(15));

    }
}
