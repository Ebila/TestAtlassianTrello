package nl.knab.test.trello.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.TestBase;

import static org.junit.Assert.*;
@Slf4j
public class BoardStepDef extends TestBase{
    static String boardName;

    @When("I click the Create button to create a new board")
    public void createANewBoardFromHomePage() {
        waitVisibility(createBoardPage.getCreateButton());
        createBoardPage.getCreateButton().click(); //Click the big Create button on the homepage
        waitVisibility(createBoardPage.getCreateBoardOption());
        createBoardPage.getCreateBoardOption().click();   //Select the 'Create board' option
        log.info("A request is made to create a new board");
    }
    @And("I enter the board name {string} and submit")
    public void enterTheDetailsOfBoard(String newBoardName){
        boardName = newBoardName;
        waitVisibility(createBoardPage.getCreateBoardHeading());
        assertEquals("Create board", createBoardPage.getCreateBoardHeading().getText());
        createBoardPage.getBoardTitleInput().sendKeys(newBoardName); //Enter the board name and leave the rest to default
        assertTrue(createBoardPage.getWorkspaceVisibilty().isEnabled());
        createBoardPage.getCreateButtonOnCreateBoard().click(); //Click the Create button to create the board
    }
    @Then("a new board is created successfully")
    public void aNewBoardIsCreatedSuccessfully() {
        if (driver.getCurrentUrl().contains("/" + boardName)) {
            assertTrue(driver.getCurrentUrl().contains("/" + boardName));//if navigated directly to board page, verify the url
            log.info("The board name is verified");
        } else {
            waitVisibility(createBoardPage.getCreatedWorkSpaceName());
            assertTrue(createBoardPage.getYourWorkSpaceHeader().isDisplayed());
            assertEquals("Trello Workspace", createBoardPage.getYourPersonalWorkSpaceName().getText());
            assertEquals(boardName, createBoardPage.getCreatedWorkSpaceName().getText());//Verify the board name created
            log.info("The board is visible on the workspace");
        }
    }
}
