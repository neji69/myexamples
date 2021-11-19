package example.restassured;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.condition.AnyOf;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class RestTest {
    private final static String BASE_URL = "http://users.bugred.ru/tasks/rest";
    private final static String CREATE_USER_PATH = "/createuser";

    private RequestSpecification specBuilder;

    @DataProvider(name = "create", parallel = true)
    static Object[][] createData() {

        String fileFullJsonRequest = "datafull.json";

        List<User> usersDataList = readJsonFromResources(fileFullJsonRequest);

        Faker faker = new Faker();
        for (User user : usersDataList) {
            user.setEmail(faker.internet().emailAddress());
            user.setName(faker.name().username());
        }

        Object[][] users = new Object[usersDataList.size()][];
        for (int i = 0; i < usersDataList.size(); i++) {
            users[i] = new Object[1];
            users[i][0] = usersDataList.get(i);
        }
        return users;
    }

    @BeforeMethod
    void setUp() {
        specBuilder = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setBasePath(CREATE_USER_PATH).build();
    }

    @Test(dataProvider = "create", dataProviderClass = RestTest.class)
    void createUserTest(User user) {
        Assertions.assertThat(user.getEmail()).isNotNull();
        Assertions.assertThat(user.getName()).isNotNull();
        Assertions.assertThat(user.getCompanies()).isNotNull();
        Assertions.assertThat(user.getTasks()).isNotNull();

        Response response =
                given()
                        .spec(specBuilder).body(user)

                        .when()
                        .post()

                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().response();

        response.jsonPath().prettyPrint();
        Party party = response.as(Party.class);

        Assertions.assertThat(party.getName()).isEqualTo(user.getName());
        Assertions.assertThat(party.getEmail()).isEqualTo(user.getEmail());

        Assertions.assertThat(party.getHobby()).satisfiesAnyOf(
                hobby -> Assertions.assertThat(hobby).isEqualTo(user.getHobby()),
                hobby -> Assertions.assertThat(hobby).isEmpty()
        );
        Assertions.assertThat(party.getPhone()).satisfiesAnyOf(
                phone -> Assertions.assertThat(phone).isEqualTo(user.getPhone()),
                phone -> Assertions.assertThat(phone).isEmpty()
        );

        Assertions.assertThat(party.getCompanies().size()).isEqualTo(user.getCompanies().length);
        Assertions.assertThat(party.getCompanies().get(0).getId()).isEqualTo(user.getCompanies()[1]);
        Assertions.assertThat(party.getCompanies().get(1).getId()).isEqualTo(user.getCompanies()[0]);
        Assertions.assertThat(party.getCompanies().get(0).getName()).isNotNull();
        Assertions.assertThat(party.getCompanies().get(1).getName()).isNotNull();
    }

    private static List<User> readJsonFromResources(String jsonFile) {
        Gson gson = new Gson();

        Type usersListType = new TypeToken<ArrayList<User>>() {
        }.getType();

        InputStream inputStream = RestTest.class.getClassLoader().getResourceAsStream(jsonFile);
        assert inputStream != null;
        return gson.fromJson(new InputStreamReader(inputStream), usersListType);
    }
}
