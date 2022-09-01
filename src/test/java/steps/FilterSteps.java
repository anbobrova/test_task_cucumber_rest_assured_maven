package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.SearchPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class FilterSteps {
    SearchPage searchPage = new SearchPage();

    @When("Применяем фильтр для категории {string}")
    public void applyFilterForCategory(String reportType) {
        searchPage.filterReportType(reportType);
    }

    @Then("Количество отображенных отчетов для категории {string} равно количеству результатов в колонке фильтра")
    public void compareReportNumber(String reportType) {
        List<String> reportTypes = searchPage.getReportType(reportType);
        int reportsFromFilter = searchPage.getNumberOfReportsFromFilter(reportTypes);
        ElementsCollection reportsFromResults = searchPage.getReportsFromResults().shouldHave(CollectionCondition.size(reportsFromFilter));
        Assert.assertEquals(reportsFromResults.size(), reportsFromFilter);
    }

    @Then("Проверяем, что количество элементов равно {string}")
    public void checkReportsNumber(String reportsNumber) {
        ElementsCollection reportsFromResults = searchPage.getReportsFromResults();
        reportsFromResults.shouldHave(CollectionCondition.size(Integer.parseInt(reportsNumber)));
    }

    @When("Очищаем фильтр")
    public void clearFilter() {
        $("div.clear").click();
        searchPage.waitForLoadingDisappear();
    }

    @Then("Количество отображенных отчетов больше {string}")
    public void checkNumberOfReports(String reportsNumber) throws InterruptedException {
        ElementsCollection elements = searchPage.getReportsFromResults();
        elements.shouldHave(CollectionCondition.sizeGreaterThan(Integer.parseInt(reportsNumber)));
    }
}