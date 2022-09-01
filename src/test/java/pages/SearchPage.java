package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private static final SelenideElement loadingBlock = $x("//span[contains(@class, 'loading')]");
    private static final SelenideElement noResultsFoundText = $("li.no-results");

    public ElementsCollection getReportsFromResults() {
        ElementsCollection elements = $$x("//ul[@class='results active']/li");
        return elements;
    }

    public int getNumberOfReportsFromFilter(List<String> reportTypes) {
        int numberOfReportsFromFilter = 0;
        if (reportTypes.size() > 1) {
            numberOfReportsFromFilter = Integer.parseInt($x("//ul//li[text()='Report Type ']/following-sibling::ul/li[text()='"
                    + reportTypes.get(1) + "']/span[@class='num']").text());
        } else
            numberOfReportsFromFilter = Integer.parseInt($x("//ul//li[text()='Report Type ']/following-sibling::li[text()='"
                    + reportTypes.get(0) + "']/span[@class='num']").text());
        return numberOfReportsFromFilter;
    }

    public void waitForLoadingDisappear() {
        //в верстке появляется белый блок, исчезновение которого занимает от 1 до 20 сек по подсчетам из разных примеров запуска
        loadingBlock.shouldNotBe(Condition.exist, Duration.ofMillis(20000));
    }

    public void filterReportType(String reportType) {
        List<String> reportTypes = getReportType(reportType);
        $x("//ul//li[text()='Report Type ']/following-sibling::li[text()='" + reportTypes.get(0) + "']").click();
        if (reportTypes.size() > 1) {
            $x("//ul//li[text()='Report Type ']/following-sibling::ul/li[text()='" + reportTypes.get(1) + "']").click();
        }
    }

    public List<String> getReportType(String reportType) {
        return Arrays.asList(reportType.split(" "));
    }

    public void noResultsFoundTextIsDisplayed(){
        Assert.assertTrue(noResultsFoundText.isDisplayed());
    }
}