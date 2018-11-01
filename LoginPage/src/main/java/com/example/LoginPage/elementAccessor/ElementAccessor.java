package com.example.LoginPage.elementAccessor;

import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import com.example.LoginPage.models.Elementfamily;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ElementAccessor {
  @Autowired
  private ElementConfigService elementConfigService;

  public List<WebElement> getWebElementsInAList(WebDriver driver, ElementPage elementPage, ElementName elementName, Elementfamily elementfamily){
    String value = elementConfigService.getValue(elementPage,elementName,elementfamily);
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(10,TimeUnit.SECONDS)
            .pollingEvery(2,TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class)
            .ignoring(ElementNotVisibleException.class)
            .ignoring(StaleElementReferenceException.class);;
    try {
      if (elementfamily.equals(Elementfamily.XPATH)) {
         ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
           @Override
           public WebElement apply(WebDriver driver) {
             return driver.findElement(By.xpath(value));
           }
         });
         return driver.findElements(By.xpath(Strings.nullToEmpty(value)));
      }
      if (elementfamily.equals(Elementfamily.CLASS)) {
        ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
          @Override
          public WebElement apply(WebDriver driver) {
            return driver.findElement(By.className(value));
          }
        });
        return driver.findElements(By.className(Strings.nullToEmpty(value)));
      }
      if (elementfamily.equals(Elementfamily.ID)) {
        ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
          
          @Override
          public WebElement apply(WebDriver driver) {
            return driver.findElement(By.id(value));
          }
        });
        return driver.findElements(By.id(Strings.nullToEmpty(value)));
      }
      if (elementfamily.equals(Elementfamily.NAME)) {
        ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
          
          @Override
          public WebElement apply(WebDriver driver) {
            return driver.findElement(By.name(value));
          }
        });
        return driver.findElements(By.name(Strings.nullToEmpty(value)));
      }
      if (elementfamily.equals(Elementfamily.CSS)) {
        ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
          
          @Override
          public WebElement apply(WebDriver driver) {
            return driver.findElement(By.cssSelector(value));
          }
        });
        return driver.findElements(By.cssSelector(Strings.nullToEmpty(value)));
      }
      if (elementfamily.equals(Elementfamily.LINK_TEXT)) {
        ((FluentWait<WebDriver>) wait).until(new Function<WebDriver, WebElement>() {
          
          @Override
          public WebElement apply(WebDriver driver) {
            return driver.findElement(By.linkText(value));
          }
        });
        return driver.findElements(By.linkText(Strings.nullToEmpty(value)));
      }
    } catch (Exception e) {
      log.error("Error finding webElementList on page : {}", e.getMessage());
    }
    return Collections.emptyList();
  }


}
