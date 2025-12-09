package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {
    // Объявление элементов страницы регистрации
    SelenideElement
            // Блок с информацией о рейсе (поиск по ID)
            flightInfo = $("#flightRegistrationInfo"),
    // Кнопка завершения регистрации (поиск по XPath по частичному тексту)
    buttonFinishRegistration = $x("//button[contains(.,'Завершить регистрацию')]");

    /**
     * Шаг проверки корректности данных рейса
     * @param cityFrom город вылета
     * @param cityTo город назначения
     */
    @Step("Проверка, что данные корректные")
    public void isFlightDataCorrect(String cityFrom, String cityTo) {
        // Проверяем что элемент видим и содержит правильный маршрут
        flightInfo
                .shouldBe(visible)  // Сначала проверяем видимость элемента
                .shouldHave(text(cityFrom + " → " + cityTo));  // Затем проверяем текст маршрута
    }

    /**
     * Шаг завершения бронирования билетов
     * Выполняет полный процесс регистрации с проверками
     */
    @Step("Бронирование билетов")
    public void successRegistration() {
        // Клик по кнопке завершения регистрации
        buttonFinishRegistration.click();

        // Работа с всплывающим alert-окном
        Alert alert = switchTo().alert();  // Переключаемся на alert
        // Проверяем текст в alert с помощью JUnit assertion
        assertTrue(alert.getText().contains("Бронирование завершено"));
        alert.accept();  // Подтверждаем alert (нажимаем OK)

        // Проверяем сообщение об успехе на самой странице
        $("#registrationMessage").shouldHave(text("Регистрация успешно завершена!"));
    }
}