package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase{

    @Test
    @DisplayName("Successful fill registration form")
    void fillPracticeFormTest() {
        step("Open students registration form", () -> open(testURL));
        step("Fill the form", () -> {
            fillForm(firstname, lastname, userEmail, gender, userNumber, monthOfBirthday, yearOfBirthday, dayOfBirthday,
                    subject1, subject2, hobby1, hobby2, picture, address, state, city);
        });
        step("Submit the form", () -> submitForm());
        step("Verify submitted form", () -> {
            verifySubmittedForm(firstname, lastname, userEmail, gender, userNumber, monthOfBirthday, yearOfBirthday, dayOfBirthday,
                    subject1, subject2, hobby1, hobby2, picture, address, state, city);
        });
    }

    private void fillForm(String firstname, String lastname, String userEmail, String gender, String userNumber,
                          String monthOfBirthday, String yearOfBirthday, String dayOfBirthday, String subject1,
                          String subject2, String hobby1, String hobby2, String picture, String address, String state,
                          String city) {
        $("#firstName").val(firstname);
        $("#lastName").val(lastname);
        $("#userEmail").val(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").val(userNumber);
        //fill the Date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirthday);
        $(".react-datepicker__year-select").selectOption(yearOfBirthday);
        $(".react-datepicker__day--0" + dayOfBirthday).click();
        //fill the Subject
        $("#subjectsInput").val(subject1).pressEnter();
        $("#subjectsInput").val(subject2).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();

        $("#uploadPicture").uploadFile(new File("src/test/java/resources/" + picture));
        $("#currentAddress").val(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
    }

    private void submitForm() {
        $("#submit").click();
    }

    private void verifySubmittedForm(String firstname, String lastname, String userEmail, String gender, String userNumber,
                                     String monthOfBirthday, String yearOfBirthday, String dayOfBirthday, String subject1,
                                     String subject2, String hobby1, String hobby2, String picture, String address,
                                     String state, String city) {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstname + " " + lastname));
        $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirthday
                + " " + monthOfBirthday + "," + yearOfBirthday));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(address));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }

    @Test
    @DisplayName("Unsuccessful fill registration form with incorrect number")
    void unseccessfulfillPracticeFormTest() {
        step("Open students registration form", () -> open(testURL));
        step("Fill the form", () -> {
            fillForm(firstname, lastname, userEmail, gender, firstname, monthOfBirthday, yearOfBirthday, dayOfBirthday,
                    subject1, subject2, hobby1, hobby2, picture, address, state, city);
        });
        step("Submit the form", () -> submitForm());
        step("Verify submitted form", () -> {
            verifySubmittedForm(firstname, lastname, userEmail, gender, userNumber, monthOfBirthday, yearOfBirthday, dayOfBirthday,
                    subject1, subject2, hobby1, hobby2, picture, address, state, city);
        });
    }

}
