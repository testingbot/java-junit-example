[![Build Status](https://travis-ci.org/testingbot/java-junit-example.svg?branch=master)](https://travis-ci.org/testingbot/java-junit-example)

## TestingBot - Java & JUnit

TestingBot provides an online grid of browsers and mobile devices to run Automated tests on via Selenium WebDriver.
This example demonstrates how to use Java with JUnit to run a test in parallel across several browsers.

### Environment Setup

1. Setup
	* Clone this repository
	* Install dependencies `mvn install`

2. TestingBot Credentials
    * Add your TestingBot Key and Secret as environmental variables. You can find these in the [TestingBot Dashboard](https://testingbot.com/members/).
    ```
    $ export TB_KEY=<your TestingBot Key>
    $ export TB_SECRET=<your TestingBot Secret>
    ```

## Running Tests
* To run a single test, run `mvn test -P single`
* To run parallel tests, run `mvn test -P parallel`

You will see the test result in the [TestingBot Dashboard](https://testingbot.com/members/)

### Resources
##### [TestingBot Documentation](https://testingbot.com/support/getting-started/junit.html)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)