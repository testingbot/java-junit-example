package com.testingbot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SingleTest extends TestingBotJUnitTest {

  @Test
  public void test() throws Exception {
    driver.get("https://www.google.com/ncr");
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("TestingBot");
    element.submit();
    Thread.sleep(5000);

    assertEquals("TestingBot - Google Search", driver.getTitle());
  }
}
