package ru.netology.web.steps;

import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TemplateSteps {
    private static DashboardPage dashboardPage;

    @Before
    public static void setUp() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage
                .validLogin(DataHelper.getAuthInfo().getLogin(), DataHelper.getAuthInfo().getPassword());
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalance = Integer
                .parseInt(dashboardPage.getCardBalance(DataHelper.getFirstCardNumber().getCardNumber()));
        var secondCardBalance = Integer
                .parseInt(dashboardPage.getCardBalance(DataHelper.getSecondCardNumber().getCardNumber()));
        var diffBetweenBalances = (firstCardBalance - secondCardBalance) / 2;
        if (diffBetweenBalances > 0) {
            var transferPage = dashboardPage.cardSelectToBalanceTransfer(2);
            transferPage.transferBalance(String.valueOf(diffBetweenBalances),
                    String.valueOf(DataHelper.getFirstCardNumber().getCardNumber()));
        } else {
            var transferPage = dashboardPage.cardSelectToBalanceTransfer(1);
            transferPage.transferBalance(String.valueOf(diffBetweenBalances),
                    String.valueOf(DataHelper.getSecondCardNumber().getCardNumber()));
        }
    }

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void login(String login, String password) {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void transferBalance(String amount, String cardNumber, String cardId) {
        var transferPage = dashboardPage.cardSelectToBalanceTransfer(Integer.parseInt(cardId));
        dashboardPage = transferPage.transferBalance(amount, cardNumber);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void checkBalance(String cardId, String expectedBalance) {
        var actualBalance = dashboardPage.getCardBalance(Integer.parseInt(cardId));
        Assertions.assertEquals(expectedBalance, actualBalance);
    }
}
