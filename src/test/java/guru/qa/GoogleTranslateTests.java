package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTranslateTests {
    public static Map<String, String> map = Map.of(
            "Футбол", "Football",
            "Хоккей", "Hockey",
            "Шахматы", "Chess"
    );

    private static Stream<Arguments> getKeyValue() {
        List<String> keys = new ArrayList<>(map.keySet());
        List<String> values = new ArrayList<>(map.values());
        return IntStream.range(0, map.size())
                .mapToObj(i -> Arguments.of(keys.get(i), values.get(i)));
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://translate.google.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("?sl=ru&tl=en&");
    }

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка перевода в GoogleTranslate")
    @ParameterizedTest(name = "Результат перевода {0} на английский {1}")
    @MethodSource("getKeyValue")
    void searchResultsShouldNotBeEmpty(String key, String value){
        $(".er8xn").setValue(key);
        $(".usGWQd").shouldHave(Condition.text(value));
    }

}
