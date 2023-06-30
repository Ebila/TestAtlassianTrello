package nl.knab.test.trello.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.TestBase;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class LoginPageStepDef extends TestBase {
    @Value("${trello.web.url}")
    private String webURL;
    @Value(("${trello.web.username}"))
    private String userName;
    @Value("${trello.web.password}")
    private String userPassword;

    @Given("I navigate to the Login page of Trello")
    public void navigateToLoginPage(){
        driver.get(webURL);                           //Navigate to the Login page url
        waitVisibility(loginPage.getWebsiteLogo());
        assertTrue(loginPage.getWebsiteLogo().isDisplayed()); //Verified the Trello logo on home page
        assertEquals("Log in to Trello", driver.getTitle()); //Title name is verified
        log.info("Navigated to the login page successfully");
    }

    @When("I enter the correct credentials and submit")
    public void enterUserCredentials(){ //Enter the username, password and submit
        waitVisibility(loginPage.getUsername());
        loginPage.getUsername().sendKeys(userName);
        waitVisibility(loginPage.getContinueButton());
        loginPage.getContinueButton().click();
        waitVisibility(loginPage.getPassword());
        loginPage.getPassword().sendKeys(userPassword);
        waitVisibility(loginPage.getLoginButton());
        loginPage.getLoginButton().click();
        log.info("Login is successful");
    }

    @Then("I am logged in the home page")
    public void loggedInToHomePage(){
        waitVisibility(loginPage.getLogoOnHomePage());
        assertEquals("Boards | Trello", driver.getTitle()); //Verified the page title and URL to confirm
        assertTrue(driver.getCurrentUrl().contains("/boards"));
        log.info("Login is verified");
    }
}
