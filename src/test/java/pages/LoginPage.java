package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class LoginPage {
    // Объявление элементов страницы
    SelenideElement
            username,      // поле ввода логина
            password,      // поле ввода пароля
            loginButton,   // кнопка входа
            errorMessage,  // блок сообщения об ошибке
            greeting;      // блок приветствия после успешного логина

    // Конструктор класса - инициализирует элементы при создании объекта страницы
    public LoginPage() {
        username = $("#username");        // Поиск по ID элемента username
        password = $("#password");        // Поиск по ID элемента password
        loginButton = $("#loginButton");  // Поиск по ID кнопки входа
        errorMessage = $("#message");     // Элемент для сообщений об ошибках
        greeting = $("#greeting");        // Элемент для приветственного сообщения
    }

    // Шаг для ввода корректных учетных данных
    @Step("Правильные данные")
    public void login(String username, String password) {
        this.username.setValue(username);  // Ввод логина
        this.password.setValue(password);  // Ввод пароля
        this.loginButton.click();          // Клик по кнопке входа
    }

    // Шаг проверки неудачного входа
    @Step("Неправильный логин")
    public void isLoginUnsuccessful() {
        // Проверка наличия сообщения об ошибке
        this.errorMessage.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    // Шаг проверки успешного входа
    @Step("Успешный вход в систему")
    public void isLoginSuccessful(String fio) {
        // Проверка персонализированного приветствия
        this.greeting.shouldHave(text("Добро пожаловать, " + fio + "!"));
    }
}