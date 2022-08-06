package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public String getCardBalance(String cardNumber) {
        return extractBalance(cards.find(text(cardNumber.substring(15, 19))).getText());
    }

    public String getCardBalance(int id) {
        String text = cards.get(id - 1).text();
        return extractBalance(text);
    }

    public TransferPage cardSelectToBalanceTransfer(String cardNumber) {
        cards.findBy(text(cardNumber.substring(15, 19))).$("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    public TransferPage cardSelectToBalanceTransfer(int id) {
        cards.get(id - 1).$("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    private String extractBalance(String text) {
        String balanceStart = "баланс: ";
        String balanceFinish = " р.";
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        return text.substring(start + balanceStart.length(), finish);
    }

}