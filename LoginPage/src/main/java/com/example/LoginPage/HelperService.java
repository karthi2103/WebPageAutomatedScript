package com.example.LoginPage;

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelperService {

  public boolean verifyContent (String val1, String val2){
    return val1.equals(val2);
  }

  public boolean isElementSeenOnPage(List<WebElement> elements){
    return !elements.isEmpty();
  }

}
