import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {

    private static final String URL = "https://datausa.io/";

    @Test
    public void searchAll() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<Dimension> dimensions = given()
                .when()
                .get("api/searchLegacy")
                .then()
                .extract().body().jsonPath().getList("results", Dimension.class);
        //проверяем что поле key не равно null для всех элементов (проверка успешная)
        dimensions.forEach(x-> Assert.assertNotNull(x.getKey()));

        //проверяем что поле Keywords не равно null для всех элементов (проверка упадет)
        dimensions.forEach(x-> Assert.assertNotNull(x.getKeywords()));
    }

    @Test
    public void searchByID() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        String id = "01000US";
        List<String> list = Arrays.asList("united", "states");
        given()
                .when()
                .get("api/searchLegacy?id=" + id)
                .then()
                .assertThat()
                .body("results[0].alts", equalTo(list))
                .body("results[0].image.author", equalTo("David J Laporte"));;
    }

    @Test
    public void searchStateNumbersFromTotal() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        var response = given()
                .when()
                .get("api/searchLegacy")
                .then()
                .assertThat()
                .body("totals.Geography.State", equalTo(52));
    }
}