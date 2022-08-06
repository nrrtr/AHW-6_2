package ru.netology.web.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TemplateSteps {
    String url = "http://localhost:9999";
    private static DashboardPage dashboardPage;

    @Пусть("Пусть пользователь залогинен с именем {string} и паролем {string}")
    public void login(String login, String password){
        var loginPage = open(url, LoginPage.class);
        var verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
    }

    @Когда("Когда пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void transferBalance(String amount, String cardNumber, String cardId) {
        
    }
}
