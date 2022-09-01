package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

import static com.codeborne.selenide.Selenide.$x;

public class SearchSteps {

    private final SelenideElement searchInput = $x("//input[@placeholder='Find a report...']");

    SearchPage searchPage = new SearchPage();

    @When("Вводим текст {string} в строку поиска")
    public void typeTextInSearchField(String searchText) {
        searchInput.sendKeys(searchText);
    }

    @And("Кликаем на результат {string}")
    public void clickOnReport(String report) {
        $x("//ul[@class = 'results active']//div[text()='" + report + "']/ancestor::a").click();
    }

    @Then("Открыта страница отчета {string}")
    public void isPageOpened(String profileTitle) throws InterruptedException {
        $x("//h1/p[text()='" + profileTitle + "']").isDisplayed();
        Thread.sleep(1000);
    }

    @Then("Поле с результатами отображает текст NO RESULTS FOUND")
    public void CheckNoResultsFoundMessage() {
        searchPage.noResultsFoundTextIsDisplayed();
    }
}