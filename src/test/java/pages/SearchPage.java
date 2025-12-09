package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class SearchPage {
    // Объявление элементов страницы поиска рейсов
    SelenideElement
            // Поле выбора города вылета (поиск по ID)
            cityFrom = $("#departureCity"),
    // Поле выбора города назначения (поиск по ID)
    cityTo = $("#arrivalCity"),
    // Поле ввода даты вылета (поиск по ID)
    departureDate = $("#departureDate"),
    // Кнопка поиска рейсов (поиск по XPath по точному тексту)
    findButton = $x("//button[.='Найти']"),
    // Блок для сообщений системы (поиск по ID)
    message = $("#searchMessage");

    /**
     * Поиск рейсов только по дате (города остаются по умолчанию или ранее выбранными)
     * @param departureDate дата вылета в строковом формате
     */
    @Step("Выбор даты")
    public void search(String departureDate) {
        this.departureDate.setValue(departureDate);  // Заполняем поле даты
        this.findButton.click();  // Нажимаем кнопку поиска
    }

    /**
     * Расширенный поиск рейсов с указанием всех параметров
     * @param departureDate дата вылета
     * @param from город вылета
     * @param to город назначения
     */
    public void search(String departureDate, String from, String to) {
        this.departureDate.setValue(departureDate);  // Заполняем дату
        this.cityFrom.selectOption(from);  // Выбираем город вылета из выпадающего списка
        this.cityTo.selectOption(to);      // Выбираем город назначения из выпадающего списка
        this.findButton.click();           // Запускаем поиск
    }

    /**
     * Проверка сообщения о необходимости указать дату вылета
     */
    @Step("Не указана дата вылета")
    public void isDepartureDateEmpty() {
        // Проверяем, что отображается сообщение о пустой дате
        this.message.shouldHave(text("Пожалуйста, укажите дату вылета."));
    }
}