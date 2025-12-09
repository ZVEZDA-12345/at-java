package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class FlightsListPage {
    // Объявление элементов страницы списка рейсов
    SelenideElement
            // Таблица со списком рейсов (поиск по ID)
            flightsTable = $("#flightsTable"),
    // Кнопка регистрации на рейс (поиск по XPath по тексту кнопки)
    registerButton = $x("//button[.='Зарегистрироваться']");

    // Шаг проверки отсутствия рейсов
    @Step("Поиск несуществующего рейса")
    public void isNoFlights() {
        // Проверка, что в таблице рейсов отображается сообщение об отсутствии рейсов
        flightsTable.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }

    // Метод для регистрации на первый найденный рейс
    public void registerToFirstFlight() {
        // Клик по кнопке регистрации (предположительно для первого рейса в списке)
        this.registerButton.click();
    }
}