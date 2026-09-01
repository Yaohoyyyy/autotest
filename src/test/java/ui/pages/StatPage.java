package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class StatPage {

    private static final String URL = "https://media.halvacard.ru/financial-literacy/kakaia-sredniaia-i-mediannaia-zarplata-v-rossii-aktualnye-cifry-po-regionam";

    public void openPage() {
        open(URL);
    }

    private SelenideElement table() {
        return $x("//div[@class='wysiwyg-markup']//table");
    }

    public ElementsCollection allRows() {
        return table().$$x(".//tbody/tr");
    }

    public List<String[]> getTableData() {
        List<String[]> data = new ArrayList<>();
        for (SelenideElement row : allRows()) {
            ElementsCollection cells = row.$$x("td");
            String region = cells.get(0).text();
            String avgSalary = cells.get(1).text();
            String medianSalary = cells.get(2).text();
            data.add(new String[]{region, avgSalary, medianSalary});
        }
        return data;
    }

    public long getTotalSum() {
        long sum = 0;
        for (SelenideElement row : allRows()) {
            ElementsCollection cells = row.$$x("td");
            for (int i = 1; i <= 2; i++) {
                String digits = cells.get(i).text().replaceAll("[^\\d]", "");
                if (!digits.isEmpty()) {
                    sum += Long.parseLong(digits);
                }
            }
        }
        System.out.println("sum: " + sum);
        return sum;
    }
}
