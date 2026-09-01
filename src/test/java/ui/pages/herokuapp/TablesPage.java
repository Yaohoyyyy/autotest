package ui.pages.herokuapp;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class TablesPage extends HerokuappPage{

    @FindBy(xpath = "//table[@id = 'table1']")
    public SelenideElement tableData_1;

    @FindBy(xpath = "//table[@id = 'table2']")
    public SelenideElement tableData_2;

    public TablesPage() {
        super("/tables");
    }

    public SelenideElement getTableByNumber(int tableNumber) {
        SelenideElement table = null;
        switch (tableNumber) {
            case 1:
                return tableData_1;
            case 2:
                return tableData_2;
        }
        return table;
    }

    @Step("Нажатие на заголовок таблицы")
    public void headerClick(int tableNumber, int headerNumber) {
        SelenideElement table = getTableByNumber(tableNumber);
        SelenideElement header = table.$x(".//th[" + headerNumber + "]");
        header.click();
    }

    public void headerClickByName(int tableNumber, String headerName) {
        SelenideElement table = getTableByNumber(tableNumber);
        SelenideElement header = table.$x(".//th/span[text() = '" + headerName + "']");
        header.click();
    }

    public List<String> getSequenceRowData(int tableNumber, int headerNumber) {
        List<String> list = new ArrayList<>();
        return list;
    }

    public ElementsCollection getRows() {
        return tableData_1.$$x(".//td");
    }

    public ElementsCollection getColumnData(int tableNumber, int headerNumber) {
        SelenideElement table = getTableByNumber(tableNumber);
        ElementsCollection list;
        list = table.$$x(".//td[" + headerNumber + "]");
        return list;
    }

    public double getColumnSum(int tableNumber, int headerNumber) {
        double sum = 0.0;
        for (SelenideElement s: getColumnData(tableNumber, headerNumber)) {

            double d = Double.parseDouble(s.text().replace("$", ""));
            sum = sum + d;
        }

        return sum;
    }



}
