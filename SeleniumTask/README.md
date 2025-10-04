# Practical task: Selenium Java Test Automation

## Test Cases

### Scenario 1: Delete User
- Navigate to http://automationexercise.com
- Verify that the main page is visible
- Click on "Sign up / Login"
- Verify that it redirects to the login page
- Enter email and password under "New user signup!"
- Click on the "Sign up" button
- Verify that it redirected to the signup page
- Fill out the form
- Click on "Create Account"
- Verify that the user is logged in
- Click on "Delete Account"
- Verify that "Account Deleted!" is visible

### Scenario 2: Login with Non-existent User
- Navigate to http://automationexercise.com
- Verify that the main page is visible
- Click on "Sign up / Login"
- Verify that it redirects to the login page
- Enter incorrect email and password under "Login to your account"
- Click on "Login"
- Verify that the text "Your email or password is incorrect!" appears

### Scenario 3: Remove Products from Cart (This test case should fail)
- Navigate to http://automationexercise.com
- Verify that the main page is visible
- Add products to the cart
- Click on the "Cart" button
- Verify that the Cart page is displayed
- Click on the "empty cart" button (Make it search for the button even if it doesn't exist)
- Verify that the product is removed from the cart


## Acceptance criteria
- The scenarios are linear (no need to implement complex logic for now). 3 scenarios in total.
- Different locator strategies are used for a task.
- Usage of auto-generated locators is avoided.
- WebDriver API is widely used.
- Different methods of waits are used.
- Test scenarios are clear, stable and good structured.
- Each method in test scenario has assertions.
- Page Objects have consistent structure (decomposition of PO is right).
- Test scenarios are clear, stable and good structured.
- There is at least one level of inheritance between pages (Abstract Page exists).
- There is no code duplication at all.
- Inner implementation of PO is hidden from tests.
- Naming and Code Conventions should be followed.



## Second Phase

The framework should have:
1. ✅ WebDriverManager for managing drivers for different browsers;
2. ✅ PageObject / PageFactory for abstract pages;
3. ✅ Necessary business model (business objects for dedicated entities);
4. ✅ Property files with test data for different environments (at least 2);
5. ✅ XML suites for Smoke and Regression tests;
6. ✅ Possibility to make a screenshot in case of test failure; The log should have information about the saved screenshot in this case
7. ✅ Flexibility on different parameters e.g., browser, test suite, environment (this flexibility will help CI integration in future);
8. ✅ Add logging of every step (with log4j or any similar lib) for your solution implemented based on previous modules.

9. ✅ Configure logs format in informative way
10. ✅ Demonstrate usage of different log levels (debug, action, error, etc)
11. ✅ Configure ability to write logs in console and to save logs in a file (a new file should be created for each day). By default logs are written in console and are stored in file.
12. ✅ Test results should present on job graphics, and screenshots should be archived as artifacts.

Bonus TAsk

* Implement your test scenario (or part of it, if it is possible) using some ready-made Selenium wrapper/framework (e.g. Selenide, Serenity, JDI, HtmlElements, etc)
* Use highlighting of elements during test execution. If any action is performed on any element during test execution, this element should be highlighted.

Acceptance criteria

* Match every point from home task is considered as acceptance criteria.


## Running Test Suites

### Command Line

```bash
mvn clean test "-DsuiteXmlFile=testng.xml"

mvn clean test "-DsuiteXmlFile=testng-smoke.xml"
mvn allure:serve
```


## Third phase

1. Implement the following design patterns in your solution from previous module*:
    * Singleton
    * Factory Method
    * Decorator 


2. You can use any area of you code to apply the pattern (any code layer – test, service or page object and their combinations).
    
    You may consult with your mentor about the specific area of applying the pattern as well, but try to make a self-dependent final decision.
    Revise your code to match S.O.L.I.D. principles. Provide list of corrections to your mentor, e.g.:
    Class


| Class | Problem | Solution |
|-------|---------|----------|
| WebDriverManager | Class single responsibility is broken. Contains methods irrelevant to browser/WD management. | Dedicated class XYZ is created for utility methods, some are shifted to abstract PO class. |
| HomePage | Open-closed principle is broken. Fields of the PO class are exposed (public), some redundant methods are present. | Fields of the PO class are set private, redundant/extra methods are eliminated from HomePage PO class and shifted to the class XYZ, which extends HomePage |



### Bonus TAsk
Implement any extra pattern mentioned during the training session (for extra mark) or any other pattern instead of the list above. Exact pattern list should be discussed with your mentor.


### Acceptance criteria
1. PATTERNS: All patterns from mandatory part (Singleton, Factory Method, Decorator) should be implemented.
2. PATTERNS: Classes which were modified/created during pattern implementation should be invoked during the test run. Just storing them in project packages is NOT enough.
3. S.O.L.I.D.: Code is revised to match S.O.L.I.D. principles.
4. S.O.L.I.D.: at least 3 fixes are described within the table per example above and delivered to a mentor.
5. S.O.L.I.D.: fixes are implemented in code structure.
6. BONUS TASK:extra patterns mentioned during the training session are implemented considering criteria from point #2 above.

### Solution


| Class         | Problem                                                                                                           | Solution |
|---------------|-------------------------------------------------------------------------------------------------------------------|----------|
| DriverFactory |                                                                                                                   | Dedicated class XYZ is created for utility methods, some are shifted to abstract PO class. |
| HomePage      | Open-closed principle is broken. Fields of the PO class are exposed (public), some redundant methods are present. | Fields of the PO class are set private, redundant/extra methods are eliminated from HomePage PO class and shifted to the class XYZ, which extends HomePage |

