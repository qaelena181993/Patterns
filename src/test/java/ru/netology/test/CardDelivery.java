package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;

public class CardDelivery {

    @BeforeEach
    public void setUp() {

        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        //  var validUser = Registration.generateUser("locale");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = generateDate(daysToAddForSecondMeeting);

        $("[class='input__control'][placeholder='Город']").setValue(generateCity());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date']  input").setValue(firstMeetingDate);
        $("[name='name']").setValue(generateName());
        $("[data-test-id='phone'] [name='phone']").setValue(DataGenerator.generatePhone());
        $("[class='checkbox__box']").click();
        $(".button").click();
            $("[class='notification__content']").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id='date']  input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date']  input").setValue(secondMeetingDate);
        $(".button").click();
        $("[data-test-id='replan-notification']").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));


    }
}
