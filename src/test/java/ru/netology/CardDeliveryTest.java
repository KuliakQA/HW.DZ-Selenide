package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulSendValidForm() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Москва");
        String date = LocalDate.now().plusDays(8).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Комаров Виктор");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + date),
                        Duration.ofSeconds(15));

    }

    @Test
    public void shouldSuccessfulSendValidFormWithSmallName() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Калининград");
        String date = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ян Гэ");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + date),
                        Duration.ofSeconds(15));

    }

    @Test
    public void shouldSuccessfulSendValidFormWithDistantDate() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Калининград");
        String date = LocalDate.now().plusDays(400).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ян Гэ");
        $("[data-test-id='phone'] input").setValue("+78565478547");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + date),
                        Duration.ofSeconds(15));

    }
}
