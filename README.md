# TestAtlassianTrello

**Pre-requisites**

Java 19

Maven

**Steps to Run**
#Open the project in an IDE(Intellij), click the 'Execute maven goal' option from Maven

#Run below command(password is generated during password encryption):

mvn clean test -Djasypt.encryptor.password=7k9u#b*zh

**Reporting**

Cucumber reports and Maven Surefire reports are generated in target folder


Improvements
-------------
#Keep the status of the application same before and after execution of test suite. Here, test scenarios are not written to delete boards, which needs to be implemented.

#The UI test is not completely stable and need to investigate whether the issue is with driver or not.

#The board created by automated UI test doesn't have the default sections 'To do', 'Doing', and 'Done' if it's not the first board that was created, made it difficult to assert those sections inside the board.

#Need to cover the negative scenarios in the UI test for the Create Board functionality.

#There was issues with permission while triggering PUT or DELETE requests to close/delete a board, need to investigate that.

#Browser support - Only added Chrome browser, will extend it to other browsers(headless) and make use of SeleniumGrid for that.

#Running directly from the terminal is failing beacause of issue in Maven compiling, that needs to be rectified(could be an issue of maven plugin version mismatch)

#Exclude launching the browser instances for API requets.

#May use separate properties file for application specific and to store credentials.

#May enter the board names from a random genrator, even though board is identified by its id and not name.

