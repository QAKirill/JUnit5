package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexWeatherTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://yandex.ru/pogoda/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("");
    }

    @CsvFileSource(resources = "/successfulSearchYandexWeather.csv", delimiter = '|')

    @Tags({
            @Tag("web"),
            @Tag("search")
    })
    @ParameterizedTest(name = "При вводе в поиске города {0} открылась страница {1} и есть элемент {2}")
    void successfulCitySelectYandexWeather(String searchQuery, String title, String cardTitle) {
        $(".mini-suggest__input-wrapper").$("input").setValue(searchQuery);
        $(".mini-suggest__popup-inner").
                $(By.xpath("ul[contains(@id,'suggest-list')]")).
                $(By.xpath("li[contains(@id,'0')]")).shouldHave(Condition.text(searchQuery)).click();
        $("title").shouldHave(attribute("text", title));
        $("#main_title").shouldHave(Condition.text(cardTitle));
    }
}
