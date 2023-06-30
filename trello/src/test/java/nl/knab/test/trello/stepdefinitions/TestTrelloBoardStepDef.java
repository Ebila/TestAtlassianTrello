package nl.knab.test.trello.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@Slf4j
public class TestTrelloBoardStepDef {
    private static final String ID_ORGANIZATION = "649aad8c733cb7f6ea9f06e5";
    private static String boardId;
    private String response;
    private JsonPath jsonPath;
    private static String boardNameProvided;

    @Value("${trello.api.endpoint}")
    private String apiEndpointForTrello;
    @Value("${trello.api.user.key}")
    private String apiKey;
    @Value("${trello.api.user.token}")
    private String userSecurityToken;


    @When("a request is made to create a new Trello board {string} using the endpoint")
    public void createABoard(String boardName) {
        boardNameProvided = boardName;
        response = given()
                .queryParam("name", boardName)
                .queryParam("key", apiKey)
                .queryParam("token", userSecurityToken)
                .header("Content-Type", "application/json")
                .post(apiEndpointForTrello +  "/1/boards/")
                .then()
                .statusCode(200) //Status code is verified
                .assertThat()
                .body("name", equalTo(boardName)) //Board name in response should be same as the name supplied to request
                .extract()
                .response()
                .asString();
    }
    @Then("the board is created successfully")
    public void verifyTheCreateBoardResponse() {
        jsonPath = JsonPath.from(response); //Extracting the response and asserting it using JsonPath Library
        boardId=jsonPath.get("id"); //Unique id of the board is fetched
        jsonPath.get("idOrganization").equals(ID_ORGANIZATION); //The organization id from response should match the id for which account is related
        log.info("A new board is created successfully via API endpoint");
    }
    @When("the details of the board are fetched then they are returned")
    public void getBoardById() {
            given()
                .queryParam("key", apiKey)
                .queryParam("token", userSecurityToken)
                .header("Content-Type", "application/json").urlEncodingEnabled(false)
                .get(apiEndpointForTrello +  "/1/boards/" + boardId)
                .then()
                .statusCode(200)
                .assertThat() //Asserting the response body using Hamcrest Matchers
                .body("id", equalTo(boardId))
                .body("name", equalTo(boardNameProvided)) //The name of the board fetched by request should be same as the name of created board
                .body("idOrganization", equalTo(ID_ORGANIZATION));
            log.info("The details of the board are fetched successfully");
    }
}
