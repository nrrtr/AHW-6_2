package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] .input__control");
    private SelenideElement passwordField = $("[data-test-id='password'] .input__control");
    private SelenideElement loginButton = $("[data-test-id='action-login']");

    public SMSVerificationPage validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new SMSVerificationPage();
    }
}
