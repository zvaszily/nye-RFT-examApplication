import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;

public class uiTest2 {

    public SelenideElement searchButton = $("[data-test=site-header-search-action]");

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() {
        searchButton.click();

        $(byClassName("_wt-input__inner_1pdmso7_92")).sendKeys("Selenium");

        $(byClassName("quick-search__results")).shouldBe(visible);
    }


}
