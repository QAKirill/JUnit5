package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class KinopoiskTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.kinopoisk.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("/lists/categories/movies/1/");
    }

    @ValueSource( strings = {"The Green Mile", "The Shawshank Redemption", "Forrest Gump"})

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @ParameterizedTest(name = "В топ 250 лучших фильмов присутствет фильм {0}")
    void successfulSearchMovieInTop250(String name) {
        $(".styles_content__2mO6X").$(byPartialLinkText("250")).click();
        $(".styles_contentSlot__h_lSN").shouldHave(Condition.text(name));
    }
}
