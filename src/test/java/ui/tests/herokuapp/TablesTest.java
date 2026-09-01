package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.github.fge.jsonschema.core.tree.SchemaTree;
import io.qameta.allure.Allure;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ui.pages.herokuapp.TablesPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

public class TablesTest extends UITestBase {

    private TablesPage tablesPage = new TablesPage();

    static Stream<Arguments> sortingTestData() {
        return Stream.of(
                Arguments.of(1, 1, List.of("Bach", "Conway", "Doe", "Smith")),
                Arguments.of(1, 2, List.of("Bach", "Doe", "Smith", "Conway"))
                //Arguments.of(1, 3, List.of("Bach", "Doe", "Smith", "Conway"))
        );
    }

    @BeforeEach
    public void setup() {
        tablesPage = Selenide.page(new TablesPage());
        tablesPage.openPage();
    }

    @Test
    public void getTable() {
        tablesPage.getColumnData(1,4);
        System.out.println("Data is " + tablesPage.getColumnData(1, 4).texts());
        tablesPage.getRows().should(size(24));
        System.out.println("Sum is " + tablesPage.getColumnSum(1, 4));
    }

    @ParameterizedTest
    @MethodSource("sortingTestData")
    @Tag("tTest")
    public void sorting(int tableNumber, int headerNumber, List<String> columnData) {
        //tablesPage.headerClick(tableNumber, headerNumber);
        Allure.step("Жмем на хедер " + headerNumber + " в таблице", () -> tablesPage.headerClick(tableNumber,
                headerNumber));
        //tablesPage.getColumnData(1, 1).should(texts("Bach", "Doe", "Smith", "Conway"));
        tablesPage.getColumnData(tableNumber, 1).should(texts(columnData));
    }

    @Test
    public void headerClickByName() {
        tablesPage.headerClickByName(1, "First Name");
        tablesPage.getColumnData(1, 1).should(texts("Bach", "Doe", "Smith", "Conway"));

        List<String> columnData = tablesPage.getColumnData(1, 1).texts();
        System.out.println("data is " + columnData);


/*        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(columnData)
                .as("soft asssert")
                .hasSize(3);*/

        assertSoftly(softly -> {
            softly.assertThat(columnData)
                    .as("soft asssert")
                    .hasSize(3);
        });

        assertThat(columnData)
                .as("testData")
                .hasSize(2)
                .doesNotContain("Do")
                ;

        //softly.assertAll();
    }

}
