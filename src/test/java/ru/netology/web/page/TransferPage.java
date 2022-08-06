package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage transferBalance(String amount, String cardNumber) {
        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(amount));
        $("[data-test-id='from'] .input__control").setValue(cardNumber);
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }
}
