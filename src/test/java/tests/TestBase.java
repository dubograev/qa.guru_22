package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    @BeforeAll
    static void setUp() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/";
        Configuration.startMaximized = true;
    }

    String  testURL = "https://demoqa.com/automation-practice-form",
            firstname = "Joe",
            lastname = "Biden",
            userEmail = "andreddd111@gmail.com",
            gender = "Female",
            userNumber = "8002001010",
            monthOfBirthday = "May",
            yearOfBirthday = "1990",
            dayOfBirthday = "14",
            subject1 = "Maths",
            subject2 = "English",
            hobby1 = "Sports",
            hobby2 = "Music",
            picture = "image.jpg",
            address = "Russia \n" +
                    "Pyatigorsk \n" +
                    "ul. Lenina, 300",
            state = "Haryana",
            city = "Karnal";

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        closeWebDriver();
    }
}
