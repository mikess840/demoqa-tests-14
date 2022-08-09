package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.timeout = 10000; // 10 seconds
//        Configuration.browser = "opera";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave("Student Registration Form"); // проверяем что страничка подрузилась
        executeJavaScript("$('footer').remove()"); //убираем баннеры
        executeJavaScript("$('footer').remove()");

//        $("[id=userName]").setValue("Egor");
        $("#firstName").setValue("Mike");
        $("#lastName").setValue("LolKekovich");
        $("#userEmail").setValue("Tester@tester.com");
        $("#genterWrapper").$(byText("Male")).click(); //ищем внутри блока  //$(".custom-control-label").click()
        //$("[for=genter-radio-3]").click();
        //$(by("for","genter-radio-3").click();
        $("#userNumber").setValue("9992223344");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(7);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText("1990");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/test.jpg"));
        //$("#uploadPicture").uploadFromClasspath("test.jpg");    //ищет сразу в папке ресурссы
        $("#currentAddress").setValue("Yekaterinburg");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#submit").click();

        $(".modal-diakit").should(appear); // мониторит что диалог появился

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text("Mike LolKekovich"),
                text("Tester@tester.com"),
                text("Male"),
                text("9992223344"),
                text("15 August,1990"),
                text("Economics"),
                text("Sports, Music"),
                text("test.jpg"),
                text("Yekaterinburg")
        );
        $(".modal-body").shouldHave(text("Haryana Karnal"));
        $(".modal-footer").shouldHave(text("Close"));

        //    $("#table-responsive table").$(byText("Date of Birth")).parent().shouldHave(text("15 August,1990")); - соответствие значений таблицы


//        $("#output #name").shouldHave(text("Egor"));
//        $("#output").$("#name").shouldHave(text("Egor"));
//        $("#output").shouldHave(text("Egor"));
//        $("#name").shouldHave(text("Egor"));
//        $("#output #email").shouldHave(text("Egor@egor.com"));
//        $("#output #currentAddress").shouldHave(text("Some address 1"));
//        $("#output #permanentAddress").shouldHave(text("Another address 1"));
    }

}