package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {
    @Given("Открываем сайт {string}")
    public void openWebsite(String url) {
        Selenide.open(url);
    }
}