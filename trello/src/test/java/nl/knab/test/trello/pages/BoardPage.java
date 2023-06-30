package nl.knab.test.trello.pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
@Getter
public class BoardPage { //Locators to create a board

    @FindBy(className = "X7iA6JJNiXCA2r")
    private WebElement createButton;

    @FindBy(xpath = "//span[contains(text(),'Create board')]")
    private WebElement createBoardOption;

    @FindBy(className = "TzntopStGOcVjM")
    private WebElement createBoardHeading;

    @FindBy(className = "fMvxkh4DHKJGnq")
    private WebElement boardTitleInput;

    @FindBy(xpath = "//div[contains(text(),'Workspace')]")
    private WebElement workspaceVisibilty;

    @FindBy(xpath = "//button[contains(text(), 'Create')]")
    private WebElement createButtonOnCreateBoard;

    @FindBy(xpath = "//*[contains(text(),'YOUR WORKSPACES')]")
    private WebElement yourWorkSpaceHeader;

    @FindBy(xpath = "//*[contains(text(),'YOUR WORKSPACES')]/../descendant::h3[@class='boards-page-board-section-header-name']")
    private WebElement yourPersonalWorkSpaceName;

    @FindBy(className = "board-tile-details-name")
    private WebElement createdWorkSpaceName;
}
