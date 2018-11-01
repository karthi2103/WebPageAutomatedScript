package com.example.LoginPage;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class WebdriverConfig {

  @Value("${driverPath}")
  private String driverPath;

  @Bean
  public WebDriver getWebDriver(){
    log.info("Starting Local chrome driver...!");
    WebDriver driver;



    System.setProperty("webdriver.chrome.driver", driverPath);
    driver = new ChromeDriver();
    log.info("Connected with Local chrome Browser! Web Driver : {}", driver);
    return driver;
  }
}
